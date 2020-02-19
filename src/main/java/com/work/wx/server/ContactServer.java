/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.work.wx.controller.modle.ChatDataModel;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.ContactModel;
import com.work.wx.controller.modle.ExtendContactModel;

import java.util.List;
import java.util.Set;

public interface ContactServer {


    public void insertAll(List<ContactModel> list);

    public List<ContactModel> getContacts(ContactModel contactModel);


}
