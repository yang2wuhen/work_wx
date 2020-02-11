/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.db;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.work.wx.controller.modle.FileModel;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import java.io.InputStream;


@Repository
public class MongoGridFSDao {

    protected Logger logger = LoggerFactory.getLogger(MongoGridFSDao.class);

    private GridFsTemplate gridFsTemplate;

    private GridFSBucket gridFSBucket;

    @Autowired
    public void setGridFSBucket(GridFSBucket gridFSBucket) {
        this.gridFSBucket = gridFSBucket;
    }

    @Autowired
    public void setGridFsTemplate(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    public GridFSUploadStream save(FileModel fileModel) {
        GridFSUploadOptions gridFSUploadOptions = new GridFSUploadOptions().chunkSizeBytes(fileModel.getLength().intValue()).
                metadata(new Document("contentType",fileModel.getFileType()));
        return gridFSBucket.openUploadStream(fileModel.getFileName(),gridFSUploadOptions);
    }


    public void getFile(String fileName) {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
        if(file != null){
            GridFSDownloadStream in = gridFSBucket.openDownloadStream(file.getObjectId());
            GridFsResource resource = new GridFsResource(file,in);
            try {
                InputStream inputStream = resource.getInputStream();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

