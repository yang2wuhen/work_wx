/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.work.wx.controller.modle.TokenModel;

public interface TokenServer {

    public long updateInsertToken(TokenModel queryModel, TokenModel tokenModel);

    public TokenModel getTokenModel(TokenModel tokenModel);
}
