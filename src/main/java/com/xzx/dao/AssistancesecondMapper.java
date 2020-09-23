package com.xzx.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xzx.model.Assistance;
import com.xzx.model.Assistancesecond;
import com.xzx.model.Zcdw;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-04-23
 */
public interface AssistancesecondMapper extends BaseMapper<Assistancesecond> {

    @Select("select * from assistancesecond where state=0 and parentid=#{assistanceId} order by createtime desc")
    List<Assistancesecond> getSecondAssistanceList(@Param("assistanceId")int assistanceId);

}
