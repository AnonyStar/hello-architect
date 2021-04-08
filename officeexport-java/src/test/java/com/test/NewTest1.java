package com.test;

import com.export.WordExportProducer;
import com.export.model.FileModel;
import com.export.model.SingleDataModel;
import com.kmood.utils.FileUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.awt.image.BufferedImageGraphicsConfig;
import sun.awt.shell.ShellFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhoucx
 * @time: 2021/3/16 17:14
 */
public class NewTest1 {

    @Test
    public void test1() throws Exception {
        WordExportProducer producer = new WordExportProducer();
        String xmlModePath = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\model";
        String modeName = "包装说明表（范例B）.xml";
        final WordExportProducer complie = producer.complie(xmlModePath, modeName, true);
        Map<String, Object> map = new HashMap<>();
        String path = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\model\\oleObject1.bin";
        final String base64String = Base64.encodeBase64String(FileUtils.readToBytesByFilepath(path));
        final BufferedImage imageIcon = getImageIcon(path);

        //输出流
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(imageIcon, "PNG", stream);
        final String string = Base64.encodeBase64String(stream.toByteArray());
        // map.put("code_image", string);
        // map.put("code_file", base64String);
        map.put("file01_image", string);
        map.put("file01_file", base64String);
        //complie.produce(map, new OutputStreamWriter(new FileOutputStream(xmlModePath + File.separator + "测试新文件.xml")));
        SingleDataModel model = new SingleDataModel();
        model.putFileData("file", new FileModel(new File(path)));
        //model.putFileData("code", new FileModel(new File(path)));
        complie.produce(model, new OutputStreamWriter(new FileOutputStream(xmlModePath + File.separator + "测试新文件.xml")));
    }

    @Test
    public void test2() throws IOException {
        String path1 = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\model\\包装说明表（范例B）.docx";

        final BufferedImage image = getImageIcon(path1);
        String path = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\model\\测试图片.png";
        ImageIO.write(image, "png", new FileOutputStream(path));// 保存图片 JPEG表示保存格式
    }

    public BufferedImage generateImage() throws IOException {
        int width = 180; // 图片宽
        int height = 120;// 图片高
        String titleStr = "公司测试点";

        // 得到图片缓冲区
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// INT精确度达到一定,RGB三原色，高度70,宽度150

        // 得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setColor(Color.WHITE); // 设置背景颜色
        g2.fillRect(0, 0, width, height);// 填充整张图片(其实就是设置背景颜色)
        g2.setColor(Color.black);// 设置字体颜色
        g2.drawRect(1, 1, width - 2, height - 2); // 画边框就是黑边框

        // 设置标题的字体,字号,大小
        Font titleFont = new Font("宋体", Font.BOLD, 15);
        g2.setFont(titleFont);
        String markNameStr = titleStr;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 抗锯齿
        // 计算文字长度,计算居中的X点坐标
        FontMetrics fm = g2.getFontMetrics(titleFont);
        int titleWidth = fm.stringWidth(markNameStr);
        int titleWidthX = (width - titleWidth) / 2 - 35;// 感觉不居中,向左移动35个单位
        g2.drawString(markNameStr + "节能账单", titleWidthX, 110);
        String path1 = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\model\\包装说明表（范例B）.docx";
        final Icon bigIcon = getBigIcon(new File(path1));

        //g2.drawImage()

        g2.dispose(); // 释放对象
        String path = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\model\\测试图片.png";
        ImageIO.write(bi, "png", new FileOutputStream(path));// 保存图片 JPEG表示保存格式
        return bi;
    }

    /**
     * 获取大图标
     *
     * @param f
     * @return
     */
    private static Icon getBigIcon(File f) {
        if (f != null && f.exists()) {
            try {
                ShellFolder sf = ShellFolder.getShellFolder(f);
                return new ImageIcon(sf.getIcon(true));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


    public BufferedImage getImageIcon(String ImgName) {
        String str1 = null;
        String str2 = null;
        try {
            File file = new File(ImgName);
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
            return image;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void saveImage(BufferedImage img, OutputStream out1) throws Exception {
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out1);
        encoder.encode(img);
    }

}