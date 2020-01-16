/*
 * work_wx
 * wuhen 2019/12/27.
 * Copyright (c) 2019  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;


import com.work.wx.controller.modle.TokenModel;
import com.work.wx.db.TokenDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TokenServerImpl implements TokenServer {

    private TokenDbDao tokenDbDao;

    @Autowired
    public void setBookMongoDbDao(TokenDbDao tokenDbDao) {
        this.tokenDbDao = tokenDbDao;
    }


    @Override
    public long updateInsertToken(TokenModel queryModel, TokenModel tokenModel) {
        return tokenDbDao.updateInsert(queryModel,tokenModel);
    }


    @Override
    public TokenModel getTokenModel(TokenModel tokenModel) {
        return (TokenModel) tokenDbDao.queryOne(tokenModel);
    }


}
