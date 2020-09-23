package com.xzx.sqlProvider;

import java.util.Map;

public class AssistanceSqlProvider {
	public String getAssistanceList(Map<String, Object> para)
	{
		String assistancetype=para.get("assistancetype").toString();//0 援助类型 1 仲裁类别 2 鉴定类别
		String userId=para.get("userId").toString();
		String modelType=para.get("modelType").toString();
		String isSubscribe=para.get("isSubscribe").toString();//0-预约；1-在线
		String subscribeModelType=isSubscribe.equals("0")?Integer.toString(Integer.parseInt(modelType)-4):"-1";
		
		String sqlStr="";
		if(assistancetype.equals("0"))
		{
			sqlStr="select *,(select count(1) from lawhelp a left join speed b"+
				   " on a.LawHelpId=b.ModelId"+
				   " where UserId="+userId+" and assistanceId=aa.assistanceId and ModelType="+modelType+" and FIND_IN_SET(State,'0,1,3')) as useState"+
				   " from assistance aa"+
				   " where state=0 and assistancetype="+assistancetype+" order by createdate desc";
		}
		else if(assistancetype.equals("1"))
		{
			sqlStr="select *,(select count(1) from authentic a left join speed b"+
					" on a.AuthenticId=b.ModelId"+
					" where UserId="+userId+" and assistanceId=aa.assistanceId and ModelType="+modelType+" and FIND_IN_SET(State,'0,1,3')) as useState"+
					" from assistance aa"+
					" where state=0 and assistancetype="+assistancetype+" order by createdate desc";
		}
		else if(assistancetype.equals("2"))
		{
			if(isSubscribe.equals("1"))
			{
				sqlStr="select *,(select count(1) from appraisal a left join speed b"+
						   " on a.AppraisalId=b.ModelId"+
						   " where UserId="+userId+" and MatterType=aa.assistanceId and b.ModelType="+modelType+" and FIND_IN_SET(State,'0,1,3')) as useState"+
						   " from assistance aa"+
						   " where state=0 and assistancetype="+assistancetype+" order by createdate desc";
			}
			else 
			{
				sqlStr="select *,(select count(1) from subscribe a left join speed b"+
						" on a.subscribeId=b.ModelId"+
						" where UserId="+userId+" and subscribetype=aa.assistanceId and a.ModelType="+subscribeModelType+" and b.ModelType="+modelType+" and FIND_IN_SET(State,'0,3,4')) as useState"+
						" from assistance aa"+
						" where state=0 and assistancetype="+assistancetype+" order by createdate desc";
			}
		}
		
		return sqlStr;
	}
}
