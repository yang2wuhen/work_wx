/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.work.wx.controller.modle.ContactModel;
import com.work.wx.controller.modle.ExtendContactModel;

import java.util.List;

public interface ExtendContactServer {


    public void insertUpdate(ExtendContactModel queryExtendContactModel, ExtendContactModel extendContactModel);

}
