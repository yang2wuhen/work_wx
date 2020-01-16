/*
 * work_wx
 * wuhen 2020/1/2.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import java.util.List;

public interface BackUpServer  {


    /**
     * 拉取聊天记录函数
     * Return值=0表示该API调用成功
     *
     *
     * @param [in]  sdk             NewSdk返回的sdk指针
     * @param [in]  seq             从指定的seq开始拉取消息，注意的是返回的消息从seq+1开始返回，seq为之前接口返回的最大seq值。首次使用请使用seq:0
     * @param [in]  limit           一次拉取的消息条数，最大值1000条，超过1000条会返回错误
     * @param [in]  proxy           使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081
     * @param [in]  passwd          代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123
     * @param [in]  timeout         超时时间，单位秒
     * @param [out] chatDatas       返回本次拉取消息的数据，slice结构体.内容包括errcode/errmsg，以及每条消息内容。


     *
     * @return 返回是否调用成功
     *      0   - 成功
     *      !=0 - 失败
     */
    public List getChatData(long seq, long limit, long timeout);



    /**
     * 拉取媒体消息函数
     * Return值=0表示该API调用成功
     *
     *
     * @param [in]  sdk             NewSdk返回的sdk指针
     * @param [in]  sdkFileid       从GetChatData返回的聊天消息中，媒体消息包括的sdkfileid
     * @param [in]  proxy           使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081
     * @param [in]  passwd          代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123
     * @param [in]  timeout         超时时间，单位秒
     * @param [in]  indexbuf        媒体消息分片拉取，需要填入每次拉取的索引信息。首次不需要填写，默认拉取512k，后续每次调用只需要将上次调用返回的outindexbuf填入即可。
     * @param [out] media_data      返回本次拉取的媒体数据.MediaData结构体.内容包括data(数据内容)/outindexbuf(下次索引)/is_finish(拉取完成标记)

     *
     * @return 返回是否调用成功
     *      0   - 成功
     *      !=0 - 失败
     */
    public long getMediaData(String indexbuf, String sdkField,long timeout);

}
