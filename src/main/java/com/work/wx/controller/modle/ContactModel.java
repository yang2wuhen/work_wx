/*
 * work_wx
 * wuhen 2020/2/10.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;

import com.google.gson.JsonObject;

import java.util.Arrays;

public class ContactModel {
    private String corp;

    private String userid;      //	成员UserID。对应管理端的帐号
    private String name;        //	成员名称，此字段从2019年12月30日起，对新创建第三方应用不再返回，2020年6月30日起，对所有历史第三方应用不再返回，
                                // 后续第三方仅通讯录应用可获取，第三方页面需要通过通讯录展示组件来展示名字
    private String mobile;	    // 手机号码，第三方仅通讯录应用可获取
    private Integer[]  department;	// 成员所属部门id列表，仅返回该应用有查看权限的部门id
    private Integer[]  order;         //	部门内的排序值，32位整数，默认为0。数量必须和department一致，数值越大排序越前面。
    private String position;    //	职务信息；第三方仅通讯录应用可获取
    private String gender;      //	性别。0表示未定义，1表示男性，2表示女性
    private String email;       //	邮箱，第三方仅通讯录应用可获取
    private Integer[] is_leader_in_dept;    //	表示在所在的部门内是否为上级；第三方仅通讯录应用可获取
    private String avatar;      //	头像url。第三方仅通讯录应用可获取
    private String thumb_avatar;    //	头像缩略图url。第三方仅通讯录应用可获取
    private String telephone;   //	座机。第三方仅通讯录应用可获取
    private Integer enable;            //	成员启用状态。1表示启用的成员，0表示被禁用。服务商调用接口不会返回此字段
    private String alias;           //	别名；第三方仅通讯录应用可获取
    private Integer    status;          //	激活状态: 1=已激活，2=已禁用，4=未激活 已激活代表已激活企业微信或已关注微工作台（原企业号）。
                                    // 未激活代表既未激活企业微信又未关注微工作台（原企业号）。
    private JsonObject extattr;     //	扩展属性，第三方仅通讯录应用可获取
    private String  qr_code;        //	员工个人二维码，扫描可添加为外部联系人；第三方仅通讯录应用可获取
    private String  external_profile;   //	成员对外属性，字段详情见对外属性；第三方仅通讯录应用可获取
    private JsonObject  external_position;  //	对外职务。 第三方仅通讯录应用可获取
    private String address;         //	地址
    private Integer  hide_mobile;       //	是否隐藏手机号
    private String english_name;        //	英文名


    public String getUserid() {
        return userid;
    }

    public Integer[] getDepartment() {
        return department;
    }

    public void setDepartment(Integer[] department) {
        this.department = department;
    }

    public Integer[] getOrder() {
        return order;
    }

    public void setOrder(Integer[] order) {
        this.order = order;
    }

    public Integer[] getIs_leader_in_dept() {
        return is_leader_in_dept;
    }

    public void setIs_leader_in_dept(Integer[] is_leader_in_dept) {
        this.is_leader_in_dept = is_leader_in_dept;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setHide_mobile(Integer hide_mobile) {
        this.hide_mobile = hide_mobile;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getThumb_avatar() {
        return thumb_avatar;
    }

    public void setThumb_avatar(String thumb_avatar) {
        this.thumb_avatar = thumb_avatar;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public JsonObject getExtattr() {
        return extattr;
    }

    public void setExtattr(JsonObject extattr) {
        this.extattr = extattr;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getExternal_profile() {
        return external_profile;
    }

    public void setExternal_profile(String external_profile) {
        this.external_profile = external_profile;
    }

    public JsonObject getExternal_position() {
        return external_position;
    }

    public void setExternal_position(JsonObject external_position) {
        this.external_position = external_position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHide_mobile() {
        return hide_mobile;
    }

    public void setHide_mobile(int hide_mobile) {
        this.hide_mobile = hide_mobile;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "corp='" + corp + '\'' +
                ", userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", department=" + Arrays.toString(department) +
                ", order=" + Arrays.toString(order) +
                ", position='" + position + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", is_leader_in_dept=" + Arrays.toString(is_leader_in_dept) +
                ", avatar='" + avatar + '\'' +
                ", thumb_avatar='" + thumb_avatar + '\'' +
                ", telephone='" + telephone + '\'' +
                ", enable=" + enable +
                ", alias='" + alias + '\'' +
                ", status=" + status +
                ", extattr=" + extattr +
                ", qr_code='" + qr_code + '\'' +
                ", external_profile='" + external_profile + '\'' +
                ", external_position=" + external_position +
                ", address='" + address + '\'' +
                ", hide_mobile=" + hide_mobile +
                ", english_name='" + english_name + '\'' +
                '}';
    }

    public ContactModel() {
    }

    public ContactModel(String corp, String userid) {
        this.corp = corp;
        this.userid = userid;
    }

    public ContactModel(String corp) {
        this.corp = corp;
    }
}
