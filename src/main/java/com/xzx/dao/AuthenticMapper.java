package com.xzx.dao;

import com.xzx.model.Authentic;
import com.xzx.viewModel.AllPqInfo;
import com.xzx.viewModel.AuthenticDetailInfo;
import com.xzx.viewModel.CaseAssignInfos;
import com.xzx.viewModel.ItemDetailInfo;

import java.util.List;
import java.util.Map;

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
 * @since 2019-09-25
 */
public interface AuthenticMapper extends BaseMapper<Authentic> {
	@Insert({ "insert into authentic(AuthDepartId, UserId, OtherPartyId, SummaryContext,Context,CreateDate,RegionId,assistanceId) "
    		+ "values(#{AuthDepartId}, #{UserId}, #{OtherPartyId}, #{SummaryContext},#{Context},now(),#{RegionId},#{assistanceId})" })
    @Options(useGeneratedKeys = true, keyProperty = "AuthenticId")
	int addAuthentic(Authentic authentic);
	
	List<ItemDetailInfo> getItemInfoById(@Param("authenticId") String authenticId);//,@Param("userId") String userId

	@Select("select count(1) from authentic a left join speed b on a.AuthenticId=b.ModelId where UserId=#{userId} and assistanceId=#{applyType} and ModelType=1 and FIND_IN_SET(State,'0,1,3')")
	int getUseCount(@Param("userId")String userId,@Param("applyType")String applyType);
	
	/**
	 * 获取案件指派（主任端）案件列表
	 * @param regionId
	 * @return
	 */
	List<CaseAssignInfos> getAssignCaseList(@Param("regionId")String regionId,@Param("caseType")String caseType,@Param("spState")String spState,@Param("starttime")String starttime,@Param("endtime")String endtime,@Param("dsrSFZH")String dsrSFZH,@Param("operateType")String operateType,@Param("isOnline")int isOnline,@Param("orderType")String orderType);
	List<CaseAssignInfos> getAssignCaseListZc(@Param("regionId")String regionId,@Param("caseType")String caseType,@Param("caseTypeSecond")String caseTypeSecond,@Param("spState")String spState,@Param("starttime")String starttime,@Param("endtime")String endtime,@Param("dsrSFZH")String dsrSFZH,@Param("operateType")String operateType,@Param("isOnline")int isOnline,@Param("orderType")String orderType);

	/**
	 * 根据仲裁申办事项id获取仲裁申办事项相关具体信息
	 * @param caseId
	 * @return
	 */
//	@Select("SELECT UserId,OtherPartyId,UserContext,OtherContext,IFNULL(WorkerName,'未指派') WorkerName,AuthenticId,"+
//			"(select State from speed where ModelType=1 and ModelId=a.AuthenticId) State,"+
//			"tjbl,xcsplzurl,"+
////			"js_tjjacgfkh,js_rmtjxys,js_tjbl,js_sdsx,zh_tjbl,zh_tjjabcgfkh,ssyd_tjbl,ssyd_tjjabcgfkh,"+
//			"d.bh as jzbh,e.pqstart,e.pqend,d.jzId,TJLZYY"+
//			" FROM authentic a left join fairworker b"+
//			" on a.FairworkerId=b.FairWorkerId"+
//			" left join fy_sp c"+
//			" on a.AuthenticId=c.fy_ajId"+
//			" left join dzjz d"+
//			" on a.AuthenticId=d.ajId and type=1 and jztyle=2"+
//			" left join pq e"+
//			" on a.AuthenticId=e.` ajId`"+
//			" where AuthenticId=#{caseId}")
	@Select("SELECT ari.dbId UserId,bari.dbId OtherPartyId,UserContext,OtherContext,IFNULL(awi.workerids,'未指派') WorkerName,AuthenticId,  " +
			"      (select State from speed where ModelType=1 and ModelId=a.AuthenticId) State,   " +
			"      tjbl,xcsplzurl, d.bh as jzbh,e.pqstart,e.pqend,d.jzId,TJLZYY  " +
			"FROM authentic a   " +
			"left join authreginfo ari on a.sqfinfoId = ari.authregId  " +
			"left join authreginfo bari on a.bsqfinfoId = bari.authregId  " +
			"left join authworkerinfo awi on a.FairworkerId=awi.id  " +
			"  " +
			"left join fy_sp c  " +
			"on a.AuthenticId=c.fy_ajId  " +
			"left join dzjz d  " +
			"on a.AuthenticId=d.ajId and type=1 and jztyle=2  " +
			"left join pq e  " +
			"on a.AuthenticId=e.` ajId`  " +
			"where a.AuthenticId=#{caseId}")
	AuthenticDetailInfo getAuthenticById(@Param("caseId")String caseId);
	
	@Update("update authentic set FairworkerId=#{FairworkerId},zprId=#{zprId},zpsj=now() where AuthenticId=#{AuthenticId}")
	int assignAuthentic(@Param("FairworkerId") String FairworkerId,@Param("AuthenticId") String AuthenticId,@Param("zprId")String zprId);

	@Update("update authentic set zprId=#{zprId},zpsj=now() where AuthenticId=#{AuthenticId}")
	int assignAuthenticZc(@Param("AuthenticId") String AuthenticId,@Param("zprId")String zprId);
	
	@Update("update authentic set SPSJ=now() where AuthenticId=#{AuthenticId}")
	int spAuthentic(@Param("AuthenticId") String AuthenticId);
	
	@Update("update authentic set tjbl=#{tjbl} where AuthenticId=#{AuthenticId}")
	int tjblAuthentic(@Param("tjbl") String tjbl,@Param("AuthenticId") String AuthenticId);
	
	@Update("update authentic set QZSJ=now() where AuthenticId=#{AuthenticId}")
	int qzAuthentic(@Param("AuthenticId") String AuthenticId);
	
	@Update("update authentic set TJLZYY=#{tjlzyy},TJLZSJ=now() where AuthenticId=#{AuthenticId}")
	int tjlzAuthentic(@Param("tjlzyy") String tjlzyy,@Param("AuthenticId") String AuthenticId);
	
	int caseAssginAgain(Map<String, Object> param);
	
	@Select("select UserId,OtherPartyId from authentic where AuthenticId=#{caseId}")
	Authentic getCaseInfo(@Param("caseId")String caseId);
	
	List<AllPqInfo> getAllPqList(@Param("departId")String departId);

	int insertAuthInfo(Authentic authentic);
}
