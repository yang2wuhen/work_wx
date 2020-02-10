/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.db;

import com.google.gson.internal.Primitives;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * 描述:
 * mongoDB基础方法封装
 * @author zhengql
 * @date 2018/8/9 11:01
 */
public abstract class MongoDbDao<T> {

    protected Logger logger = LoggerFactory.getLogger(MongoDbDao.class);

    /**
     * 反射获取泛型类型
     *
     * @return
     */
    protected abstract Class<T> getEntityClass();

    @Autowired
    private MongoTemplate mongoTemplate;



    public void insert(T t) {
        this.mongoTemplate.insert(t);
    }


    public void insertAll(Collection<? extends T> t) {
        this.mongoTemplate.insertAll(t);
    }


    /***
     * 保存一个对象
     * @param t
     */
    public void save(T t) {
        this.mongoTemplate.save(t);
    }

    /***
     * 根据id从几何中查询对象
     * @param id
     * @return
     */
    public T queryById(Integer id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 根据条件查询集合
     *
     * @param object
     * @return
     */
    public List<T> queryList(T object) {
        Query query = getQueryByObject(object);
        return mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 根据条件查询集合
     *
     * @param object
     * @return
     */
    public List<T> queryList(T object,Set noEqualsFields) {
        Query query = getQueryByObject(object,noEqualsFields);
        return mongoTemplate.find(query, this.getEntityClass());
    }


    /**
     * 根据条件查询只返回一个文档
     *
     * @param object
     * @return
     */
    public T queryOne(T object) {
        Query query = getQueryByObject(object);
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 根据条件查询只返回一个文档
     *
     * @param object
     * @return
     */
    public T queryOneDesc(T object,String field) {
        Query query = getQueryByObjectDesc(object,field);
        logger.debug(query.toString());
        return mongoTemplate.findOne(query, this.getEntityClass());
    }


    /***
     * 根据条件分页查询
     * @param object
     * @param start 查询起始值
     * @param size  查询大小
     * @return
     */
    public List<T> getPage(T object, int start, int size) {
        Query query = getQueryByObject(object);
        query.skip(start);
        query.limit(size);
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    /***
     * 根据条件查询库中符合条件的记录数量
     * @param object
     * @return
     */
    public Long getCount(T object) {
        Query query = getQueryByObject(object);
        return this.mongoTemplate.count(query, this.getEntityClass());
    }

    /***
     * 删除对象
     * @param t
     * @return
     */
    public int delete(T t) {
        return (int) this.mongoTemplate.remove(t).getDeletedCount();
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    public void deleteById(Integer id) {
        Criteria criteria = Criteria.where("_id").is(id);
        if (null != criteria) {
            Query query = new Query(criteria);
            T obj = this.mongoTemplate.findOne(query, this.getEntityClass());
            if (obj != null) {
                this.delete(obj);
            }
        }
    }

    /*MongoDB中更新操作分为三种
     * 1：updateFirst     修改第一条
     * 2：updateMulti     修改所有匹配的记录
     * 3：upsert  修改时如果不存在则进行添加操作
     * */

    /**
     * 修改匹配到的第一条记录
     * @param srcObj
     * @param targetObj
     */
    public long updateFirst(T srcObj, T targetObj){
        Query query = getQueryByObject(srcObj);
        Update update = getUpdateByObject(targetObj);
        return this.mongoTemplate.updateFirst(query,update,this.getEntityClass()).getModifiedCount();
    }

    /***
     * 修改匹配到的所有记录
     * @param srcObj
     * @param targetObj
     */
    public long updateMulti(T srcObj, T targetObj){
        Query query = getQueryByObject(srcObj);
        Update update = getUpdateByObject(targetObj);
        return this.mongoTemplate.updateMulti(query,update,this.getEntityClass()).getModifiedCount();
    }

    /***
     * 修改匹配到的记录，若不存在该记录则进行添加
     * @param srcObj
     * @param targetObj
     */
    public long updateInsert(T srcObj, T targetObj){
        Query query = getQueryByObject(srcObj);
        Update update = getUpdateByObject(targetObj);
        logger.error(query.toString());
        return this.mongoTemplate.upsert(query,update,this.getEntityClass()).getModifiedCount();
    }



    /**
     * 将查询条件对象转换为query
     *
     * @param object
     * @return
     * @author Jason
     */
    private Query getQueryByObject(T object) {
        Query query = new Query();
        String[] fileds = getFiledName(object);
        Criteria criteria = new Criteria();
        for (int i = 0; i < fileds.length; i++) {
            String filedName = (String) fileds[i];
            if (filedName.equals("Fields") || filedName.equals("Sort")) {
                continue;
            }
            Object filedValue = getFieldValueByName(filedName, object);
            if (filedValue != null) {
                criteria.and(filedName).is(filedValue);
            }
        }
        query.addCriteria(criteria);
        return query;
    }



    private Query getQueryByObject(T object,Set noEqualsFields) {
        Query query = new Query();
        String[] fileds = getFiledName(object);
        Criteria criteria = new Criteria();
        for (int i = 0; i < fileds.length; i++) {
            String filedName = (String) fileds[i];
            if (filedName.equals("Fields") || filedName.equals("Sort")) {
                continue;
            }
            Object filedValue = getFieldValueByName(filedName, object);
            if (filedValue != null) {
                if (noEqualsFields.contains(fileds)) {
                    criteria.and(filedName).ne(filedValue);
                } else {
                    criteria.and(filedName).is(filedValue);
                }
            }
        }
        query.addCriteria(criteria);
        return query;
    }

    /**
     * 将查询条件对象转换为update
     *
     * @param object
     * @return
     * @author Jason
     */
    private Update getUpdateByObject(T object) {
        Update update = new Update();
        String[] fileds = getFiledName(object);
        for (int i = 0; i < fileds.length; i++) {
            String filedName = (String) fileds[i];
            Object filedValue =getFieldValueByName(filedName, object);
            if (filedValue != null) {
                update.set(filedName, filedValue);
            }
        }
        return update;
    }

    /***
     * 获取对象属性返回字符串数组
     * @param o
     * @return
     */
    private static String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];

        for (int i = 0; i < fields.length; ++i) {
            fieldNames[i] = fields[i].getName();
        }

        return fieldNames;
    }

    /***
     * 根据属性获取对象属性值
     * @param fieldName
     * @param o
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        String e = fieldName.substring(0, 1).toUpperCase();
        try {
            String getter = "get" + e + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[0]);
            return method.invoke(o, new Object[0]);
        } catch (Exception var6) {
            try {
                String is = "is" + e + fieldName.substring(1);
                Method method = o.getClass().getMethod(is, new Class[0]);
                return method.invoke(o, new Object[0]);
            } catch (Exception e1) {
                return null;
            }
        }
    }



    private Query getQueryByObjectDesc(T object,String field) {
        Query query = getQueryByObject(object);
        query.with(Sort.by(Sort.Order.desc(field)));
        return query;
    }



}

