/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.api.chat;

import com.google.gson.JsonObject;
import com.work.wx.config.CustomConfig;
import com.work.wx.config.RequestUtil;
import com.work.wx.controller.api.token.MsgAuditAccessToken;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.server.ChatServer;
import com.work.wx.server.TokenServer;
import com.work.wx.tips.SuccessTip;
import com.work.wx.tips.Tip;
import com.work.wx.util.LangUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@Api(tags = "chat")
public class ChatAPI {
    private final static Logger logger = LoggerFactory.getLogger(ChatAPI.class);

    private TokenServer tokenServer;
    private ChatServer chatServer;
    private CustomConfig customConfig;

    @Autowired
    public void setCustomConfig(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @Autowired()
    public void setChatServer(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    public void setTokenServer(TokenServer tokenServer) {
        this.tokenServer = tokenServer;
    }

    private String getToken() {
        return new MsgAuditAccessToken().getMSGAUDITAccessToken(tokenServer,customConfig);
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


    @ApiOperation("获取会话人员列表")
    @ResponseBody
    @RequestMapping(value = "/getChatUsers",method = RequestMethod.POST)
    public Tip getChatUsers(){
        ChatModel chatModel = new ChatModel(customConfig.getCorp());
        chatModel.setRoomid("");
        chatModel.setMark(null);
        List chatModelList = chatServer.getChatList(chatModel,"from","seq");
        return new SuccessTip(chatModelList);
    }


    @ApiOperation("获取会话人员聊天详情")
    @ResponseBody
    @RequestMapping(value = "/getChatUserRecord",method = RequestMethod.POST)
    public Tip getChatUserRecord(@RequestParam("userId") String userId,@RequestParam("chatUser") String chatUser){
        List chatModelList = chatServer.queryChatList(customConfig.getCorp(), userId, chatUser);
        return new SuccessTip(chatModelList);
    }



    @ApiOperation("群聊获取会话同意情况")
    @ResponseBody
    @RequestMapping(value = "/getChatRoom",method = RequestMethod.POST)
    public Tip getChatRoom(@RequestParam("roomId") String roomId){
        ChatModel chatModel = new ChatModel(customConfig.getCorp());
        chatModel.setRoomid(roomId);
        List<ChatModel> chatModels = chatServer.getChatList(chatModel);
        return new SuccessTip(chatModels);
    }


    @ApiOperation("获取会话存档图片")
    @ResponseBody
    @RequestMapping(value = "/getAuditImageFile",method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getAuditFile(@RequestParam("msgId") String msgId){
        return getBytes(msgId);
    }



    @ApiOperation("获取会话存档文件")
    @ResponseBody
    @RequestMapping(value = "/getAuditFileFile",method = RequestMethod.GET,produces = MediaType.ALL_VALUE)
    public byte[] getAuditFileFile(@RequestParam("msgId") String msgId){
        return getBytes(msgId);
    }


    private byte[] getBytes(@RequestParam("msgId") String msgId) {
        try {
            InputStream inputStream = chatServer.getChatFile(msgId);
            if (null != inputStream) {
                byte[] bytes = LangUtil.getBytes(inputStream);
                inputStream.close();
                return bytes;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
