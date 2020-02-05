/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.contact;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.work.wx.config.RequestUtil;
import com.work.wx.controller.api.token.ExternalContactAccessToken;
import com.work.wx.controller.modle.FollowModel;
import com.work.wx.server.FollowServer;
import com.work.wx.server.TokenServer;
import com.work.wx.tips.ErrorTip;
import com.work.wx.tips.SuccessTip;
import com.work.wx.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.util.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonProperties;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = "followContact")
public class FollowContactAPI {
    private final static Logger logger = LoggerFactory.getLogger(FollowContactAPI.class);

    private TokenServer tokenServer;
    private FollowServer followServer;

    @Autowired
    public void setTokenServer(TokenServer tokenServer) {
        this.tokenServer = tokenServer;
    }

    @Autowired
    public void setFollowServer(FollowServer followServer) {
        this.followServer = followServer;
    }


    private String getToken() {
        return new ExternalContactAccessToken().getExternalContactAccessToken(tokenServer);
    }


    @ApiOperation("获取配置了客户联系功能的成员列表")
    @ResponseBody
    @RequestMapping(value = "/getFollowUserList",method = RequestMethod.POST)
    public Tip getFollowUserList() {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_follow_user_list";
        ParameterMap parameterMap = new ParameterMap();
        return new RequestUtil().requestGettDone(BASE_ADDRESS, getToken(),parameterMap);
    }



    @ApiOperation("获取企业已配置的「联系我」方式")
    @ResponseBody
    @RequestMapping(value = "/getFollowWay",method = RequestMethod.POST)
    public Tip getFollowWay() {
        List followModels = followServer.getAllFollowConfig(new FollowModel(ExternalContactAccessToken.CORP_ID));
        if (null != followModels && followModels.size() > 0) {
            return new SuccessTip(followModels);
        }
        return new ErrorTip(0);
    }



    @ApiOperation("配置客户联系「联系我」方式")
    @ResponseBody
    @RequestMapping(value = "/addContactWay",method = RequestMethod.POST)
    public Tip addContactWay(@RequestBody FollowModel followModel) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/add_contact_way";
        String json = new Gson().toJson(followModel);
        try {
            String result =  new RequestUtil().requestJsonPost(BASE_ADDRESS, getToken(),json);
            if (null != result) {
                int code = JsonParser.parseString(result).getAsJsonObject().get("errcode").getAsInt();
                if (code == 0) {
                    String configId = JsonParser.parseString(result).getAsJsonObject().get("config_id").getAsString();
                    followModel.setConfig_id(configId);
                    followModel.setCropId(ExternalContactAccessToken.CORP_ID);
                    boolean flag = followServer.updateFollowConfig(new FollowModel(ExternalContactAccessToken.CORP_ID),followModel) > 0;
                    if (flag) {
                        return  new SuccessTip(followModel);
                    }
                }
            }
        } catch (Exception e) {
            return new ErrorTip(0,e.getMessage());
        }
        return new ErrorTip(0);
    }



    @ApiOperation("更新企业已配置的「联系我」方式")
    @ResponseBody
    @RequestMapping(value = "/updateContactWay",method = RequestMethod.POST)
    public Tip updateContactWay(@RequestBody FollowModel followModel) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/update_contact_way";
        String json = new Gson().toJson(followModel);
        try {
            String result =  new RequestUtil().requestJsonPost(BASE_ADDRESS, getToken(),json);
            if (null != result) {
                int code = JsonParser.parseString(result).getAsJsonObject().get("errcode").getAsInt();
                if (code == 0) {
                    String configId = JsonParser.parseString(result).getAsJsonObject().get("config_id").getAsString();
                    followModel.setConfig_id(configId);
                    followModel.setCropId(ExternalContactAccessToken.CORP_ID);
                    boolean flag = followServer.updateFollowConfig(new FollowModel(ExternalContactAccessToken.CORP_ID,
                            followModel.getConfig_id()),followModel) > 0;
                    if (flag) {
                        return  new SuccessTip(followModel);
                    }
                }
            }
        } catch (Exception e) {
            return new ErrorTip(0,e.getMessage());
        }
        return new ErrorTip(0);
    }



    @ApiOperation("config_id「联系我」方式")
    @ResponseBody
    @RequestMapping(value = "/getFollowWayById",method = RequestMethod.POST)
    public Tip getFollowWayById(@RequestParam String configId) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_contact_way";
        JsonObject json = new JsonObject();
        json.addProperty("config_id", configId);
        return new RequestUtil().requestJsonPostDone(BASE_ADDRESS, getToken(),json.toString());
    }



    @ApiOperation("删除企业已配置的「联系我」方式")
    @ResponseBody
    @RequestMapping(value = "/delFollowWayById",method = RequestMethod.POST)
    public Tip delFollowWayById(@RequestParam String configId) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/del_contact_way";
        JsonObject json = new JsonObject();
        json.addProperty("config_id", configId);
        return new RequestUtil().requestJsonPostDone(BASE_ADDRESS, getToken(),json.toString());
    }






}
