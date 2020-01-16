/*
 * work_wx
 * wuhen 2019/12/27.
 * Copyright (c) 2019  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.tips;



public class ErrorTip extends Tip {
    public ErrorTip(int state, String message) {
        super();
        this.state = state;
        this.message = message;
    }


    public ErrorTip(int state) {
        super();
        this.state = state;
        this.message = "";
    }

}
