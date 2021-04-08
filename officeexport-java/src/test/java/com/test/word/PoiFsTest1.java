package com.test.word;

import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author: zhoucx
 * @time: 2021/3/10 20:18
 */
public class PoiFsTest1 {


    @Test
    public void test() throws IOException {
        String path = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template - 副本.doc";
        String path1 = "G:\\IdeaProjects\\hello-architect\\officeexport-java\\src\\test\\resources\\word\\template.docx";
        POIFSFileSystem fileSystem = new POIFSFileSystem(new File(path));
        final DirectoryNode root = fileSystem.getRoot();
        System.out.println(root.getEntries());
    }
}
