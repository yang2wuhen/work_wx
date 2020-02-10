/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;


import com.work.wx.controller.modle.ChatDataModel;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.ContactModel;
import com.work.wx.db.ChatDataDbDao;
import com.work.wx.db.ChatDbDao;
import com.work.wx.db.ContactDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ContactServerImpl implements ContactServer {

    private ContactDbDao contactDbDao;

    @Autowired
    public void setContactDbDao(ContactDbDao contactDbDao) {
        this.contactDbDao = contactDbDao;
    }

    @Override
    public void insertAll(List<ContactModel> list) {
        ContactModel contact = null;
        for (ContactModel contactModel : list) {
            contact = new ContactModel(contactModel.getCorp(),contactModel.getUserid());
            contactDbDao.updateInsert(contact,contactModel);
        }
    }


}
