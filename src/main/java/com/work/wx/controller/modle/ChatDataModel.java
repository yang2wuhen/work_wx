/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;


public class ChatDataModel extends BaseModel{

    private String sdkField;
    private byte[] file;
    private boolean isExit;
    private String cropId;

    public String getSdkField() {
        return sdkField;
    }

    public void setSdkField(String sdkField) {
        this.sdkField = sdkField;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public ChatDataModel(String cropId, String sdkField) {
        this.cropId = cropId;
        this.sdkField = sdkField;
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }
}
