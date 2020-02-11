/*
 * work_wx
 * wuhen 2020/2/11.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;

public class FileModel extends BaseModel {
    private String fileName;
    private String fileSDKField;
    private Long length;
    private String MD5;
    private String fileType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSDKField() {
        return fileSDKField;
    }

    public void setFileSDKField(String fileSDKField) {
        this.fileSDKField = fileSDKField;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
