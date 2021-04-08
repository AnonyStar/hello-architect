package com.export.model;

import lombok.Data;

import java.io.File;

/**
 * @author: zhoucx
 * @time: 2021/3/19 10:38
 */
@Data
public class FileModel {

    public FileModel(File file) {
        this.file = file;
        this.fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
              this.fileTye = fileName.substring(fileName.lastIndexOf(".")+1);

    }

    private String key;


    private File file;

    private String fileTye;


    private String fileName;


    private String imageRid;


    private String fileRid;

    private String shapeId;

    private String shapeTypeId;

    private String imageTarget;


    private String fileTarget;


    private String imageBinaryDataPlace;

    private String fileBinaryDataPlace;


}
