/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.chat;

import com.work.wx.config.CustomConfig;
import com.work.wx.controller.modle.AuditModel;
import com.work.wx.controller.modle.CorpModel;
import com.work.wx.controller.modle.KeywordConfigModel;
import com.work.wx.server.AuditServer;
import com.work.wx.server.CorpServer;
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

@RestController
@Api(tags = "corp")
public class CorpAPI {
    private final static Logger logger = LoggerFactory.getLogger(CorpAPI.class);
    private CorpServer corpServer;

    @Autowired
    public void setCorpServer(CorpServer corpServer) {
        this.corpServer = corpServer;
    }


    @ApiOperation("添加公司配置")
    @ResponseBody
    @RequestMapping(value = "/addCorp",method = RequestMethod.POST)
    public Tip addCorp(@RequestBody CorpModel corpModel){
        corpServer.addCorpModel(corpModel);
        return new SuccessTip();
    }


    @ApiOperation("添加公司配置")
    @ResponseBody
    @RequestMapping(value = "/updateCorp",method = RequestMethod.POST)
    public Tip updateCorp(@RequestBody CorpModel corpModel){
        corpServer.insertCorpModel(corpModel);
        return new SuccessTip();
    }





}
