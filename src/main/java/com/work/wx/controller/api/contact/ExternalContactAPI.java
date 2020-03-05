/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.contact;

import com.google.gson.Gson;
import com.work.wx.config.CustomConfig;
import com.work.wx.config.RequestUtil;
import com.work.wx.controller.api.token.ContactAccessToken;
import com.work.wx.controller.api.token.ExternalContactAccessToken;
import com.work.wx.controller.modle.ExtendContactModel;
import com.work.wx.controller.modle.TokenModel;
import com.work.wx.server.CorpServer;
import com.work.wx.server.ExtendContactServer;
import com.work.wx.server.TokenServer;
import com.work.wx.tips.ErrorTip;
import com.work.wx.tips.SuccessTip;
import com.work.wx.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@Api(tags = "externalContact")
public class ExternalContactAPI {
    private final static Logger logger = LoggerFactory.getLogger(ExternalContactAPI.class);

    private TokenServer tokenServer;
    private CorpServer corpServer;
    private ExtendContactServer extendedService;

    @Autowired
    public void setExtendedService(ExtendContactServer extendedService) {
        this.extendedService = extendedService;
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
        return new ExternalContactAccessToken().getExternalContactAccessToken(tokenServer,corpServer.getCorpModel(corpId));
    }


    @ApiOperation("获取外部联系人列表")
    @ResponseBody
    @RequestMapping(value = "/getExternalContactUsers",method = RequestMethod.POST)
    public Tip getExternalContactUsers(@RequestParam("cropId") String cropId) {
        ExtendContactModel extendContactModel = new ExtendContactModel(cropId);
        List<ExtendContactModel> extendContactModels = extendedService.getExtendContacts(extendContactModel);
        return new SuccessTip(extendContactModels);
    }


    @ApiOperation("获取外部联系人详情")
    @ResponseBody
    @RequestMapping(value = "/getExternalUserInfo",method = RequestMethod.POST)
    public Tip getExternalUserInfo(@RequestParam("cropId") String cropId,@RequestParam("externalUserId") String externalUserId) {
        ExtendContactModel extendContactModel = new ExtendContactModel(cropId);
        extendContactModel.setExternal_userid(externalUserId);
        ExtendContactModel extendContact = extendedService.getExtendContact(extendContactModel);
        return new SuccessTip(extendContact);
    }


    /**
     * @todo 获取配置过客户群管理的客户群列表
     * @author wuhen
     * @returns com.work.wx.tips.Tip
     * @throws
     * @date 2019/12/26 15:36
     */
    @ApiOperation("获取配置过客户群管理的客户群列表")
    @ResponseBody
    @RequestMapping(value = "/getExternalGroupChat",method = RequestMethod.POST)
    public Tip getExternalGroupChat(@RequestParam("cropId") String cropId,@RequestBody String json) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/groupchat/list";
        return new RequestUtil().requestJsonPostDone(BASE_ADDRESS, getToken(cropId), json);
    }


    /**
     * @todo 客户群统计数据
     * @author wuhen
     * @returns com.work.wx.tips.Tip
     * @throws
     * @date 2019/12/26 15:39
     */
    @ApiOperation("客户群统计数据")
    @ResponseBody
    @RequestMapping(value = "/getUserBehavior",method = RequestMethod.POST)
    public Tip getUserBehavior(@RequestParam("cropId") String cropId,@RequestBody  String json) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_user_behavior_data";
        return new RequestUtil().requestJsonPostDone(BASE_ADDRESS, getToken(cropId), json);
    }



    /**
     * @todo 获取成员联系客户的数据，包括发起申请数、新增客户数、聊天数、发送消息数和删除/拉黑成员的客户数等指标
     * @author wuhen
     * @returns com.work.wx.tips.Tip
     * @throws
     * @date 2019/12/26 15:39
     */
    @ApiOperation("获取成员联系客户的数据，包括发起申请数、新增客户数、聊天数、发送消息数和删除/拉黑成员的客户数等指标")
    @ResponseBody
    @RequestMapping(value = "/getUserStatistic",method = RequestMethod.POST)
    public Tip getUserStatistic(@RequestParam("cropId") String cropId,@RequestBody String json) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/groupchat/statistic";
        return new RequestUtil().requestJsonPostDone(BASE_ADDRESS, getToken(cropId), json);
    }









}
