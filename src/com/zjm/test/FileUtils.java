package com.zjm.test;

import java.io.File;

public class FileUtils{
    public void renameFile1(String dirFileStr){
        File parent = new File(dirFileStr);
        if (parent.isDirectory()) {
            File[] files = parent.listFiles();
            for(File file : files){
                String fileName = file.getName();
                String[] strs = fileName.split("_");
                if (strs.length > 2) {
                    String newStr = fileName.substring(16);
                    String end = newStr.substring(0, newStr.indexOf("_"));
                    String newFileName = "socialassistant_" + end;
                    file.renameTo(new File(Common.desktop + "change/" + newFileName + ".apk"));
                }
            }
        }
    }
}
