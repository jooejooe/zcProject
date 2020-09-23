package com.xzx.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xzx.model.Access_bczj;
import com.xzx.model.Zcdw;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-04-23
 */
public interface ZcdwMapper extends BaseMapper<Zcdw> {

//    @Insert({ "insert into zcdw(dwmc, fddbr, zw, dzxx, zcqq) "
//            + "values(#{dwmc}, #{fddbr}, #{zw}, #{dzxx}, #{zcqq})" })
//    @Options(useGeneratedKeys=true, keyProperty="zcdwId", keyColumn="zcdwId")
    int insertZcdwInfo(Zcdw zcdw);

}
