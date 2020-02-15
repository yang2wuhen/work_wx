/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;


import com.work.wx.controller.modle.ContactModel;
import com.work.wx.controller.modle.ExtendContactModel;
import com.work.wx.db.ContactDbDao;
import com.work.wx.db.ExtendContactDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExtendContactServerImpl implements ExtendContactServer {

    private ExtendContactDbDao extendContactDbDao;

    @Autowired
    public void setExtendContactDbDao(ExtendContactDbDao extendContactDbDao) {
        this.extendContactDbDao = extendContactDbDao;
    }


    @Override
    public void insertUpdate(ExtendContactModel queryExtendContactModel, ExtendContactModel extendContactModel) {
        extendContactDbDao.updateInsert(queryExtendContactModel, extendContactModel);
    }



}
