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

    @Override
    public List<ContactModel> getContacts(ContactModel contactModel) {
        return contactDbDao.queryList(contactModel);
    }
}
