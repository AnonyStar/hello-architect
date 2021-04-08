package com.test;

import com.kmood.word.WordParserUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

/**
 * @author: zhoucx
 * @time: 2021/3/12 10:04
 */
public class Doma4jTest1 {


    @Test
    public void test2() throws DocumentException, URISyntaxException, IOException {
        String actualModelPath = this.getClass().getClassLoader().getResource("./model/").toURI().getPath();
        String xmlModelName = "测试.xml";
        SAXReader reader = new SAXReader();
        Document document = reader.read(actualModelPath + xmlModelName);
        WordParserUtils.parseAttachmentNode(document);
       // document.write(new OutputStreamWriter(new FileOutputStream(actualModelPath + "测试-1.xml")));
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(
                new FileOutputStream(actualModelPath + "测试-1.xml"), "UTF-8"));
        writer.write(document);
        writer.close();
    }


    @Test
    public void test1() throws URISyntaxException, DocumentException {
        String actualModelPath = this.getClass().getClassLoader().getResource("./model/").toURI().getPath();
        String xmlModelName = "包装说明表（范例B）.xml";
        SAXReader reader = new SAXReader();
        Document document = reader.read(actualModelPath + xmlModelName);
        Element rootElement = document.getRootElement();
        final List list = rootElement.selectNodes("pkg:part");
        final List list1 = rootElement.selectNodes("pkg:part[@pkg:name='/word/document.xml']/pkg:xmlData");
        final Element docNote = WordParserUtils.getDocNote(document);
        list1.forEach( e -> {
            final Element e1 = (Element) e;
            final QName qName = e1.getQName();
            final Iterator iterator = e1.elementIterator();
            while (iterator.hasNext()) {
                final Element next = (Element) iterator.next();
                final String name = next.getName();
                final QName qName1 = next.getQName();

            }
        });
        final Iterator iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            final Element next = (Element) iterator.next();

        }
    }
}
