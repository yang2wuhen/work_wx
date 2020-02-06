/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.token;

import com.google.gson.Gson;
import com.work.wx.config.CustomConfig;
import com.work.wx.controller.modle.TokenModel;
import com.work.wx.server.TokenServer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class MsgAuditAccessToken {
    private final static Logger logger = LoggerFactory.getLogger(MsgAuditAccessToken.class);
    public static final int MSG_AUDIT_TOKEN_TYPE = 4;

    public static final String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    public String getMSGAUDITAccessToken(TokenServer tokenServer,CustomConfig customConfig) {
        TokenModel tokenModel = tokenServer.getTokenModel(new TokenModel(customConfig.getCorp(),MSG_AUDIT_TOKEN_TYPE));
        if (null != tokenModel) {
            if (tokenModel.getLoseTime() > System.currentTimeMillis()) {
                return tokenModel.getAccess_token();
            }
        }
        boolean status = requestContactToken(tokenServer,customConfig);
        return status ? getMSGAUDITAccessToken(tokenServer,customConfig) : "";
    }



    private boolean setMSGAUDITAccessToken(TokenModel queryModel,TokenModel tokenModel,TokenServer tokenServer) {
        return tokenServer.updateInsertToken(queryModel,tokenModel) > 0;
    }



    private boolean requestContactToken(TokenServer tokenServer,CustomConfig customConfig) {
        String url = BASE_ADDRESS + "?" + "corpid=" + customConfig.getCorp() +"&" + "corpsecret=" + customConfig.getAuditSecret();
        try {
            Response response = new OkHttpClient().newCall(new Request.Builder().url(url).get().build()).execute();
            if (response.code() == 200) {
                TokenModel tokenModel = new Gson().fromJson(response.body().string(), TokenModel.class);
                if (tokenModel.getErrcode() == 0) {
                    logger.debug(tokenModel.toString());
                    tokenModel.setLoseTime(System.currentTimeMillis() + tokenModel.getExpires_in() * 1000);
                    tokenModel.setCorpId(customConfig.getCorp());
                    tokenModel.setToken_type(MSG_AUDIT_TOKEN_TYPE);
                    setMSGAUDITAccessToken(new TokenModel(customConfig.getCorp(),MSG_AUDIT_TOKEN_TYPE),tokenModel,tokenServer);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




}
