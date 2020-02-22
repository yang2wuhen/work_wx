/*
 * work_wx
 * wuhen 2020/2/15.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;

import java.util.List;

public class ExtendContactModel extends BaseModel{

    private String corpId;
    private List<FollowUser> follow_user;
    private ExternalUser external_contact;
    private String external_userid;
    private Long updateTime;


    class ExternalUser {
        private String external_userid;             //	外部联系人的userid
        private String name;                        //	外部联系人的名称*
        private String avatar;                      //avatar	外部联系人头像，第三方不可获取
        private Integer type;                           //	外部联系人的类型，1表示该外部联系人是微信用户，2表示该外部联系人是企业微信用户
        private Integer gender;                         //	外部联系人性别 0-未知 1-男性 2-女性
        private String unionid;                     //	外部联系人在微信开放平台的唯一身份标识（微信unionid），通过此字段企业可将外部联系人与公众号/小程序用户关联起                                                   // 仅当联系人类型是微信用户，且企业或第三方服务商绑定了微信开发者ID有此字段。查看绑定方法
        private String position;                    //	外部联系人的职位，如果外部企业或用户选择隐藏职位，则不返回，仅当联系人类型是企业微信用户时有此字段
        private String corp_name;                   //	外部联系人所在企业的简称，仅当联系人类型是企业微信用户时有此字段
        private String corp_full_name;              //	外部联系人所在企业的主体名称，仅当联系人类型是企业微信用户时有此字段
        private String external_profile;            //	外部联系人的自定义展示信息，可以有多个字段和多种类型，包括文本，网页和小程序，仅当联系人类型是企业微信用户时有此
        // 字段 ，字段详情见对外属性；

        public String getExternal_userid() {
            return external_userid;
        }

        public void setExternal_userid(String external_userid) {
            this.external_userid = external_userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getCorp_name() {
            return corp_name;
        }

        public void setCorp_name(String corp_name) {
            this.corp_name = corp_name;
        }

        public String getCorp_full_name() {
            return corp_full_name;
        }

        public void setCorp_full_name(String corp_full_name) {
            this.corp_full_name = corp_full_name;
        }

        public String getExternal_profile() {
            return external_profile;
        }

        public void setExternal_profile(String external_profile) {
            this.external_profile = external_profile;
        }

    }



     class FollowUser {
        private String userid;                  //	添加了此外部联系人的企业成员userid
        private String remark;                  //	该成员对此外部联系人的备注
        private String description;             //	该成员对此外部联系人的描述
        private String createtime;              //	该成员添加此外部联系人的时间
        private String remark_corp_name;        //	该成员对此客户备注的企业名称
        private String[] remark_mobiles;        //	该成员对此客户备注的手机号码，第三方不可获取
        private String state;                   //	该成员添加此客户的渠道，由用户通过创建「联系我」方式指定
        private List<Tags> tags;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getRemark_corp_name() {
            return remark_corp_name;
        }

        public void setRemark_corp_name(String remark_corp_name) {
            this.remark_corp_name = remark_corp_name;
        }

        public String[] getRemark_mobiles() {
            return remark_mobiles;
        }

        public void setRemark_mobiles(String[] remark_mobiles) {
            this.remark_mobiles = remark_mobiles;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

         public List<Tags> getTags() {
             return tags;
         }

         public void setTags(List<Tags> tags) {
             this.tags = tags;
         }

         class Tags {

            private String group_name;          //	该成员添加此外部联系人所打标签的分组名称（标签功能需要企业微信升级到2.7.5及以上版本）
            private String tag_name;            //	该成员添加此外部联系人所打标签名称
            private Integer type;                   //	该成员添加此外部联系人所打标签类型, 1-企业设置, 2-用户自定义

            public String getGroup_name() {
                return group_name;
            }

            public void setGroup_name(String group_name) {
                this.group_name = group_name;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public Integer getType() {
                return type;
            }

            public void setType(Integer type) {
                this.type = type;
            }
        }
    }

    public String getExternal_userid() {
        return external_userid;
    }

    public void setExternal_userid(String external_userid) {
        this.external_userid = external_userid;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public List<FollowUser> getFollow_user() {
        return follow_user;
    }

    public void setFollow_user(List<FollowUser> follow_user) {
        this.follow_user = follow_user;
    }

    public ExternalUser getExternal_contact() {
        return external_contact;
    }

    public void setExternal_contact(ExternalUser external_contact) {
        this.external_contact = external_contact;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public ExtendContactModel() {
    }


    public ExtendContactModel(String corpId, String external_userid) {
        this.corpId = corpId;
        this.external_userid = external_userid;
    }


    public ExtendContactModel(String corpId) {
        this.corpId = corpId;
    }
}
