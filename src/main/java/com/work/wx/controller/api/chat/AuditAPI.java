/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.chat;

import com.work.wx.config.CustomConfig;
import com.work.wx.controller.api.token.MsgAuditAccessToken;
import com.work.wx.controller.modle.AuditModel;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.KeywordConfigModel;
import com.work.wx.server.AuditServer;
import com.work.wx.tips.ErrorTip;
import com.work.wx.tips.SuccessTip;
import com.work.wx.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Parameter;
import java.util.List;

@RestController
@Api(tags = "audit")
public class AuditAPI {
    private final static Logger logger = LoggerFactory.getLogger(AuditAPI.class);
    private CustomConfig customConfig;
    private AuditServer auditServer;

    @Autowired
    public void setCustomConfig(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @Autowired
    public void setAuditServer(AuditServer auditServer) {
        this.auditServer = auditServer;
    }


    @ApiOperation("添加审计关键词配置")
    @ResponseBody
    @RequestMapping(value = "/addAuditKeyword",method = RequestMethod.POST)
    public Tip addAuditKeyword(@RequestBody String[] keyword){
        return getTip(keyword);
    }


    @ApiOperation("修改审计关键词配置")
    @ResponseBody
    @RequestMapping(value = "/updateAuditKeyword",method = RequestMethod.POST)
    public Tip updateAuditKeyword(@RequestBody String[] keyword){
        return getTip(keyword);
    }

    @ApiOperation("获取审计人员列表")
    @ResponseBody
    @RequestMapping(value = "/getAuditUsers",method = RequestMethod.POST)
    public Tip getAuditUsers(){
        AuditModel auditModel = new AuditModel(customConfig.getCorp());
        List audits =  auditServer.getGroupAuditUsers(auditModel,"from","seq");
        return new SuccessTip(audits);
    }



    @ApiOperation("获取审计全部数据")
    @ResponseBody
    @RequestMapping(value = "/getAuditModels",method = RequestMethod.POST)
    public Tip getAuditModels(){
        AuditModel auditModel = new AuditModel(customConfig.getCorp());
        List audits =  auditServer.getAuditModels(auditModel);
        return new SuccessTip(audits);
    }


    private Tip getTip(@RequestBody String[] keyword) {
        if (null != keyword && keyword.length > 0) {
            KeywordConfigModel keywordConfigModel = new KeywordConfigModel(customConfig.getCorp());
            keywordConfigModel.setKeywords(keyword);
            keywordConfigModel.setInsertTime(System.currentTimeMillis());
            Long id = auditServer.insertKeywordConfigModel(keywordConfigModel);
            return new SuccessTip(id);
        }
        return new ErrorTip(0);
    }



}
