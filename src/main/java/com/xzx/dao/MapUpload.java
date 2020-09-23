package com.xzx.dao;

import com.xzx.service.impl.DynUpload;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface MapUpload {
    //图片更新
    @SelectProvider(type=DynUpload.class,method="imageUpload")
    void  imageUpload(String strSql);
    @SelectProvider(type=DynUpload.class,method="PathUpdate")
    void  PathUpdate(String strSql);
}