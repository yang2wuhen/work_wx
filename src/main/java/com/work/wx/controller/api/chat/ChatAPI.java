/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.chat;

import com.google.gson.JsonObject;
import com.work.wx.config.RequestUtil;
import com.work.wx.controller.api.token.ExternalContactAccessToken;
import com.work.wx.controller.api.token.MsgAuditAccessToken;
import com.work.wx.server.ChatServer;
import com.work.wx.server.TokenServer;
import com.work.wx.task.BackUp;
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
@Api(tags = "chat")
public class ChatAPI {
    private final static Logger logger = LoggerFactory.getLogger(ChatAPI.class);

    private TokenServer tokenServer;
    private ChatServer chatServer;

    @Autowired()
    public void setChatServer(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    public void setTokenServer(TokenServer tokenServer) {
        this.tokenServer = tokenServer;
    }

    private String getToken() {
        return new MsgAuditAccessToken().getMSGAUDITAccessToken(tokenServer);
    }


    @ApiOperation("单聊获取会话同意情况")
    @ResponseBody
    @RequestMapping(value = "/checkSingleAgree" ,method = RequestMethod.POST)
    public Tip checkSingleAgree(@RequestBody String info){
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/msgaudit/check_single_agree";
//        String json = "{\n" +
//                "\"info\":[\n" +
//                "{\"userid\":\"XuJinSheng\",\"exteranalopenid\":\"wmeDKaCQAAGd9oGiQWxVsAKwV2HxNAAA\"},{\"userid\":\"XuJinSheng\",\"exteranalopenid\":\"wmeDKaCQAAIQ_p7ACn_jpLVBJSGocAAA\"},\n" +
//                "{\"userid\":\"XuJinSheng\",\"exteranalopenid\":\"wmeDKaCQAAPE_p7ABnxkpLBBJSGocAAA\"}\n" +
//                "]\n" +
//                "}\n";
        return new RequestUtil().requestJsonPostDone(BASE_ADDRESS, getToken(), info);
    }



    @ApiOperation("群聊获取会话同意情况")
    @ResponseBody
    @RequestMapping(value = "/checkRoomAgree",method = RequestMethod.POST)
    public Tip checkRoomAgree(@RequestParam("roomid") String roomid){
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/msgaudit/check_room_agree";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("roomid",roomid);
        return new RequestUtil().requestJsonPostDone(BASE_ADDRESS, getToken(), jsonObject.toString());
    }



//    @ApiOperation("获取加密归档记录")
//    @ResponseBody
//    @RequestMapping(value = "/getEncryptChatData" ,method = RequestMethod.POST)
//    public Tip getEncryptChatData(){
////        String content = new BackUp().getEncryptChatData(0,1000,TIME_OUT);
//        String content = BackUp.getEncryptChatData(0,1000);
//
//        return new SuccessTip(content);
//    }
//
//
//    @ApiOperation("获取归档记录")
//    @ResponseBody
//    @RequestMapping(value = "/getChatData" ,method = RequestMethod.POST)
//    public Tip getChatData(){
////        List list = new BackUp().getChatData(0,1000,TIME_OUT);
//        List list = BackUp.getChatDataList(0,1000);
//        return new SuccessTip(list);
//    }




}
