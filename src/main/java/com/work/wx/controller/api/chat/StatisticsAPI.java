/*
 * work_wx
 * wuhen 2020/3/3.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.chat;

import com.work.wx.controller.modle.ChatModel;
import com.work.wx.tips.SuccessTip;
import com.work.wx.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "statistics")
public class StatisticsAPI {
    private final static Logger logger = LoggerFactory.getLogger(StatisticsAPI.class);


    @ApiOperation("获取新增外部联系人统计")
    @ResponseBody
    @RequestMapping(value = "/getAddExternalContact",method = RequestMethod.POST)
    public Tip getAddExternalContact(@RequestParam("corpId") String corpId,Long startTime,Long endTime){

        return new SuccessTip();
    }



    @ApiOperation("获取删除外部联系人统计")
    @ResponseBody
    @RequestMapping(value = "/getDelExternalContact",method = RequestMethod.POST)
    public Tip getDelExternalContact(@RequestParam("corpId") String corpId,Long startTime,Long endTime){

        return new SuccessTip();
    }


    @ApiOperation("获取拉黑外部联系人统计")
    @ResponseBody
    @RequestMapping(value = "/getBlackExternalContact",method = RequestMethod.POST)
    public Tip getBlackExternalContact(@RequestParam("corpId") String corpId,Long startTime,Long endTime){

        return new SuccessTip();
    }






}
