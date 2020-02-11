/*
 * work_wx
 * wuhen 2020/2/11.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class LangUtil {

    public static byte[] getBytes(InputStream inputStream) throws  Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int  i = 0;
        while (-1!=(i=inputStream.read(b))){
            bos.write(b,0,i);
        }
        return bos.toByteArray();
    }



}
