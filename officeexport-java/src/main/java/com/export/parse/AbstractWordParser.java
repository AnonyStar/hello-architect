package com.export.parse;

import com.export.model.FileModel;
import com.export.model.FileTypeConst;
import com.export.model.SingleDataModel;
import com.export.model.XmlModel;
import com.kmood.basic.SyntaxException;
import com.kmood.utils.StringUtil;
import com.kmood.word.DataContext;
import com.kmood.word.WordParserUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhoucx
 * @time: 2021/3/17 16:02
 */
public abstract class AbstractWordParser implements IParser {


    @Override
    public void process(XmlModel xmlModel) {
        handler(xmlModel);
    }


    protected abstract void handler(XmlModel xmlModel);

    protected void process(XmlModel xmlModel, SingleDataModel dataModel){

    }
    protected void placeHodlerHandle(Node WPNode) {
        List WTList = WPNode.selectNodes(".//w:t");
        Node WTNodeNew = null;
        int s = WTList.size();
        //算法分三种方式整合占位符，例 [*QF@t*   {t.QF}    *QF*] 需要将这三类整合 对于[*QF@t*遍历wt进行整合
        for (int j = 0; j < s; j++) {
            WTNodeNew = (Node) WTList.get(j);
            String text = WTNodeNew.getText();
            int fi = text.lastIndexOf('[');
            int xi = text.lastIndexOf("*");
            int ji = text.lastIndexOf("#");

            if (fi > xi || fi > ji) {
                WTNodeNew.setText(StringUtil.removeInvisibleChar(text.substring(0, fi)));
                String temp = text.substring(fi, text.length());
                for (int i = j; i <= s; i++) {
                    Node WTNodeNew_ = (Node) WTList.get(i);
                    String t = WTNodeNew_.getText();
                    WTNodeNew_.setText(StringUtil.removeInvisibleChar(t));
                    temp += t;
                    int i1 = StringUtil.countMatches(temp, '*');
                    int i2 = StringUtil.countMatches(temp, '#');
                    int i3 = StringUtil.countMatches(temp, '[');
                    int i4 = StringUtil.countMatches(temp, ']');

                    if ((i1 > 1 || i2 > 1)) {
                        if (temp.contains("#")) {
                            int endIndex = temp.indexOf('#', temp.indexOf('#') + 1) + 1;
                            t = temp.substring(endIndex, temp.length());
                            temp = StringUtil.removeInvisibleChar(temp.substring(0, endIndex));
                        }
                        if (temp.contains("*")) {
                            int endIndex = temp.indexOf('*', temp.indexOf('*') + 1) + 1;
                            t = temp.substring(endIndex, temp.length());
                            temp = StringUtil.removeInvisibleChar(temp.substring(0, endIndex));
                        }
                        if (i == j) {
                            WTNodeNew.setText(WTNodeNew.getText() + temp + StringUtil.removeInvisibleChar(t));
                        } else {
                            WTNodeNew_.setText(StringUtil.removeInvisibleChar(t));
                            WTNodeNew.setText(WTNodeNew.getText() + temp);
                        }
                        j = i;
                        break;
                    } else {
                        WTNodeNew_.setText("");
                    }
                }
            }
        }
        for (int j = 0; j < s; j++) {
            WTNodeNew = (Node) WTList.get(j);
            String text = WTNodeNew.getText();
            int di = text.lastIndexOf('{');
            int di_ = text.lastIndexOf('}');
            if (di > di_) {
                String temp = text.substring(di, text.length());
                for (int i = j + 1; i <= s; i++) {
                    Node WTNodeNew_ = (Node) WTList.get(i);
                    String t = WTNodeNew_.getText();
                    temp += t;
                    if (StringUtil.countMatches(temp, '}') > 0) {
                        WTNodeNew.setText(text.substring(0, di));
                        WTNodeNew_.setText(StringUtil.removeInvisibleChar(temp));
                        j = i;
                        break;
                    } else {
                        WTNodeNew_.setText("");
                    }
                }
            }
        }
        for (int j = s - 1; j >= 0; j--) {
            WTNodeNew = (Node) WTList.get(j);
            String text = WTNodeNew.getText();
            int fi = text.indexOf(']');
            if (fi == -1) continue;
            String text_ = text.substring(0, fi + 1);
            int i1 = StringUtil.countMatches(text_, '*');
            int i2 = StringUtil.countMatches(text_, '#');
            if (text_.contains("]") && ((i1 < 2 && i1 > 0) || (i2 < 2 && i2 > 0) || (i1 == 0 && i2 == 0))) {
                WTNodeNew.setText(StringUtil.removeInvisibleChar(text.substring(fi + 1, text.length())));
                String temp = text.substring(0, fi + 1);
                for (int i = j; i >= 0; i--) {
                    Node WTNodeNew_ = (Node) WTList.get(i);
                    String t = WTNodeNew_.getText();
                    temp = t + temp;
                    if ((StringUtil.countMatches(temp, '*') > 1 || StringUtil.countMatches(temp, '#') > 1)) {
                        WTNodeNew_.setText(StringUtil.removeInvisibleChar(temp));
                        j = i;
                        break;
                    } else {
                        WTNodeNew_.setText("");
                    }
                }
            }
        }
    }


    protected void specialPlaceHodlerHandle(Node WPNode) {
        List WTList = WPNode.selectNodes(".//w:t");
        Node WTNodeNew = null;
        int s = WTList.size();
        for (int j = 0; j < s; j++) {
            WTNodeNew = (Node) WTList.get(j);
            String text = WTNodeNew.getText();
            //包含分页符 与自定义替换符号有冲突，这里待处理解决
/*            boolean containsFY = text.contains("~");
            if(containsFY){

                WTNodeNew.setText(StringUtils.replaceAll(text,"~",""));

                Element wrelement = WTNodeNew.getParent().addElement("w:r");
                Element brelement = wrelement.addElement("w:br");
                brelement.addAttribute("w:type","page");
            }*/
            //添加分页符
//        <w:r>
//				<w:br w:type="page"/>
//		</w:r>

        }

    }


    protected void bracketToListConversion(List<Element> wpList) {
        for (int i = 0; i < wpList.size(); i++) {
            Node wpNode = wpList.get(i);
            List wtlist = wpNode.selectNodes(".//w:t");
            String[] Xarr = null;
            String[] Jarr = null;
            for (int j = 0; j < wtlist.size(); j++) {
                Node node = (Node) wtlist.get(j);
                String text1 = node.getText();
                if (text1 != null && text1.contains("[#")) {
                    //清除[##
                    Jarr = StringUtil.substringsBetween(text1, "[#", "#");
                    for (String s : Jarr)
                        node.setText(text1.replace("[#" + s + "#", ""));
                }
                if (text1 != null && text1.contains("[*")) {
                    //清除[**
                    Xarr = StringUtil.substringsBetween(text1, "[*", "*");
                    for (String s : Xarr)
                        node.setText(text1.replace("[*" + s + "*", ""));
                }
            }
            if (Jarr != null) {
                String s = "#";
                for (int g = 0; g < Jarr.length; g++) {
                    converList_JH(wpList, i, (Element) wpNode, Jarr[g], s);
                }
            }
            if (Xarr != null) {
                String s = "*";
                for (int g = 0; g < Xarr.length; g++) {
                    converList_XH(wpList, i, (Element) wpNode, Xarr[g], s);
                }
            }


        }
    }

    private void converList_JH(List wpNodeList, int i, Element wpNode, String value, String s) {
        Element beginEle = wpNode;
        if ("#".equals(s)) {
            while (beginEle != null && !"tr".equals(beginEle.getName())) {
                beginEle = beginEle.getParent();
            }
        }
        if ("*".equals(s)) {
            while (beginEle != null && !"p".equals(beginEle.getName())) {
                beginEle = beginEle.getParent();
            }
        }
        Element endEle = null;
        String valueTrim = StringUtil.substringBefore(StringUtil.removeInvisibleChar(value), "@").trim();
        String t = s + valueTrim + s + "]";
        for (int j = i; j < wpNodeList.size(); j++) {
            Node temp = (Node) wpNodeList.get(j);
            List wtlisttemp = temp.selectNodes(".//w:t");
            for (int k = 0; k < wtlisttemp.size(); k++) {
                Node node = (Node) wtlisttemp.get(k);
                String text1 = node.getText();
                if (text1 != null && StringUtil.removeInvisibleChar(text1).contains(t) && StringUtil.removeInvisibleChar(text1).contains(t)) {
                    String[] vArr = StringUtil.substringsBetween(text1, s, "]");
                    for (String str : vArr) {
                        String s1 = valueTrim + s;
                        if (s1.equals(StringUtil.removeInvisibleChar(str)))
                            node.setText(text1.replace(s + str + "]", ""));
                    }
                    endEle = (Element) temp; // wp标签
                }
            }
        }
        if (endEle == null) throw new SyntaxException(beginEle.getText() + "-----'" + value + "'未匹配到结束符");
        if ("#".equals(s)) {
            while (endEle != null && !"tr".equals(endEle.getName())) {
                endEle = endEle.getParent();
            }
        }
        if ("*".equals(s)) {
            while (endEle != null && !"p".equals(endEle.getName())) {
                endEle = endEle.getParent();
            }
        }
        HashMap<String, String> listAttMap = new HashMap<>();
        listAttMap.put("type", "list");
        listAttMap.put("content", " " + value + " ");
        HashMap<String, String> ifAttMap = new HashMap<>();
        ifAttMap.put("type", "if");
        ifAttMap.put("content", " (" + StringUtil.substringBefore(value, " ").trim() + ")??");
        String name = "#list";

        Element element = WordParserUtils.AddParentNode_JH(beginEle, endEle, name, listAttMap);
        WordParserUtils.AddParentNode(element, "#if", ifAttMap);
    }

    private void converList_XH(List wpNodeList, int i, Element BwpNode, String value, String s) {
        Element beginEle = BwpNode;
        if ("*".equals(s)) {
            while (beginEle != null && !"p".equals(beginEle.getName())) {
                beginEle = beginEle.getParent();
            }
        }
        Element eLEEle_wp = null;
        Element eLEEle = null;
        String valueTrim = StringUtil.substringBefore(StringUtil.removeInvisibleChar(value), "@").trim();
        String t = s + valueTrim + s + "]";
        //查询**]关闭
        for (int j = i; j < wpNodeList.size(); j++) {
            Node temp = (Node) wpNodeList.get(j);
            List wtlisttemp = temp.selectNodes(".//w:t");
            for (int k = 0; k < wtlisttemp.size(); k++) {
                Node node = (Node) wtlisttemp.get(k);
                String text1 = node.getText();
                if (text1 != null && StringUtil.removeInvisibleChar(text1).contains(t) && StringUtil.removeInvisibleChar(text1).contains(t)) {
                    String[] vArr = StringUtil.substringsBetween(text1, s, "]");
                    for (String str : vArr) {
                        String s1 = valueTrim + s;
                        if (s1.equals(StringUtil.removeInvisibleChar(str)))
                            node.setText(text1.replace(s + str + "]", ""));
                    }
                    eLEEle_wp = (Element) temp; // wp标签
                }
            }
        }

        if (eLEEle_wp == null) throw new SyntaxException(beginEle.getText() + "-----'" + value + "'未匹配到结束符");
        if ("*".equals(s)) {

            while (eLEEle_wp != null) {
                if (eLEEle_wp.getParent().equals(beginEle.getParent())) {
                    break;
                }
                eLEEle_wp = eLEEle_wp.getParent();

            }
//            if (eLEEle_wp.getParent() == null || !eLEEle_wp.getParent().equals(beginEle.getParent()) )
//                throw new RuntimeException("模板占位符格式不正确：-----"+beginEle.getText()+"-----部分的占位符起始符与结束符不同级");
        }
        Element parent = beginEle.getParent();
        Element parent1 = eLEEle_wp.getParent();
        boolean equals = parent1.equals(parent);
        HashMap<String, String> listAttMap = new HashMap<>();
        listAttMap.put("type", "list");
        listAttMap.put("content", " " + value + " ");
        HashMap<String, String> ifAttMap = new HashMap<>();
        ifAttMap.put("type", "if");
        ifAttMap.put("content", " (" + StringUtil.substringBefore(value, " ").trim() + ")??");
        String name = "#list";

        Element element = WordParserUtils.AddParentNode_XH(beginEle, eLEEle_wp, name, listAttMap);
        WordParserUtils.AddParentNode(element, "#if", ifAttMap);
    }


    /**
     * 添加 Relationship 关系
     *
     * @param relsroot
     * @param rId
     * @param drId
     */
    protected void addRelationship(Element relsroot, FileModel fileModel) {

        Element relationship = DocumentHelper.createElement(QName.get("Relationship", relsroot.getNamespace()));
        relationship.add(new DefaultAttribute("Id", fileModel.getImageRid()));
        relationship.add(new DefaultAttribute("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image"));
        // todo 处理名字问题
        relationship.add(new DefaultAttribute("Target", fileModel.getImageTarget()));
        relationship.remove(new DefaultAttribute("xmlns", ""));

        // Element relationship2 = new DefaultElement("Relationship");
        Element relationship2 = DocumentHelper.createElement(QName.get("Relationship", relsroot.getNamespace()));
        relationship2.add(new DefaultAttribute("Id", fileModel.getFileRid()));
        final FileTypeConst.FileType fileType = FileTypeConst.getFileType(fileModel.getFileTye());
        relationship2.add(new DefaultAttribute("Type", fileType.getRelsType()));
        // todo 处理名字问题
        relationship2.add(new DefaultAttribute("Target", fileModel.getFileTarget()));
        relationship2.remove(new DefaultAttribute("xmlns", ""));

        relsroot.add(relationship);
        relsroot.add(relationship2);
    }


    /**
     * w:object 节点的创建.
     *
     * @param rId /图片数据关联
     * @param drId // 文件数据关联
     * @return
     */
    protected Element createWObjectNode(FileModel fileModel) {
        final DefaultElement wObject = new DefaultElement("w:object");
        wObject.add(getElement(fileModel));
        wObject.add(getShape(fileModel));
        wObject.add(createOLEObject(fileModel));
        return wObject;
    }


    protected int generateRid(Element relsRoot) {
        final List elements = relsRoot.elements();
        int count = elements.size() + 1;
        String rId = "rId" + count;
        while (true) {
            final Node singleNode = relsRoot.selectSingleNode(".//Relationship[@Id='" + rId + "']");
            if (singleNode != null) {
                // 说明已存在 需要重新选择
                count += 1;
                rId = "rId" + count;

            } else {
                break;
            }
        }
        return count;
    }

    private Element createOLEObject(FileModel fileModel) {
        // o:OLEObject Type="Embed" ProgID="Word.Document.12" ShapeID="_x0000_i1011251" DrawAspect="Icon" ObjectID="_1677332640" r:id="rId7"
        final DefaultElement oleObject = new DefaultElement("o:OLEObject");
        oleObject.add(new DefaultAttribute("Type", "Embed"));
        oleObject.add(new DefaultAttribute("ProgID", "Word.Document.12"));
        oleObject.add(new DefaultAttribute("ShapeID", fileModel.getShapeId()));
        oleObject.add(new DefaultAttribute("DrawAspect", "Icon"));
        oleObject.add(new DefaultAttribute("ObjectID", "_1677332640"));
        oleObject.add(new DefaultAttribute("r:id", fileModel.getFileRid()));
        //<o:FieldCodes>\s</o:FieldCodes>
        final DefaultElement fieldCodes = new DefaultElement("o:FieldCodes");
        fieldCodes.setText("\\s");
        oleObject.add(fieldCodes);
        return oleObject;
    }

    private Element getShape(FileModel fileModel) {

        // v:shape id="_x0000_i1011251" type="#_x0000_t75" style="width:74.1pt;height:49.8pt" o:ole=""
        final DefaultElement shape = new DefaultElement("v:shape");
        shape.add(new DefaultAttribute("id", fileModel.getShapeId()));
        shape.add(new DefaultAttribute("type", "#" + fileModel.getShapeTypeId()));
        shape.add(new DefaultAttribute("style", "width:74.1pt;height:49.8pt"));
        shape.add(new DefaultAttribute("o:ole", ""));

        // v:imagedata r:id="rId6" o:title=""
        final DefaultElement imagedata = new DefaultElement("v:imagedata");
        imagedata.add(new DefaultAttribute("r:id", fileModel.getImageRid()));
        imagedata.add(new DefaultAttribute("o:title", ""));
        shape.add(imagedata);
        return shape;
    }

    private Element getElement(FileModel fileModel) {
        String shapeTypeId = fileModel.getShapeTypeId();
        Element shapetype = new DefaultElement("v:shapetype");
        // id="_x0000_t75" coordsize="21600,21600" o:spt="75" o:preferrelative="t" path="m@4@5l@4@11@9@11@9@5xe" filled="f" stroked="f"
        shapetype.add(new DefaultAttribute("id", shapeTypeId));
        shapetype.add(new DefaultAttribute("coordsize", "21600,21600"));
        shapetype.add(new DefaultAttribute("o:spt", "75"));
        shapetype.add(new DefaultAttribute("o:preferrelative", "t"));
        shapetype.add(new DefaultAttribute("path", "m@4@5l@4@11@9@11@9@5xe"));
        shapetype.add(new DefaultAttribute("filled", "f"));
        shapetype.add(new DefaultAttribute("stroked", "f"));

        final DefaultElement stroke = new DefaultElement("v:stroke");
        stroke.add(new DefaultAttribute("joinstyle", "miter"));
        shapetype.add(stroke);

        final DefaultElement formulas = new DefaultElement("v:formulas");
        final Map<String, String> vfEqnMap = DataContext.getVfEqnMap();
        final int size = vfEqnMap.size();
        for (int i = 1; i <= size; i++) {
            final DefaultElement f = new DefaultElement("v:f");
            f.add(new DefaultAttribute("eqn", vfEqnMap.get("eqn" + i)));
            formulas.add(f);
        }
        shapetype.add(formulas);

        final DefaultElement path = new DefaultElement("v:path");
        // o:extrusionok="f" gradientshapeok="t" o:connecttype="rect"
        path.add(new DefaultAttribute("o:extrusionok", "f"));
        path.add(new DefaultAttribute("gradientshapeok", "t"));
        path.add(new DefaultAttribute("o:connecttype", "rect"));
        shapetype.add(path);

        final DefaultElement lock = new DefaultElement("o:lock");
        // v:ext="edit" aspectratio="t"
        lock.add(new DefaultAttribute("v:ext", "edit"));
        lock.add(new DefaultAttribute("aspectratio", "t"));
        shapetype.add(lock);
        return shapetype;
    }


}
