package com.xzx.dao;

import com.xzx.model.Otherparty;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
public interface OtherpartyMapper extends BaseMapper<Otherparty> {
	@Insert({ "insert into otherparty(OtherpartyName, RelationsUser, RelationsPhone, RelationsEmail,RelationsAddress,RelationPlace,OnlineType,SFZH,OtherDepartNAME,OtherDepartPhone,OtherDepartAddress,WorkDepart) "
    		+ "values(#{OtherpartyName}, #{RelationsUser}, #{RelationsPhone}, #{RelationsEmail},#{RelationsAddress},#{RelationPlace},#{OnlineType},#{SFZH},#{OtherDepartNAME},#{OtherDepartPhone},#{OtherDepartAddress},#{WorkDepart})" })
    @Options(useGeneratedKeys = true, keyProperty = "OtherpartyId")
	int addOtherParty(Otherparty otherparty);
}
