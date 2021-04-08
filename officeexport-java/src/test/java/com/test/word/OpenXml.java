package com.test.word;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author: zhoucx
 * @time: 2021/3/10 22:27
 */
public class OpenXml {

    @Test
    public void test1() throws IOException, OpenXML4JException, XmlException {
        XWPFWordExtractor docx = new XWPFWordExtractor(POIXMLDocument.openPackage("G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template - 副本.docx"));
//提取.docx正文文本
        String text = docx.getText();
        System.out.println("解析DOCX格式的word文档！"+text);

    }
}
