/*
 * work_wx
 * wuhen 2020/2/6.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


public class CorpModel extends BaseModel {

    private String corp;                        //公司id
    private String adminId;                     //管理员id
    private String contactSecret;               //通讯录秘钥
    private String externalContactSecret;       //外部联系人秘钥
    private String auditSecret;                 //会话存档秘钥
    private String applicationSecret;           //移动端登录秘钥

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }


    public String getContactSecret() {
        return contactSecret;
    }

    public void setContactSecret(String contactSecret) {
        this.contactSecret = contactSecret;
    }

    public String getExternalContactSecret() {
        return externalContactSecret;
    }

    public void setExternalContactSecret(String externalContactSecret) {
        this.externalContactSecret = externalContactSecret;
    }

    public String getAuditSecret() {
        return auditSecret;
    }

    public void setAuditSecret(String auditSecret) {
        this.auditSecret = auditSecret;
    }

    public String getApplicationSecret() {
        return applicationSecret;
    }

    public void setApplicationSecret(String applicationSecret) {
        this.applicationSecret = applicationSecret;
    }

    public CorpModel(String corp) {
        this.corp = corp;
    }

    public CorpModel() {
    }
}
