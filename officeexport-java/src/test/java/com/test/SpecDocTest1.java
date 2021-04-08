package com.test;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.OleLinkType;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.DocOleObject;
import com.spire.doc.fields.DocPicture;
import org.apache.commons.io.FileUtils;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.contenttype.ContentTypes;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.parts.WordprocessingML.OleObjectBinaryPart;
import org.docx4j.org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.docx4j.relationships.Relationship;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: zhoucx
 * @time: 2021/3/22 15:04
 */
public class SpecDocTest1 {
    String xmlModePath = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\model";
    String modeName = "包装说明表（范例B）.docx";
    String path = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\model\\包装说明表（范例B） - 1.zip";


    public void test() throws IOException, Docx4JException {
        String inputfilepath = "";
        // 1. Load the Package
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(inputfilepath));

        // 2. Fetch the OLE document part
        OleObjectBinaryPart olePart = (OleObjectBinaryPart)wordMLPackage.getParts().get(new PartName("/word/embeddings/oleObject1.bin") );

        // 3. Write it
        File f=new File("/home/dev/ole.bin");
        FileOutputStream out=new FileOutputStream(f);
        olePart.writeDataToOutputStream(out);
        out.close();
    }


    public Part createOleBinPart(String filename) throws Exception
    {
// PartName partName = new PartName("embeddings/oleobject1.bin");
        Part olePart = new OleObjectBinaryPart();

        FileInputStream fi = new FileInputStream("d:\\temp\\mydoc.docx");
        ((BinaryPart)olePart).setBinaryData(fi);
// ((OleObjectBinaryPart)olePart).initPOIFSFileSystem();
// ((OleObjectBinaryPart)olePart).writePOIFSFileSystem();

        return olePart;
    }
    public void test2() throws Docx4JException, FileNotFoundException {
        OleObjectBinaryPart oleObj = new OleObjectBinaryPart();
        File file = new File("C:/ERD.VDX" );
        java.io.InputStream is = new java.io.FileInputStream(file);
        oleObj.setBinaryData(is);


        String outputfilepath = "C:/OLE.docx";

        // 1. Load the Package
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
        wordMLPackage.getContentTypeManager().addDefaultContentType("bin", ContentTypes.OFFICEDOCUMENT_OLE_OBJECT);
        Relationship relOleObject = wordMLPackage.getMainDocumentPart().addTargetPart(oleObj);
        wordMLPackage.save(new java.io.File(outputfilepath));
        System.out.println( "Saved output to:" + outputfilepath );
    }


    public void test3() throws InvalidFormatException {
        boolean OLE_LINK_ONLY = false;

        ////////////////////////////////////////////////////////
        // For this sample, create a docx package using docx4j

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();


        ////////////////////////////////////////////////////////
        // OLE config
//
//        EmbeddingType embeddingType = EmbeddingType.TXT; // CSV is a text format
//        byte[] contentBytes = FileUtils.readFileToByteArray(new File(System.getProperty("user.dir")+"/Hi.VDX" ));
//
//        // the image on the document surface
//        byte[] imageBytes = FileUtils.readFileToByteArray(new File(System.getProperty("user.dir")+"/greentick.png" ));
//
//        // if you know your image mime type, you can avoid image introspection (which avoids temp file creation)
//        String mime = "image/png"; // .. otherwise, set this to null
//
//        // position in docx content list
//        int index = -1; // place at end
//
//        // Both link and embed require this
//        String filepath = "C:\\Users\\jharrop\\Documents\\Hi.VDX";     // typically the original file location
//        String caption = "Hi.VDX"; // can be anything
//        String command ="C:\\Users\\jharrop\\AppData\\Local\\Temp\\Hi.VDX"; // a temp file location
//
//        // For link only
//        String targetURL = "file:///" + command;
//
//
//        ////////////////////////////////////////////////////////
//        // Perform OLE
//
//        OleHelperDocx OleHelperDocx = new OleHelperDocx(wordMLPackage);
//        if (OLE_LINK_ONLY) {
//
//            OleHelperDocx.link(index, embeddingType, caption, filepath, command, targetURL, imageBytes);
//
//            // Save the resulting docx
//            wordMLPackage.save(new File(System.getProperty("user.dir")
//                    + "/OUT_MSG_linked.docx"));
//
//        } else {
//
//            OleHelperDocx.embed(index, embeddingType,
//                    caption, filepath, command,
//                    contentBytes, imageBytes);
//
//            // Save the resulting docx
//            wordMLPackage.save(new File(System.getProperty("user.dir")
//                    + "/OUT_MSG_embedded.docx"));
//
//        }
    }
    @Test
    public void test1() throws InvalidFormatException {
//创建文档
        Document doc = new Document();

        //添加段落
        Section sec = doc.addSection();
        Paragraph paragraph = sec.addParagraph();

        //加载图片，并添加ole对象，链接到Word文档
        DocPicture picture = new DocPicture(doc);
        picture.loadImage(xmlModePath + File.separator +"1.jpg");
        DocOleObject ole = paragraph.appendOleObject(path,picture, OleLinkType.Link);

        //保存文档
        doc.saveToFile(xmlModePath + File.separator + modeName, FileFormat.Docx_2013);
        doc.dispose();

        OleObjectBinaryPart part = new OleObjectBinaryPart();

    }
}
