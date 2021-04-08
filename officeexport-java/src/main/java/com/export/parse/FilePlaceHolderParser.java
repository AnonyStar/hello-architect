package com.export.parse;

import com.export.model.FileModel;
import com.export.model.FileTypeConst;
import com.export.model.SingleDataModel;
import com.export.model.XmlModel;
import com.export.model.XpathEnum;
import com.export.utils.StringUtil;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhoucx
 * @time: 2021/3/19 11:05
 */
public class FilePlaceHolderParser extends AbstractWordParser {

    private FilePlaceHolderParser() {
    }

    ;

    @Override
    protected void handler(XmlModel xmlModel) {
        // 添加标签 文件附件
        parseAttachmentNode(xmlModel);
    }

    @Override
    public void process(XmlModel xmlModel, SingleDataModel dataModel) {
        parseAttachmentNode(xmlModel, dataModel);
    }

    private void parseAttachmentNode(XmlModel xmlModel, SingleDataModel dataModel) {
        final List<Element> wtList = xmlModel.getNoteList4Element(xmlModel.getDocumentPart(), XpathEnum.W_T);
        final Map<String, FileModel> fileData = dataModel.getFileData();
        // Relationships 的添加
        Element node = xmlModel.getSingleNote4Root(XpathEnum.WORD_DOCUMENT_RELS_PKG_XMLDATA);
        Element finalNode = node.element("Relationships");
        AtomicInteger defRid = new AtomicInteger(generateRid(finalNode));
        // 1. 先找到标记位置 {？image}|
        AtomicInteger serNum = new AtomicInteger(0);

        wtList.forEach(element -> {
            final String text = element.getText();
            if (StringUtil.isNotBlank(text) && text.contains("{?")) {
                // 说明是文件标记点，进行文件处理
                final String context = StringUtil.substringBetween(text, "{?", "}");
                if (StringUtil.isNotBlank(context) && fileData.containsKey(context.trim())) {
                    final FileModel fileModel = fileData.get(context.trim());
                    fileModel.setKey(context);
                    initFileModel(defRid, serNum, fileModel);
                    // 有添加数据说明
                    //doc下插入节点
                    element.setText("");
                    element.add(createWObjectNode(fileModel));
                    // Relationships 的添加
                    // "/word/_rels/document.xml.rels 下添加两条记录
                    addRelationship(finalNode, fileModel);

                    // 添加两个 pkg:part  一个图标的，一个附件的，
                    addPart(xmlModel, fileModel);
                }
                element.setText("");
                serNum.getAndIncrement();
            }

        });
    }

    private void initFileModel(AtomicInteger defRid, AtomicInteger serNum, FileModel fileModel) {
        defRid.addAndGet(1);
        fileModel.setImageRid("rId" + (defRid.get() + serNum.get()));
        fileModel.setFileRid("rId" + (defRid.get() + serNum.get() + 2));
        defRid.addAndGet(1);

        //_x0000_i1011251
        // _x0000_t75
        fileModel.setShapeId("_x0000_i10119" + serNum);
        fileModel.setShapeTypeId("_x0000_t" + serNum);

        //target 初始
        fileModel.setImageTarget("media/image" + serNum.get() + ".emf");
        final String fileSuffix = FileTypeConst.getFileSuffix(fileModel.getFileTye());
        fileModel.setFileTarget("embeddings/Microsoft_File" + serNum.get() + "." + fileSuffix);

        fileModel.setImageBinaryDataPlace(fileModel.getKey() + "_image");
        fileModel.setFileBinaryDataPlace(fileModel.getKey() + "_file");
    }

    /**
     * 获取文本中 附件标记，同时生成相应的节点信息.
     *
     * @param xmlModel
     */
    private void parseAttachmentNode(XmlModel xmlModel) {
        final List<Element> wtList = xmlModel.getNoteList4Element(xmlModel.getDocumentPart(), XpathEnum.W_T);
        // todo 待实现


    }

    private void addPart(XmlModel xmlModel, FileModel fileModel) {
        final Element pkge = xmlModel.getSingleNote4Root(XpathEnum.PKG_PACKAGE);
        Element part1 = new DefaultElement("pkg:part");
        part1.add(new DefaultAttribute("pkg:name", "/word/" + fileModel.getImageTarget()));
        part1.add(new DefaultAttribute("pkg:contentType", "image/x-emf"));
        final DefaultElement binaryData1 = new DefaultElement("pkg:binaryData");
        binaryData1.setText("${(" + fileModel.getImageBinaryDataPlace() + ")!''}");
        part1.add(binaryData1);

        Element part2 = new DefaultElement("pkg:part");
        part2.add(new DefaultAttribute("pkg:name", "/word/" + fileModel.getFileTarget()));
        final FileTypeConst.FileType fileType = FileTypeConst.getFileType(fileModel.getFileTye());
        part2.add(new DefaultAttribute("pkg:contentType", fileType.getContextType()));
        final DefaultElement binaryData2 = new DefaultElement("pkg:binaryData");
        binaryData2.setText("${(" + fileModel.getFileBinaryDataPlace() + ")!''}");
        part2.add(binaryData2);
        pkge.add(part1);
        pkge.add(part2);
    }


    private static class Singleton {
        private static FilePlaceHolderParser instance;

        static {
            instance = new FilePlaceHolderParser();
        }

        public static FilePlaceHolderParser getInstance() {
            return instance;
        }
    }

    public static FilePlaceHolderParser getInstance() {
        return Singleton.getInstance();
    }

}
