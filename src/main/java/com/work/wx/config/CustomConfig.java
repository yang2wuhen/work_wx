/*
 * work_wx
 * wuhen 2020/2/6.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "work-wx-config")
public class CustomConfig {

    private String corp;
    private String applicationSecret;
    private String contactSecret;
    private String externalContactSecret;
    private String providerSecret;
    private String auditSecret;


    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }


    public String getApplicationSecret() {
        return applicationSecret;
    }

    public void setApplicationSecret(String applicationSecret) {
        this.applicationSecret = applicationSecret;
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

    public String getProviderSecret() {
        return providerSecret;
    }

    public void setProviderSecret(String providerSecret) {
        this.providerSecret = providerSecret;
    }

    public String getAuditSecret() {
        return auditSecret;
    }

    public void setAuditSecret(String auditSecret) {
        this.auditSecret = auditSecret;
    }
}
