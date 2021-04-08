package com.export.core;

import com.export.model.DataModel;
import com.export.model.FileModel;
import com.export.model.SingleDataModel;
import com.export.model.XmlModel;
import com.export.parse.FilePlaceHolderParser;
import com.export.parse.MergePlaceHolderParser;
import com.export.parse.VerifyXmlParse;
import com.kmood.datahandle.DataConverter;
import com.kmood.utils.FileUtils;
import com.kmood.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import sun.awt.image.BufferedImageGraphicsConfig;
import sun.awt.shell.ShellFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: zhoucx
 * @time: 2021/3/16 16:03
 */
@Slf4j
public abstract class AbstractTemplateHandler implements TemplateHandler {

    /**
     * 存放模板路径目录 xml文件
     */
    private static final ThreadLocal<String> MODEL_DIR_PATH_LOCAL = new ThreadLocal<>();
    /**
     * 存放模板名称 .ftl
     */
    private static final ThreadLocal<String> MODEL_NAME_LOCAL = new ThreadLocal<>();


    private XmlModel xmlModel;

    @Override
    public void process(String xmlPath) throws DocumentException, IOException {
       this.process(xmlPath, null);
    }

    @Override
    public void process(String xmlPath, String ftlOutDirPath) throws DocumentException, IOException {
        this.xmlModel = new XmlModel(xmlPath);
        if (StringUtil.isNotBlank(ftlOutDirPath)){
            ftlOutDirPath = ftlOutDirPath + File.separator + xmlModel.getFileName() + ".ftl";
        }else {
            ftlOutDirPath = xmlPath + ".ftl";
        }
        xmlModel.setFtlOutPath(ftlOutDirPath);
        VerifyXmlParse.getInstance().process(xmlModel);
        MergePlaceHolderParser.getInstance().process(xmlModel);

    }


    @Override
    public void produce(SingleDataModel dataModel, Writer writer) throws IOException, TemplateException {
        String ftlOutDirPath = xmlModel.getFtlOutPath();
        // 数据处理
        FilePlaceHolderParser.getInstance().process(xmlModel, dataModel);


        try (Writer writer1 =
                     new OutputStreamWriter(
                             new FileOutputStream(ftlOutDirPath),TemplateConfigFactory.getConfiguration().getDefaultEncoding()
                     )
        ){
            xmlModel.getDocument().write(writer1);
            writer1.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        xmlPlaceHolderHandler(ftlOutDirPath);

        Configuration configuration = TemplateConfigFactory.getConfiguration();
        Template template = configuration.getTemplate(getModelName());

        // 数据处理
        final Map<String, Object> baseData = dataModel.getBaseData();
        final Map<String, FileModel> fileData = dataModel.getFileData();
        fileData.entrySet().forEach(enty -> {
            final FileModel value = enty.getValue();
            baseData.put(value.getImageBinaryDataPlace(),getImageIconBase64(value.getFile()));
            try {
                baseData.put(value.getFileBinaryDataPlace(), Base64.encodeBase64String(FileUtils.readToBytesByFile(value.getFile())));
                //baseData.put(value.getFileBinaryDataPlace(),new String(FileUtils.readToBytesByFile(value.getFile())));
            } catch (IOException e) {
                log.error("file to base64 error......");
                e.printStackTrace();
            }
        });
        Object dataConvert = DataConverter.convert(baseData, null);
        template.process(dataConvert,writer);
    }

    @Override
    public String getModelDirPath() {
        return MODEL_DIR_PATH_LOCAL.get();
    }
    @Override
    public String getModelName() {
        return MODEL_NAME_LOCAL.get();
    }
    @Override
    public void setModelDirPath(String dirPathLocal) {
        MODEL_DIR_PATH_LOCAL.set(dirPathLocal);
    }
    @Override
    public void setModelName(String nameLocal) {
        MODEL_NAME_LOCAL.set(nameLocal);
    }


    protected abstract void xmlPlaceHolderHandler(String xmlFtlPath) throws IOException;

    private String getImageIconBase64(File file) {
        String str1 = null;
        String str2 = null;
        try {
            final String fileName = file.getName();
            str1 = file.getName();
            ;
            int wv = 40;
            int hv = 200;
            if (fileName.length() > 12) {
                str1 = fileName.substring(0, 10);
                str2 = fileName.substring(10);
                wv += 40;
            }
            ShellFolder sf = ShellFolder.getShellFolder(file);
            final ImageIcon icon = new ImageIcon(sf.getIcon(true));
            Image src = icon.getImage();
            int wideth = src.getWidth(null) + 100;
            int height = src.getHeight(null) + 100;
            BufferedImage image = new BufferedImage(wideth + hv, height + wv, BufferedImage.TYPE_INT_RGB);
            BufferedImageGraphicsConfig config = BufferedImageGraphicsConfig.getConfig(image);
            image =config.createCompatibleImage(wideth + hv, height + wv, Transparency.TRANSLUCENT);
            Graphics g = image.createGraphics();

            // g.setColor(Color.BLUE); // 设置背景颜色
            g.fillRect(0, 0, wideth +  hv, height + wv);// 填充整张图片(其实就是设置背景颜色)
            g.drawImage(src, hv/2, 0, wideth, height, null);
            g.setColor(Color.black);// 设置字体颜色
            final Font font = new Font("微软雅黑", Font.PLAIN, 30);
            g.setFont(font);
            if (str2 != null) {
                g.drawString(str1, 0, height + 40);
                g.drawString(str2, 0, height + 70);
            }else {
                g.drawString(str1, 0, height + 35);

            }
            g.dispose();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", stream);
            return  Base64.encodeBase64String(stream.toByteArray());
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
