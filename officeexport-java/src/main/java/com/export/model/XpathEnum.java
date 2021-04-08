package com.export.model;

/**
 * @author: zhoucx
 * @time: 2021/3/17 15:19
 */
public enum XpathEnum {

    WORD_DOCUMENT_PKG_XMLDATA(".//pkg:part[@pkg:name='/word/document.xml']/pkg:xmlData"),
    WORD_DOCUMENT_RELS_PKG_XMLDATA(".//pkg:part[@pkg:name='/word/_rels/document.xml.rels']/pkg:xmlData"),
    PKG_PACKAGE(".//pkg:package"),
    W_T(".//w:t"),
    W_P(".//w:p")
    ;


    private String xpath;

    XpathEnum(String xpath) {
        this.xpath = xpath;
    }

    public String getXpath() {
        return xpath;
    }
}
