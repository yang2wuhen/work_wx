/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;

import com.work.wx.controller.modle.BaseModel;

public class ChatModelEmotion extends BaseModel {

    private Long type;          //	表情类型，png或者gif.1表示gif 2表示png。Uint32类型
    private Integer width;         //  表情图片宽度。Uint32类型
    private Integer height;        //	表情图片高度。Uint32类型
    private Long imagesize;     //	资源的文件大小。Uint32类型
    private String sdkfileid;       //	媒体资源的id信息。String类型
    private String md5sum;      //	资源的md5值，供进行校验。String类型

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Long getImagesize() {
        return imagesize;
    }

    public void setImagesize(Long imagesize) {
        this.imagesize = imagesize;
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
