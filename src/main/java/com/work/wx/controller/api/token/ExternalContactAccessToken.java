/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.token;

import com.google.gson.Gson;
import com.work.wx.controller.modle.TokenModel;
import com.work.wx.server.TokenServer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalContactAccessToken {
    private final static Logger logger = LoggerFactory.getLogger(ExternalContactAccessToken.class);
    public static final int EXTERNAL_CONTACT_TOKEN_TYPE = 2;

    public static final String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    public static final String CORP_ID = "ww834fad03c1c86344";
    public static final String SECRET = "6xYZIhkf2KPrIqfjU9xvZ1IUlrWERgXRCo24Vnz88zE";

    public String getExternalContactAccessToken(TokenServer tokenServer) {
        TokenModel tokenModel = tokenServer.getTokenModel(new TokenModel(CORP_ID,EXTERNAL_CONTACT_TOKEN_TYPE));
        if (null != tokenModel) {
            if (tokenModel.getLoseTime() > System.currentTimeMillis()) {
                return tokenModel.getAccess_token();
            }
        }
        boolean status = requestToken(tokenServer);
        return status ? getExternalContactAccessToken(tokenServer) : "";
    }



    private boolean setExternalContactAcessToken(TokenModel queryModel,TokenModel tokenModel,TokenServer tokenServer) {
        return tokenServer.updateInsertToken(queryModel,tokenModel) > 0;
    }



    private boolean requestToken(TokenServer tokenServer) {
        String url = BASE_ADDRESS + "?" + "corpid=" + CORP_ID +"&" + "corpsecret=" + SECRET;
        try {
            Response response = new OkHttpClient().newCall(new Request.Builder().url(url).get().build()).execute();
            if (response.code() == 200) {
                TokenModel tokenModel = new Gson().fromJson(response.body().string(), TokenModel.class);
                if (tokenModel.getErrcode() == 0) {
                    logger.debug(tokenModel.toString());
                    tokenModel.setLoseTime(System.currentTimeMillis() + tokenModel.getExpires_in() * 1000);
                    tokenModel.setCorpId(CORP_ID);
                    tokenModel.setToken_type(EXTERNAL_CONTACT_TOKEN_TYPE);
                    setExternalContactAcessToken(new TokenModel(CORP_ID,EXTERNAL_CONTACT_TOKEN_TYPE),tokenModel,tokenServer);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




}
