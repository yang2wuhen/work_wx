/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;

import com.work.wx.controller.modle.BaseModel;

public class ChatModelMedia extends BaseModel {

    private Integer voice_size;   	//  语音消息大小。Uint32类型
    private Integer play_length;   //	播放长度。Uint32类型
    private Integer filesize;
    private String sdkfileid;
    private String md5sum;


    public Integer getVoice_size() {
        return voice_size;
    }

    public void setVoice_size(Integer voice_size) {
        this.voice_size = voice_size;
    }

    public Integer getPlay_length() {
        return play_length;
    }

    public void setPlay_length(Integer play_length) {
        this.play_length = play_length;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public String getSdkfileid() {
        return sdkfileid;
    }

    public void setSdkfileid(String sdkfileid) {
        this.sdkfileid = sdkfileid;
    }

    public String getMd5sum() {
        return md5sum;
    }

    public void setMd5sum(String md5sum) {
        this.md5sum = md5sum;
    }
}
