package com.export.model;

import com.export.exception.ExportWordException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * document 的结构化
 * @author: zhoucx
 * @time: 2021/3/17 14:30
 */
@Slf4j
@Data
public class XmlModel {

    private Document document;

    private Element packageNode;

    private Element documentPart;

    private Element docRelspart;

    private String fileName;

    private String ftlOutPath;

    public XmlModel(File xmlFile) {
        final SAXReader reader = new SAXReader();
        this.fileName = xmlFile.getName();
        try {
            this.document = reader.read(xmlFile);
            this.packageNode = getSingleNote4Root(XpathEnum.PKG_PACKAGE);
            this.documentPart =getDocumentPart();
            this.docRelspart = getSingleNote4Root(XpathEnum.WORD_DOCUMENT_RELS_PKG_XMLDATA);
        } catch (DocumentException e) {
            //log.error("{} :xml file parse erro...... ", fileName);
            throw new ExportWordException("Xml 解析失败.....");
        }
    }

    public XmlModel(String xmlPath) {
        this(new File(xmlPath));
    }


    public Element getDocumentPart() {
        final Element element = getSingleNote4Root(XpathEnum.WORD_DOCUMENT_PKG_XMLDATA);
        return element.element("document");

    }

    public Element getSingleNote4Root(XpathEnum xpathEnum) {
        return (Element) this.document.selectSingleNode(xpathEnum.getXpath());
    }
    public List<Element> getNoteList4Root( XpathEnum xpathEnum) {
        return this.document.selectNodes(xpathEnum.getXpath());
    }

    public Element getSingleNote4Element(Element element, XpathEnum xpathEnum) {
        return (Element) element.selectSingleNode(xpathEnum.getXpath());
    }

    public List<Element> getNoteList4Element(Element element, XpathEnum xpathEnum) {
        return element.selectNodes(xpathEnum.getXpath());
    }


}
