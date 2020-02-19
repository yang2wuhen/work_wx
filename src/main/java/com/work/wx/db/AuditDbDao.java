/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.db;

import com.work.wx.controller.modle.AuditModel;
import com.work.wx.controller.modle.ChatModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AuditDbDao extends MongoDbDao {

    @Override
    protected Class<AuditModel> getEntityClass() {
        return AuditModel.class;
    }

    /**
     * @todo
     * @author wuhen
     * @param chatModel :
     * @param groupField :
     * @param orderField :
     * @returns java.util.List<java.lang.String>
     * @throws
     * @date 2020/2/19 15:57
     */
    public List<String> queryGroupBy(AuditModel auditModel, String groupField, String orderField) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(getCriteriaByObject(auditModel)),
                Aggregation.sort(Sort.Direction.DESC, orderField),
                Aggregation.group(groupField));
        return getMongoTemplate().aggregate(aggregation, getEntityClass(),String.class).getMappedResults();
    }




}
