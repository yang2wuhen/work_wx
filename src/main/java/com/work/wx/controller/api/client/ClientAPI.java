/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.client;

import com.google.gson.JsonObject;
import com.work.wx.config.CustomConfig;
import com.work.wx.config.RequestUtil;
import com.work.wx.controller.api.token.ApplicationAccessToken;
import com.work.wx.controller.api.token.ContactAccessToken;
import com.work.wx.controller.api.token.ProviderAccessToken;
import com.work.wx.server.CorpServer;
import com.work.wx.server.TokenServer;
import com.work.wx.tips.ErrorTip;
import com.work.wx.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.util.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "client")
public class ClientAPI {
    private final static Logger logger = LoggerFactory.getLogger(ClientAPI.class);

    private TokenServer tokenServer;
    private CorpServer corpServer;
    private CustomConfig customConfig;

    @Autowired
    public void setCustomConfig(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @Autowired
    public void setCorpServer(CorpServer corpServer) {
        this.corpServer = corpServer;
    }

    @Autowired
    public void setTokenServer(TokenServer tokenServer) {
        this.tokenServer = tokenServer;
    }

    private String getToken(String corpId) {
        return new ApplicationAccessToken().getApplicationAccessToken(tokenServer,corpServer.getCorpModel(corpId));
    }

    private String getProviderToken() {
        return new ProviderAccessToken().getProviderAccessToken(tokenServer,customConfig);
    }


    /**
     * @todo
     * @author wuhen
     * @param code : 根据客户端code获取userId
     * @returns com.work.wx.tips.Tip
     * @throws
     * @date 2019/12/26 15:07
     */
    @ApiOperation("根据客户端code获取userId")
    @ResponseBody
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public Tip getUserInfo(@RequestParam("cropId") String corpId, @RequestParam("code") String code) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
        ParameterMap parameterMap = new ParameterMap();
        parameterMap.put("code",code);
        return new RequestUtil().requestGettDone(BASE_ADDRESS, getToken(corpId),parameterMap);
    }



    /**
     * @todo
     * @author wuhen
     * @param code : 根据客户端code获取userId
     * @returns com.work.wx.tips.Tip
     * @throws
     * @date 2019/12/26 15:07
     */
    @ApiOperation("根据服务商返回auth_code获取userId")
    @ResponseBody
    @RequestMapping(value = "/getProviderUserInfo", method = RequestMethod.GET)
    public Tip getProviderUserInfo(@RequestParam("auth_code") String code) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/service/get_login_info";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("auth_code",code);
        try {
            return new RequestUtil().requestJsonPostDone(BASE_ADDRESS, getProviderToken(),jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ErrorTip(0);
    }








}
