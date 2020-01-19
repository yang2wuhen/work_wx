/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.token;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.work.wx.config.RequestUtil;
import com.work.wx.controller.modle.TokenModel;
import com.work.wx.server.TokenServer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.catalina.util.ParameterMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProviderAccessToken {
    private final static Logger logger = LoggerFactory.getLogger(ProviderAccessToken.class);
    public static final int CONTACT_TOKEN_TYPE = 10;

    public static final String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/service/get_provider_token";
    public static final String CORP_ID = "ww834fad03c1c86344";
    public static final String PROVIDER_SECRET = "cGTOWlJ432ePXzFfcHXn8xLxyEZxbLiKlVZWzZ_9gyW6olYn6Jxx8K2Tlz28Qr0P";



    public String getProviderAccessToken(TokenServer tokenServer) {
        TokenModel tokenModel = tokenServer.getTokenModel(new TokenModel(CORP_ID,CONTACT_TOKEN_TYPE));
        if (null != tokenModel) {
            if (tokenModel.getLoseTime() > System.currentTimeMillis()) {
                return tokenModel.getAccess_token();
            }
        }
        boolean status = requestProviderToken(tokenServer);
        return status ? getProviderAccessToken(tokenServer) : "";
    }



    private boolean setContactAcessToken(TokenModel queryModel,TokenModel tokenModel,TokenServer tokenServer) {
        return tokenServer.updateInsertToken(queryModel,tokenModel) > 0;
    }



    private boolean requestProviderToken(TokenServer tokenServer) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("corpid",CORP_ID);
        jsonObject.addProperty("provider_secret",PROVIDER_SECRET);
        try {
            String result = new RequestUtil().requestJsonPost(BASE_ADDRESS, "",jsonObject.toString());
            if (StringUtils.isNoneBlank(result)) {
                JsonObject js = JsonParser.parseString(result).getAsJsonObject();
                TokenModel tokenModel = new TokenModel(CORP_ID,CONTACT_TOKEN_TYPE);
                tokenModel.setLoseTime(System.currentTimeMillis() + js.get("expires_in").getAsInt() * 1000);
                tokenModel.setAccess_token(js.get("provider_access_token").getAsString());
                setContactAcessToken(new TokenModel(CORP_ID,CONTACT_TOKEN_TYPE),tokenModel,tokenServer);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }






}
