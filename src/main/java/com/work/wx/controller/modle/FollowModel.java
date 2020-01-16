/*
 * work_wx
 * wuhen 2020/1/15.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;

import com.google.gson.JsonArray;

public class FollowModel {

    private String cropId;             //公司id

    private int type;                   //	是	联系方式类型,1-单人, 2-多人
    private int scene;                  //	是	场景，1-在小程序中联系，2-通过二维码联系
    private int style;                  //  否	在小程序中联系时使用的控件样式，详见附表
    private String remark;              //	否	联系方式的备注信息，用于助记，不超过30个字符
    private boolean skip_verify;        //	否	外部客户添加时是否无需验证，默认为true
    private String state;               //	否	企业自定义的state参数，用于区分不同的添加渠道，在调用“获取外部联系人详情”时会返回该参数值，不超过30个字符
    private String user;                //	否	使用该联系方式的用户userID列表，在type为1时为必填，且只能有一个
    private String party;               //	否	使用该联系方式的部门id列表，只在type为2时有效

    private String qr_code;

    private String config_id;           //  当前配置的唯一标示

    public FollowModel(String cropId) {
        this.cropId = cropId;
    }


    public FollowModel(String cropId, String config_id) {
        this.cropId = cropId;
        this.config_id = config_id;
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getScene() {
        return scene;
    }

    public void setScene(int scene) {
        this.scene = scene;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSkip_verify() {
        return skip_verify;
    }

    public void setSkip_verify(boolean skip_verify) {
        this.skip_verify = skip_verify;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getConfig_id() {
        return config_id;
    }

    public void setConfig_id(String config_id) {
        this.config_id = config_id;
    }


    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }


    @Override
    public String toString() {
        return "FollowModel{" +
                "cropId='" + cropId + '\'' +
                ", type=" + type +
                ", scene=" + scene +
                ", style=" + style +
                ", remark='" + remark + '\'' +
                ", skip_verify=" + skip_verify +
                ", state='" + state + '\'' +
                ", user='" + user + '\'' +
                ", party='" + party + '\'' +
                ", qr_code='" + qr_code + '\'' +
                ", config_id='" + config_id + '\'' +
                '}';
    }
}
