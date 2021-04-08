package com.export.core;

import com.export.model.DataModel;
import com.export.model.SingleDataModel;
import freemarker.template.TemplateException;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.io.Writer;

/**
 * @author: zhoucx
 * @time: 2021/3/16 15:56
 */
public interface TemplateHandler {

    void process(String xmlPath,String ftlOutDirPath) throws DocumentException, IOException;

    void process(String xmlPath) throws DocumentException, IOException;

    void produce(SingleDataModel model, Writer writer) throws IOException, TemplateException;

    String getModelDirPath();
    String getModelName();
    void setModelDirPath(String dirPathLocal);
    void setModelName(String nameLocal);


}
