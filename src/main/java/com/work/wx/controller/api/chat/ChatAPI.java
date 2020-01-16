/*
 * work_wx
 * wuhen 2020/1/2.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.chat;

import com.work.wx.server.BackUpServer;
import com.work.wx.server.BackUpServerImpl;
import com.work.wx.tips.SuccessTip;
import com.work.wx.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@Api(tags = "chat")
public class ChatAPI {
    private final static Logger logger = LoggerFactory.getLogger(ChatAPI.class);
    private final long TIME_OUT = 1000 * 60;


//    private BackUpServer backUpServer;
//
//    @Autowired()
//    public void setBackUpServer(BackUpServer backUpServer) {
//        this.backUpServer = backUpServer;
//    }


    @ApiOperation("获取加密归档记录")
    @ResponseBody
    @RequestMapping(value = "/getEncryptChatData" ,method = RequestMethod.POST)
    public Tip getEncryptChatData(){
        String content = new BackUpServerImpl().getEncryptChatData(0,1000,TIME_OUT);
        return new SuccessTip(content);
    }


    @ApiOperation("获取归档记录")
    @ResponseBody
    @RequestMapping(value = "/getChatData" ,method = RequestMethod.POST)
    public Tip getChatData(){
        List list = new BackUpServerImpl().getChatData(0,1000,TIME_OUT);
        return new SuccessTip(list);
    }




}
