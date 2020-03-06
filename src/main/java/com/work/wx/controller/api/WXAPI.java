/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api;

import com.work.wx.config.CustomConfig;
import com.work.wx.controller.api.token.ApplicationAccessToken;
import com.work.wx.controller.api.token.ContactAccessToken;
import com.work.wx.controller.api.token.ExternalContactAccessToken;
import com.work.wx.controller.api.token.ProviderAccessToken;
import com.work.wx.server.CorpServer;
import com.work.wx.server.TokenServer;
import com.work.wx.tips.SuccessTip;
import com.work.wx.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "token")
@RestController
public class WXAPI  {
    private final static Logger logger = LoggerFactory.getLogger(WXAPI.class);

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

    @ApiOperation("获取外部联系人token")
    @ResponseBody
    @RequestMapping(value = "/getExternalContactWorkWXToken",method = RequestMethod.POST)
    public Tip getWorkWXToken(@RequestParam("corp") String corpId) {
        String accessToken = new ExternalContactAccessToken().getExternalContactAccessToken(tokenServer,corpServer.getCorpModel(corpId));
        return new SuccessTip(accessToken);
    }

    @ApiOperation("获取通讯录Token")
    @ResponseBody
    @RequestMapping(value = "/getContactWXToken",method = RequestMethod.POST)
    public Tip getWorkWXContactToken(@RequestParam("corp") String corpId) {
        String accessToken = new ContactAccessToken().getContactAccessToken(tokenServer,corpServer.getCorpModel(corpId));
        return new SuccessTip(accessToken);
    }


    @ApiOperation("获取APP应用Token")
    @ResponseBody
    @RequestMapping(value = "/getApplicationWXToken",method = RequestMethod.POST)
    public Tip getApplicationWXToken(@RequestParam("corp") String corpId) {
        String accessToken = new ApplicationAccessToken().getApplicationAccessToken(tokenServer,corpServer.getCorpModel(corpId));
        return new SuccessTip(accessToken);
    }


    @ApiOperation("获取服务商Token")
    @ResponseBody
    @RequestMapping(value = "/getProviderWXToken",method = RequestMethod.POST)
    public Tip getProviderWXToken(@RequestParam("corp") String corpId) {
        String accessToken = new ProviderAccessToken().getProviderAccessToken(tokenServer,customConfig);
        return new SuccessTip(accessToken);
    }





}
