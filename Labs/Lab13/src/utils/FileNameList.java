package utils;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;

public class FileNameList {

    public String fileNames[];

    protected class FileExtensionFilter implements FilenameFilter {

        String extension = null;

        public FileExtensionFilter(String extension) {
            this.extension = "." + extension;
        }

        public boolean accept(File directory, String name) {
            return name.endsWith(this.extension);
        }
    }

    public FileNameList(String basePath, String extension) {
        File directory = new File(basePath);
        if (directory.exists()) {
            this.fileNames = directory.list(new FileExtensionFilter(extension));
        }
    }
}
