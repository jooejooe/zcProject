package com.xzx.dao;

import com.xzx.service.impl.DynSystj;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MapSystjxx {
    //查询字典

    @SelectProvider(type=DynSystj.class,method="Operation")
    List<Map<String, Object>> objectList(String strSql);

    @SelectProvider(type=DynSystj.class,method="Operation")
    Map<String, Object> objectOne(String strSql);

    @SelectProvider(type=DynSystj.class,method="Operation")
    void  objectVoid(String strSql);

    @SelectProvider(type=DynSystj.class,method="Operation")
    String  objectStr(String strSql);

    @SelectProvider(type=DynSystj.class,method="Operation")
    int  objectInt(String strSql);


}
