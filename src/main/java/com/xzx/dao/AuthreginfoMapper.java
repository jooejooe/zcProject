package com.xzx.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xzx.model.Authreginfo;
import com.xzx.model.Zcdw;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-04-23
 */
public interface AuthreginfoMapper extends BaseMapper<Authreginfo> {

//    @Insert({ "insert into authreginfo(registerId, sfid, isDw, dwId, dbId) "
//            + "values(#{registerId}, #{sfid}, #{isDw}, #{dwId}, #{dbId})" })
//    @Options(useGeneratedKeys=true, keyProperty="authregId", keyColumn="authregId")
    int insertinfo(Authreginfo authreginfo);

}
