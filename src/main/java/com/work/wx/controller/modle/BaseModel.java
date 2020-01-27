/*
 * work_wx
 * wuhen 2020/1/27.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;

import java.io.Serializable;

public class BaseModel implements Serializable {
    public String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
