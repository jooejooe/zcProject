package com.xzx.dao;

import com.xzx.model.Pq;
import com.xzx.viewModel.PqInfos;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-04-24
 */
public interface PqMapper extends BaseMapper<Pq> {
	@Insert({ "insert into pq(` ajId`, pqstart, pqend, fairworkerId) "
    		+ "values(#{ajId}, #{pqstart}, #{pqend}, #{fairworkerId})" })
	@Options(useGeneratedKeys = true, keyProperty = "pqId")
    int addPq(Pq pq);
	
	@Select("select * from pq where ` ajId`=#{caseId}")
	Pq getPqByCaseId(@Param("caseId")String caseId);
	
	@Update("update pq set pqstart=#{pqstart},pqend=#{pqend} where ` ajId`=#{ajId}")
	int updatePq(Pq pq);
	
	List<PqInfos> getPqListByUser(@Param("userId")String userId);
	
	List<PqInfos> getAllPqListByUser(@Param("FairworkerId")String FairworkerId,@Param("ajId")String ajId);
	
	@Select("select count(1) from pq where ` ajId`<>#{ajId} and fairworkerId=#{fairworkerId} and pqstart>now()"+
			"and ((pqstart<=#{pqStart} and pqend>=#{pqStart}) "+
			"or (pqstart<=#{pqEnd} and pqend>=#{pqEnd}) "+
			"or (pqstart>=#{pqStart} and pqend<=#{pqEnd}))")
	int getExistPqCount(@Param("ajId")String ajId,@Param("fairworkerId")String fairworkerId,@Param("pqStart")String pqStart,@Param("pqEnd")String pqEnd);
}
