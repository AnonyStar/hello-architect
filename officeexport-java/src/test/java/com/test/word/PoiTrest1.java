package com.test.word;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Includes;
import com.deepoove.poi.data.Pictures;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.OleObjectBinaryPart;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author: zhoucx
 * @time: 2021/3/10 14:59
 */
public class PoiTrest1 {

    @Test
    public void test1() throws IOException {
        List<SegmentData> subData = new ArrayList<SegmentData>();
        SegmentData s1 = new SegmentData();
        s1.setTitle("Monday");
        s1.setContent("Two parallel lines will intersect one day.");
        subData.add(s1);

        SegmentData s2 = new SegmentData();
        s2.setTitle("Friday");
        s2.setContent("Confidence is not necessarily successful.");
        subData.add(s2);
        XWPFTemplate template = XWPFTemplate.compile("G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template.docx").render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                    put("imag", Pictures.ofLocal("G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template - 副本.emf").create());
                }});



        template.writeAndClose(new FileOutputStream("G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\output.docx"));
    }

    @Test
    public void test2() {
        WordprocessingMLPackage wordprocessingMLPackage;
        String path = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template - 副本.docx";
        String path1 = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template.docx";
        try {
            wordprocessingMLPackage = WordprocessingMLPackage
                    .load(new File(path));
/*            wordprocessingMLPackage.getMainDocumentPart().addParagraphOfText("Hello Word!");
            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "Hello Word!");
            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle", " a subtitle!");*/
//            wordprocessingMLPackage.getMainDocumentPart().addObject();
            OleObjectBinaryPart binaryPart = new OleObjectBinaryPart();
            binaryPart.setBinaryData(new FileInputStream(new File(path1)));
            wordprocessingMLPackage.getMainDocumentPart().addObject(binaryPart);
            wordprocessingMLPackage.save(new File(path));
        } catch (Docx4JException | FileNotFoundException e) {
          e.printStackTrace();
        }
    }

    private  P addInlineImageToParagraph(org.docx4j.dml.wordprocessingDrawing.Inline inline) {
        // 添加内联对象到一个段落中
        ObjectFactory factory = new ObjectFactory();
        P paragraph = factory.createP();
        R run = factory.createR();
        paragraph.getContent().add(run);
        Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return paragraph;
    }
}
