/*
 * work_wx
 * wuhen 2020/1/2.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@Api(tags = "client")
public class Index {
    private final static Logger logger = LoggerFactory.getLogger(Index.class);


    @ApiOperation("web端登录")
    @ResponseBody
    @RequestMapping(value = "/index" ,method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("index");
    }






}
