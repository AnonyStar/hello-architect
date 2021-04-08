package com.test.word;

import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author: zhoucx
 * @time: 2021/3/10 17:09
 */
public class Doc4jTest1 {


    @Test
    public void test1() throws Docx4JException, FileNotFoundException {
        String path = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template - 副本.docx";
        String path1 = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template.docx";
        String inputfilepath = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template - 副本.docx";
        boolean nestLists = true;

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(new File(inputfilepath));

        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();

        htmlSettings.setImageDirPath(inputfilepath + "_files");
        htmlSettings.setImageTargetUri(inputfilepath.substring(inputfilepath.lastIndexOf("/") + 1) + "_files");
        htmlSettings.setWmlPackage(wordMLPackage);

        String userCSS = null;
        if (nestLists) {
            userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  table, caption, tbody, tfoot, thead, tr, th, td "
                    + "{ margin: 0; padding: 0; border: 0;}" + "body {line-height: 1;} ";
        } else {
            userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  ol, ul, li, table, caption, tbody, tfoot, thead, tr, th, td "
                    + "{ margin: 0; padding: 0; border: 0;}" + "body {line-height: 1;} ";

        }
        htmlSettings.setUserCSS(userCSS);

        Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

        Docx4J.toHTML(htmlSettings, new FileOutputStream(new File("G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template - 副本.html")),
                Docx4J.FLAG_EXPORT_PREFER_XSL);

        if (wordMLPackage.getMainDocumentPart().getFontTablePart() != null) {
            wordMLPackage.getMainDocumentPart().getFontTablePart().deleteEmbeddedFontTempFiles();
        }
        htmlSettings = null;
        wordMLPackage = null;
    }
}
