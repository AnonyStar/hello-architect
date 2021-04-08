package com.test.word;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.util.ByteUtils;
import com.kmood.utils.FileUtils;
import com.test.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.ZipPackagePart;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.apache.poi.ss.extractor.EmbeddedData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/**
 * @author: zhoucx
 * @time: 2021/3/11 10:31
 */
public class PoiTlTest1 {


    @Test
    public void test1() throws IOException, OpenXML4JException {
        final byte[] bytes = ByteUtils.toByteArray(new FileInputStream(new File("G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template - 副本.docx")));
        EmbeddedData data = new EmbeddedData("测试文档完美.docx",bytes, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        XWPFTemplate template = XWPFTemplate.compile("G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\ememplate.docx").render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                    put("file", data);
                }});
        final PictureRenderData pictureRenderData1 = Pictures.ofBase64("", PictureType.DIB).create();
        final PictureRenderData pictureRenderData = Pictures.ofLocal("").create();
        template.writeAndClose(new FileOutputStream("output.docx"));

    }


    @Test
    public void test2() throws IOException, OpenXML4JException {
        final FileInputStream stream = new FileInputStream(new File("G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template - 副本.docx"));

        XWPFDocument document = new XWPFDocument(stream);
        final List<PackagePart> allEmbeddedParts = document.getAllEmbeddedParts();
        final PackagePart packagePart = document.getPackagePart();
        final List<XWPFParagraph> paragraphs = document.getParagraphs();
        paragraphs.forEach(p -> {
            final POIXMLDocumentPart part = p.getPart();
            final OPCPackage aPackage = part.getPackagePart().getPackage();
           // ZipPackagePart packagePart1 = new ZipPackagePart(aPackage,)
            final OutputStream outputStream = part.getPackagePart().getOutputStream();


            final XWPFRun xwpfRun = p.getRuns().get(0);
           // xwpfRun.setText();
        });
        allEmbeddedParts.forEach(p -> {
            final String contentType = p.getContentType();
            final PackagePartName partName = p.getPartName();
            final OPCPackage aPackage = p.getPackage();
            final ContentType details = p.getContentTypeDetails();
            try {
                final PackageRelationshipCollection relationships = p.getRelationships();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        });

        document.write(new FileOutputStream(new File("G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template - 副本 - 附件.docx")));

    }

    @Test
    public void test3() throws IOException {
        String path = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\测试文档--图片.docx";
        String path1 = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template.xml";
        final byte[] bytes = IOUtils.toByteArray(new FileInputStream(path));
        System.out.println(Base64.decodeInteger(bytes));
        final String string = Base64.encodeBase64String(bytes);
        OutputStream outputStream = new FileOutputStream(path1);
        final String string1 = java.util.Base64.getEncoder().encodeToString(FileUtils.readToBytesByFilepath(path));
       // final String encode1 = sun.misc.BASE64Encoder.encode();
        final String encode = BASE64Encoder.encode(bytes);
        outputStream.write(string.getBytes(StandardCharsets.UTF_8));
        System.out.println(Base64.encodeBase64String(bytes));
    }
}
