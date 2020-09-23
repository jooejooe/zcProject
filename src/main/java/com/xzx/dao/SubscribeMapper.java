package com.xzx.dao;

import com.xzx.model.Subscribe;
import com.xzx.viewModel.ItemDetailInfo;

import java.util.List;

/*import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;*/
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
 * @since 2019-09-26
 */
public interface SubscribeMapper extends BaseMapper<Subscribe> {
/*	@Insert({ "insert into subscribe(RegionId, DepartMentId, fairworkerId, subscribeType,UserId,subscribe,CreateDate,ModelType,Context,applyType,authenticateType) "
    		+ "values(#{RegionId}, #{DepartMentId}, #{fairworkerId}, #{subscribeType},#{UserId},#{subscribe},now(),#{ModelType},#{Context},#{applyType},#{authenticateType})" })
    @Options(useGeneratedKeys = true, keyProperty = "subscribeId")*/
    int addSubscribe(Subscribe subscribe);
	
	List<ItemDetailInfo> getItemInfoById(@Param("subscribeId") String subscribeId);//,@Param("userId") String userId
	
	@Update("update subscribe set subscribe=#{newTime} where subscribeId=#{subscribeId}")
	int updateSubscribeTime(@Param("subscribeId") String subscribeId,@Param("newTime") String newTime);
	
	@Select("select * from subscribe where subscribeId=#{subscribeId}")
	Subscribe getSubscribeById(@Param("subscribeId") String subscribeId);
	
	@Select("select count(1) from subscribe a left join speed b on a.subscribeId=b.ModelId where UserId=#{userId} and subscribetype=#{subscribeType} and a.ModelType=#{modelType} and b.ModelType=#{speedModelType} and FIND_IN_SET(State,'0,3,4')")
	int getUseCount(@Param("userId") int userId,@Param("subscribeType") int subscribeType,@Param("modelType") int modelType,@Param("speedModelType") int speedModelType);
}
