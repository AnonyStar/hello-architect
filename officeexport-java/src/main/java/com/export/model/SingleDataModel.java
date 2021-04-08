package com.export.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: zhoucx
 * @time: 2021/3/19 10:26
 */
public class SingleDataModel implements DataModel{

    private Map<String,Object> baseData;


    private Map<String, FileModel> fileData;


    public SingleDataModel() {
        this.baseData = new HashMap<>();
        this.fileData = new HashMap<>();
    }



    public void putBaseData(String key, Object value) {
        baseData.put(key, fileData);
    }


    public void putFileData(String key, FileModel model) {
        fileData.put(key, model);
    }

    public Map<String, FileModel> getFileData() {
        return fileData;
    }

    public Set<String>  getFileKeys() {
        return fileData.keySet();
    }

    public Map<String, Object> getBaseData() {
        return baseData;
    }
}
