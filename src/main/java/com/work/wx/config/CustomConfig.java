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
    private String providerSecret;


    public String getProviderSecret() {
        return providerSecret;
    }

    public void setProviderSecret(String providerSecret) {
        this.providerSecret = providerSecret;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }
}
