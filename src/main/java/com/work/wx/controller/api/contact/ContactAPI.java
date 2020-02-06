/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.contact;

import com.google.gson.Gson;
import com.work.wx.config.CustomConfig;
import com.work.wx.config.RequestUtil;
import com.work.wx.controller.api.WXAPI;
import com.work.wx.controller.api.token.ContactAccessToken;
import com.work.wx.controller.api.token.ExternalContactAccessToken;
import com.work.wx.controller.modle.TokenModel;
import com.work.wx.server.TokenServer;
import com.work.wx.tips.ErrorTip;
import com.work.wx.tips.SuccessTip;
import com.work.wx.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.catalina.util.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "contact")
public class ContactAPI {
    private final static Logger logger = LoggerFactory.getLogger(ContactAPI.class);

    private TokenServer tokenServer;
    private CustomConfig customConfig;

    @Autowired
    public void setCustomConfig(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @Autowired
    public void setTokenServer(TokenServer tokenServer) {
        this.tokenServer = tokenServer;
    }


    private String getToken() {
        return new ContactAccessToken().getContactAccessToken(tokenServer,customConfig);
    }

    /**
     * @todo
     * @author wuhen
     * @param userId : 成员ID
     * @returns com.work.wx.tips.Tip
     * @throws
     * @date 2019/12/26 15:07
     */
    @ApiOperation("读取成员")
    @ResponseBody
    @RequestMapping(value = "/getContactUser",method = RequestMethod.POST)
    public Tip getContactUser(@RequestParam("userId") String userId) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/user/get";

        ParameterMap parameterMap = new ParameterMap();
        parameterMap.put("userid",userId);
        return new RequestUtil().requestGettDone(BASE_ADDRESS, getToken(),parameterMap);
    }


    /**
     * @todo 获取部门
     * @author wuhen
     * @param id : 部门id 如果不填，默认获取全量组织架构
     * @returns com.work.wx.tips.Tip
     * @throws
     * @date 2019/12/26 15:09
     */
    @ApiOperation("获取部门")
    @ResponseBody
    @RequestMapping(value = "/getDepartment",method = RequestMethod.POST)
    public Tip getDepartment(@RequestParam(value = "id",required = false) String id ) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/department/list";
        ParameterMap parameterMap = new ParameterMap();
        parameterMap.put("id",id);
        return new RequestUtil().requestGettDone(BASE_ADDRESS, getToken(),parameterMap);
    }


    /**
     * @todo 获取部门成员
     * @author wuhen
     * @param department_id :获取的部门id
     * @param fetch_child : 是否递归获取子部门下面的成员：1-递归获取，0-只获取本部门
     * @returns com.work.wx.tips.Tip
     * @throws
     * @date 2019/12/26 15:13
     */
    @ApiOperation("获取部门成员")
    @ResponseBody
    @RequestMapping(value = "/getSimplelist",method = RequestMethod.POST)
    public Tip getSimplelist(@RequestParam("department_id") String department_id,@RequestParam("fetch_child") int fetch_child) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist";
        ParameterMap parameterMap = new ParameterMap();
        parameterMap.put("department_id",department_id);
        parameterMap.put("fetch_child",fetch_child);
        return new RequestUtil().requestGettDone(BASE_ADDRESS, getToken(),parameterMap);
    }




}
