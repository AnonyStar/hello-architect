package com.export.core;

import com.export.model.XmlModel;
import com.export.parse.MergePlaceHolderParser;
import com.export.utils.FileUtils;
import com.export.utils.PlaceholderUtils;
import com.export.utils.StringUtil;
import com.kmood.word.WordParserUtils;
import freemarker.template.Configuration;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author: zhoucx
 * @time: 2021/3/16 16:07
 */
public class WordTemplateHandler extends AbstractTemplateHandler{

    //private WordTemplateHandler(){}


   /* public static WordTemplateHandler getInstance() {
        return Singleton.getInstance();
    }*/


    @Override
    protected void xmlPlaceHolderHandler(String xmlFtlPath) throws IOException {
        FileOutputStream out = null;
        try {
            Configuration configuration = TemplateConfigFactory.getConfiguration();
            String xmModelStr = new String(FileUtils.read2BytesByFilepath(xmlFtlPath),configuration.getDefaultEncoding());
            String body = StringUtil.substringBetween(xmModelStr, "<w:body>", "</w:body>");
            body = PlaceholderUtils.IfTagHandle(body);
            body = PlaceholderUtils.ListTagHandle(body);
            body = PlaceholderUtils.BraceTagHandle(body);
            body = PlaceholderUtils.markToEscape(body);
            out = new FileOutputStream(xmlFtlPath);
            xmModelStr = xmModelStr.substring(0,xmModelStr.indexOf("<w:body>"))+"<w:body>"+body+"</w:body>"+xmModelStr.substring(xmModelStr.lastIndexOf("</w:body>")+9);

            out.write(xmModelStr.getBytes(configuration.getDefaultEncoding()));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null){
                out.close();
            }
        }
    }


    private static class Singleton {
        private static WordTemplateHandler instance;
        static {
            instance = new WordTemplateHandler();
        }
        public static WordTemplateHandler getInstance() {
            return instance;
        }
    }
}
