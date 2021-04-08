package com.export;

import com.export.core.TemplateConfigFactory;
import com.export.core.TemplateHandler;
import com.export.core.WordTemplateHandler;
import com.export.model.DataModel;
import com.export.model.SingleDataModel;
import com.kmood.datahandle.DataConverter;
import com.kmood.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 *
 * @author: zhoucx
 * @time: 2021/3/16 14:38
 */
@Slf4j
public class WordExportProducer {

    private TemplateHandler templateHandler = new WordTemplateHandler();

    public WordExportProducer() {
        //todo 默认给 modelDirPath  从配置中获取
        templateHandler.setModelDirPath("");

    }

    public WordExportProducer(String modelDirPath) throws Exception {
        TemplateConfigFactory.addModelDirPath(modelDirPath);
        templateHandler.setModelDirPath(modelDirPath);
    }



    public WordExportProducer complie(String xmlModelName) throws Exception {
        return this.complie(xmlModelName, true);
    }

    public WordExportProducer complie(String xmlModelName, boolean generateModel) throws Exception {
        if(StringUtil.isBlank(templateHandler.getModelDirPath())) {
            log.error("template address does not exist......");
            throw new RuntimeException("模板地址不存在，请配置模板地址！");
        }
        return this.complie(templateHandler.getModelDirPath(), xmlModelName, generateModel);
    }

    public WordExportProducer complie(String xmlModelPath,String xmlModelName,boolean generateModel) throws Exception {
        if(StringUtil.isBlank(templateHandler.getModelDirPath())){
            TemplateConfigFactory.addModelDirPath(xmlModelPath);
            templateHandler.setModelDirPath(xmlModelPath);
        }
        if (generateModel){
            templateHandler.process(xmlModelPath+ File.separator+xmlModelName);
        }
        templateHandler.setModelName(xmlModelName+".ftl");
        final String dir = xmlModelPath + File.separator + xmlModelName + ".ftl";
        log.info("generate template path :[{}]", dir);
        return this;
    }


    public void produce(Map data, String outFilePath)throws IOException, TemplateException {
        Configuration configuration = TemplateConfigFactory.getConfiguration();
        Template template = configuration.getTemplate(templateHandler.getModelName());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(outFilePath), template.getEncoding());
        Object dataConvert = DataConverter.convert(data, null);
        template.process(dataConvert,outputStreamWriter);
    }

    public void produce(Map data, Writer writer) throws IOException, TemplateException {
        Configuration configuration = TemplateConfigFactory.getConfiguration();
        Template template = configuration.getTemplate(templateHandler.getModelName());
        Object dataConvert = DataConverter.convert(data, null);
        template.process(dataConvert,writer);
    }

    public void produce(SingleDataModel model, Writer writer) throws IOException, TemplateException {
        templateHandler.produce(model, writer);
    }



}
