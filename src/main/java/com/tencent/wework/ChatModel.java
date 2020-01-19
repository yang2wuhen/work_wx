/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.tencent.wework;

import org.apache.tomcat.util.modeler.BaseModelMBean;

public class ChatModel {

    private Integer seq;
    private String msgid;
    private Integer publickey_ver;
    private String encrypt_random_key;
    private String encrypt_chat_msg;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public Integer getPublickey_ver() {
        return publickey_ver;
    }

    public void setPublickey_ver(Integer publickey_ver) {
        this.publickey_ver = publickey_ver;
    }

    public String getEncrypt_random_key() {
        return encrypt_random_key;
    }

    public void setEncrypt_random_key(String encrypt_random_key) {
        this.encrypt_random_key = encrypt_random_key;
    }

    public String getEncrypt_chat_msg() {
        return encrypt_chat_msg;
    }

    public void setEncrypt_chat_msg(String encrypt_chat_msg) {
        this.encrypt_chat_msg = encrypt_chat_msg;
    }

    @Override
    public String toString() {
        return "ChatModel{" +
                "seq=" + seq +
                ", msgid='" + msgid + '\'' +
                ", publickey_ver=" + publickey_ver +
                ", encrypt_random_key='" + encrypt_random_key + '\'' +
                ", encrypt_chat_msg='" + encrypt_chat_msg + '\'' +
                '}';
    }
}
