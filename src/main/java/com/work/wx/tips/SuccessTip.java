/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.tips;

/**
 *
 */
public class SuccessTip extends Tip {

    public SuccessTip(){
        super.state = 1;
        super.message = "操作成功";
    }

    public SuccessTip(String result){
        super.state = 1;
        super.message = result;
    }


    public  SuccessTip(Object data){
        super.state = 1;
        super.message = "操作成功";
        super.data = data;
    }


}
