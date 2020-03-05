/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import com.google.gson.*;
import com.work.wx.config.CustomConfig;
import com.work.wx.config.RequestUtil;
import com.work.wx.controller.api.token.ContactAccessToken;
import com.work.wx.controller.api.token.ExternalContactAccessToken;
import com.work.wx.controller.modle.ContactModel;
import com.work.wx.controller.modle.CorpModel;
import com.work.wx.controller.modle.ExtendContactModel;
import com.work.wx.server.*;
import org.apache.catalina.util.ParameterMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutoBackUpContactTask {
    private final static Logger logger = LoggerFactory.getLogger(AutoBackUpContactTask.class);

    private TokenServer tokenServer;
    private ContactServer contactServer;
    private ExtendContactServer extendContactServer;
    private GroupServer groupServer;
    private CorpServer corpServer;

    @Autowired
    public void setCorpServer(CorpServer corpServer) {
        this.corpServer = corpServer;
    }

    @Autowired
    public void setGroupServer(GroupServer groupServer) {
        this.groupServer = groupServer;
    }

    @Autowired
    public void setTokenServer(TokenServer tokenServer) {
        this.tokenServer = tokenServer;
    }

    @Autowired
    public void setContactServer(ContactServer contactServer) {
        this.contactServer = contactServer;
    }

    @Autowired
    public void setExtendContactServer(ExtendContactServer extendContactServer) {
        this.extendContactServer = extendContactServer;
    }

    private String getToken(String corpId) {
        return new ContactAccessToken().getContactAccessToken(tokenServer,corpServer.getCorpModel(corpId));
    }

    private String getExtendToken(String corpId) {
        return new ExternalContactAccessToken().getExternalContactAccessToken(tokenServer,corpServer.getCorpModel(corpId));
    }

    /**
     * @todo 自动备份通讯录
     * @author wuhen
     * @returns void
     * @throws
     * @date 2020/1/27 13:14
     */
    @Async
    @Scheduled(fixedRate = 1000*60*60*2, initialDelay = 1000*60*10)
    public void backupContact() {
        List<CorpModel> corpModels = corpServer.getCorpModels();
        if (null != corpModels && corpModels.size() > 0) {
            for (CorpModel corpModel : corpModels) {
                String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/user/list";
                ParameterMap parameterMap = new ParameterMap();
                parameterMap.put("department_id",1);
                parameterMap.put("fetch_child",1);
                try {
                    String response =  new RequestUtil().requestGet(BASE_ADDRESS, getToken(corpModel.getCorp()),parameterMap);
                    if (StringUtils.isNotEmpty(response)) {
                        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
                        int status = jsonObject.get("errcode").getAsInt();
                        if (status == 0) {
                            JsonArray jsonArray = jsonObject.get("userlist").getAsJsonArray();
                            List list = new ArrayList();
                            ContactModel contactModel = null;
                            Long localTime = System.currentTimeMillis();
                            for (JsonElement jsonElement : jsonArray) {
                                logger.debug(jsonElement.toString());
                                contactModel = new Gson().fromJson(jsonElement, ContactModel.class);
                                contactModel.setCorp(corpModel.getCorp());
                                contactModel.setUpdateTime(localTime);
                                list.add(contactModel);
                            }
                            contactServer.insertAll(list);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }



    /**
     * @todo 自动备份外部联系人
     * @author wuhen
     * @returns void
     * @throws
     * @date 2020/1/27 13:14
     */
    @Async
    @Scheduled(fixedRate = 1000*60*60*2, initialDelay = 1000*60*20)
    public void backUpExtendContact() {
        List<CorpModel> corpModels = corpServer.getCorpModels();
        if (null != corpModels && corpModels.size() > 0) {
            for (CorpModel corpModel : corpModels) {
                String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/list";
                ParameterMap parameterMap = new ParameterMap();
                parameterMap.put("userid",corpModel.getAdminId());
                try {
                    String response =  new RequestUtil().requestGet(BASE_ADDRESS, getExtendToken(corpModel.getCorp()),parameterMap);
                    if (StringUtils.isNotEmpty(response)) {
                        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
                        int status = jsonObject.get("errcode").getAsInt();
                        if (status == 0) {
                            JsonArray jsonArray = jsonObject.get("external_userid").getAsJsonArray();
                            ExtendContactModel extendContactModel = null;
                            Long localTime = System.currentTimeMillis();
                            for (JsonElement jsonElement : jsonArray) {
                                String extendUserId = jsonElement.getAsString();
                                logger.debug("get userId is "+ extendUserId +" info");
                                String extendContact = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get";
                                ParameterMap map = new ParameterMap();
                                map.put("external_userid",extendUserId);
                                String requestGet =  new RequestUtil().requestGet(extendContact, getExtendToken(corpModel.getCorp()),map);
                                if (StringUtils.isNotEmpty(requestGet)) {
                                    logger.debug(requestGet);
                                    JsonObject resultRes = JsonParser.parseString(requestGet).getAsJsonObject();
                                    if (resultRes.get("errcode").getAsInt() == 0) {
                                        extendContactModel = new ExtendContactModel(corpModel.getCorp(), extendUserId);
                                        ExtendContactModel contactModel = new Gson().fromJson(resultRes, ExtendContactModel.class);
                                        String userId = resultRes.get("external_contact").getAsJsonObject().get("external_userid").
                                                getAsString();
                                        contactModel.setExternal_userid(userId);
                                        contactModel.setCorpId(corpModel.getCorp());
                                        contactModel.setUpdateTime(localTime);
                                        extendContactServer.insertUpdate(extendContactModel, contactModel);
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }



    /**
     * @todo 自动备份外部联系人群
     * @author wuhen
     * @returns void
     * @throws
     * @date 2020/1/27 13:14
     */
    @Async
    @Scheduled(fixedRate = 1000*60*60*2, initialDelay = 1000*60*30)
    public void backUpExtendGroup() {
        List<CorpModel> corpModels = corpServer.getCorpModels();
        if (null != corpModels && corpModels.size() > 0) {
            for (CorpModel corpModel : corpModels) {
                ContactModel contactModel = new ContactModel(corpModel.getCorp());
                List<ContactModel> contactModels = contactServer.getContacts(contactModel);
                if (null != contactModels && contactModels.size() > 0) {
                    for (ContactModel contact : contactModels) {
                        String userId = contact.getUserid();
                        logger.debug("get user chat_id from user id  is " + userId);
                        GroupDataProcess.groupGetChatId(groupServer,corpModel.getCorp(),userId, getExtendToken(corpModel.getCorp()));
                    }
                }
            }
        }

     }






}
