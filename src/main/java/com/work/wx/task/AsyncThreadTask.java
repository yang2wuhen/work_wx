/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class AsyncThreadTask {

    @Async
    public void MediaFile(String file)  {

    }


}
