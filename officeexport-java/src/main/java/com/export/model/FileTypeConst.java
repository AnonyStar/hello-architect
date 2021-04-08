package com.export.model;

import com.export.utils.StringUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhoucx
 * @time: 2021/3/19 15:36
 */
public class FileTypeConst {

    private static Map<String,FileType> fileTypeMap = new HashMap<>();
    static {
        fileTypeMap.put("xlsx",FileType.FILE_XLSX);
        fileTypeMap.put("docx",FileType.FILE_DOCX);
        fileTypeMap.put("pptx",FileType.FILE_PPTX);
        fileTypeMap.put("xls",FileType.FILE_XLS);
        fileTypeMap.put("doc",FileType.FILE_DOC);
        fileTypeMap.put("ppt",FileType.FILE_PPT);
    }


    public static FileType getFileType(String type) {
        return fileTypeMap.get(type) == null ? FileType.FILE_BIN :fileTypeMap.get(type);
    }

    /**
     * 获取不带 . 的后缀正确的
     * @param type
     * @return
     */
    public static String getFileSuffix(String type) {
        return fileTypeMap.get(type) == null ? "bin" :type;
    }

    public   enum FileType {
        FILE_BIN("application/vnd.openxmlformats-officedocument.oleObject","http://schemas.openxmlformats.org/officeDocument/2006/relationships/oleObject"),
        FILE_DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document","http://schemas.openxmlformats.org/officeDocument/2006/relationships/package"),
        FILE_XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","http://schemas.openxmlformats.org/officeDocument/2006/relationships/package"),
        FILE_PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation","http://schemas.openxmlformats.org/officeDocument/2006/relationships/package"),
        FILE_XLS("application/vnd.ms-excel","http://schemas.openxmlformats.org/officeDocument/2006/relationships/oleObject"),
        FILE_DOC("application/msword","http://schemas.openxmlformats.org/officeDocument/2006/relationships/oleObject"),
        FILE_PPT("application/vnd.ms-powerpoint","http://schemas.openxmlformats.org/officeDocument/2006/relationships/oleObject");

        private String contextType;
        private String relsType;

        FileType(String contextType, String relsType) {
            this.contextType = contextType;
            this.relsType = relsType;
        }

        public String getContextType() {
            return contextType;
        }

        public String getRelsType() {
            return relsType;
        }
    }
}
