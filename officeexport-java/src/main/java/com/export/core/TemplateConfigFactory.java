package com.export.core;

import com.kmood.utils.StringUtil;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * FREEMAKER 模板引擎配置初始.
 * <P>
 *     全局唯一，配置为全局设置
 * </P>
 * @author: zhoucx
 * @time: 2021/3/16 15:10
 */
public class TemplateConfigFactory {

    private static Version FMVersion = Configuration.VERSION_2_3_28;
    private static final TemplateExceptionHandler FM_EXCEPTION_HANDLER = TemplateExceptionHandler.RETHROW_HANDLER;
    private static final String FM_ENCODING = "UTF-8";
    private static volatile List<String> ModelDirPathArr = new CopyOnWriteArrayList<>();

    private TemplateConfigFactory(){}

    private static class Singleton {
        private static  Configuration instance;
        static {
            instance = new Configuration(FMVersion);
            instance.setTemplateExceptionHandler(FM_EXCEPTION_HANDLER);
            instance.setDefaultEncoding(FM_ENCODING);
        }
        public static Configuration getInstance() {
            return instance;
        }
    }

    public static Configuration getConfiguration() {
        return Singleton.getInstance();
    }

    public static void init() {
        getConfiguration();
    }



    public static Configuration addModelDirPath(String modelDirPath) throws Exception{
        if (StringUtil.isBlank(modelDirPath)) return getConfiguration();

        modelDirPath = modelDirPath.replace("\\",File.separator);
        modelDirPath = modelDirPath.replace("/",File.separator);

        if (ModelDirPathArr.contains(modelDirPath)) return getConfiguration();

        TemplateLoader tl = getConfiguration().getTemplateLoader();
        if (tl == null || tl instanceof MultiTemplateLoader) {
            ModelDirPathArr.add(modelDirPath);
            TemplateLoader[] loaders = new TemplateLoader[ModelDirPathArr.size()];
            for (int i = 0; i < ModelDirPathArr.size(); i++) {
                loaders[i] = new FileTemplateLoader(new File(ModelDirPathArr.get(i)));
            }
            MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
            getConfiguration().setTemplateLoader(mtl);
        }else{
            throw new RuntimeException("FREEMAKER 配置对应的模板加载器类型不一致，若使用自定义配置，请调整模板位置！无需调用方法！");
        }
        return getConfiguration();
    }


}
