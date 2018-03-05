package com.bj.dto;

/**
 * Created by neko on 2018/3/5.
 */
public class FileInfo {

    private String path;

    public FileInfo(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
