/*
 * work_wx
 * wuhen 2019/12/27.
 * Copyright (c) 2019  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.db;

import com.work.wx.controller.modle.TokenModel;
import org.springframework.stereotype.Repository;



@Repository
public class TokenDbDao extends MongoDbDao {

    @Override
    protected Class<TokenModel> getEntityClass() {
        return TokenModel.class;
    }






}
