package com.xzx.viewModel;

import io.swagger.annotations.ApiModelProperty;

public class AuthenticDetailInfo {
	/**
	 * 申请方当事人id
	 */
	@ApiModelProperty("申请方当事人id")
	private String UserId;
	
	/**
	 * 被申请方当事人id
	 */
	@ApiModelProperty("被申请方当事人id")
	private String OtherPartyId;
	
	/**
	 * 申请方当事人陈述简要案情
	 */
	@ApiModelProperty("申请方当事人陈述简要案情")
	private String UserContext;
	
	/**
	 * 被申请方当事人陈述简要案情
	 */
	@ApiModelProperty("被申请方当事人陈述简要案情")
	private String OtherContext;
	
	/**
	 * 指派仲裁员
	 */
	@ApiModelProperty("指派仲裁员")
	private String WorkerName;
	
	/**
	 * 仲裁申办事项id
	 */
	@ApiModelProperty("仲裁申办事项id")
	private String AuthenticId;
	
	/**
	 * 审批状态
	 */
	@ApiModelProperty("审批状态")
	private String State;
	
	/**
	 * 审批状态文字描述
	 */
	@ApiModelProperty("审批状态文字描述")
	private String StateText;
	
	@ApiModelProperty("仲裁笔录")
	private String tjbl;
	
	@ApiModelProperty("仲裁视频")
	private String xcsplzurl;

	/**
	 * 接受_仲裁结案（撤销）反馈函
	 */
	@ApiModelProperty("接受_仲裁结案（撤销）反馈函")
	private String js_tjjacgfkh;
	
	/**
	 * 接受_人民仲裁协议书
	 */
	@ApiModelProperty("接受_人民仲裁协议书")
	private String js_rmtjxys;
	
	/**
	 * 接受_仲裁笔录
	 */
	@ApiModelProperty("接受_仲裁笔录")
	private String js_tjbl;
	
	/**
	 * 接受_送达手续
	 */
	@ApiModelProperty("接受_送达手续")
	private String js_sdsx;
		
	/**
	 * 转回_仲裁笔录
	 */
	@ApiModelProperty("转回_仲裁笔录")
	private String zh_tjbl;
	
	/**
	 * 转回_仲裁结案(仲裁不成功)反馈函
	 */
	@ApiModelProperty("转回_仲裁结案(仲裁不成功)反馈函")
	private String zh_tjjabcgfkh;
	
	/**
	 * 诉讼引导_仲裁笔录
	 */
	@ApiModelProperty("诉讼引导_仲裁笔录")
	private String ssyd_tjbl;
	
	/**
	 * 诉讼引导_仲裁结案(仲裁不成功)反馈函
	 */
	@ApiModelProperty("诉讼引导_仲裁结案(仲裁不成功)反馈函")
	private String ssyd_tjjabcgfkh;
	
	/**
	 * 卷宗编号
	 */
	@ApiModelProperty("卷宗编号")
	private String jzbh;
	
	/**
	 * 卷宗id
	 */
	@ApiModelProperty("卷宗id")
	private String jzId;

	/**
	 * 排期开始时间
	 */
	@ApiModelProperty("排期开始时间")
	private String pqstart;
	
	/**
	 * 排期结束时间
	 */
	@ApiModelProperty("排期结束时间")
	private String pqend;
	
	@ApiModelProperty("仲裁流转原因")
	private String TJLZYY;

	public String getTjbl() {
		return tjbl;
	}

	public void setTjbl(String tjbl) {
		this.tjbl = tjbl;
	}

	public String getXcsplzurl() {
		return xcsplzurl;
	}

	public void setXcsplzurl(String xcsplzurl) {
		this.xcsplzurl = xcsplzurl;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getOtherPartyId() {
		return OtherPartyId;
	}

	public void setOtherPartyId(String otherPartyId) {
		OtherPartyId = otherPartyId;
	}

	public String getUserContext() {
		return UserContext;
	}

	public void setUserContext(String userContext) {
		UserContext = userContext;
	}

	public String getOtherContext() {
		return OtherContext;
	}

	public void setOtherContext(String otherContext) {
		OtherContext = otherContext;
	}

	public String getWorkerName() {
		return WorkerName;
	}

	public void setWorkerName(String workerName) {
		WorkerName = workerName;
	}

	public String getAuthenticId() {
		return AuthenticId;
	}

	public void setAuthenticId(String authenticId) {
		AuthenticId = authenticId;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getJs_tjjacgfkh() {
		return js_tjjacgfkh;
	}

	public void setJs_tjjacgfkh(String js_tjjacgfkh) {
		this.js_tjjacgfkh = js_tjjacgfkh;
	}

	public String getJs_rmtjxys() {
		return js_rmtjxys;
	}

	public void setJs_rmtjxys(String js_rmtjxys) {
		this.js_rmtjxys = js_rmtjxys;
	}

	public String getJs_tjbl() {
		return js_tjbl;
	}

	public void setJs_tjbl(String js_tjbl) {
		this.js_tjbl = js_tjbl;
	}

	public String getJs_sdsx() {
		return js_sdsx;
	}

	public void setJs_sdsx(String js_sdsx) {
		this.js_sdsx = js_sdsx;
	}

	public String getZh_tjbl() {
		return zh_tjbl;
	}

	public void setZh_tjbl(String zh_tjbl) {
		this.zh_tjbl = zh_tjbl;
	}

	public String getZh_tjjabcgfkh() {
		return zh_tjjabcgfkh;
	}

	public void setZh_tjjabcgfkh(String zh_tjjabcgfkh) {
		this.zh_tjjabcgfkh = zh_tjjabcgfkh;
	}

	public String getSsyd_tjbl() {
		return ssyd_tjbl;
	}

	public void setSsyd_tjbl(String ssyd_tjbl) {
		this.ssyd_tjbl = ssyd_tjbl;
	}

	public String getSsyd_tjjabcgfkh() {
		return ssyd_tjjabcgfkh;
	}

	public void setSsyd_tjjabcgfkh(String ssyd_tjjabcgfkh) {
		this.ssyd_tjjabcgfkh = ssyd_tjjabcgfkh;
	}

	public String getJzbh() {
		return jzbh;
	}

	public void setJzbh(String jzbh) {
		this.jzbh = jzbh;
	}
	
	public String getJzId() {
		return jzId;
	}

	public void setJzId(String jzId) {
		this.jzId = jzId;
	}

	public String getPqstart() {
		return pqstart;
	}

	public void setPqstart(String pqstart) {
		this.pqstart = pqstart;
	}

	public String getPqend() {
		return pqend;
	}

	public void setPqend(String pqend) {
		this.pqend = pqend;
	}
	
	public String getTJLZYY() {
		return TJLZYY;
	}

	public void setTJLZYY(String tJLZYY) {
		TJLZYY = tJLZYY;
	}

	public String getStateText() {
		String text="";
		
		switch (State) {
		case "0":
			text="待仲裁";
			break;
		case "1":
			text="已指派";
			break;
		case "2":
			text="已排期";
			break;
		case "3":
			text="仲裁中";
			break;
		case "4":
			text="撤销";
			break;
		case "5":
			text="维持";
			break;
		case "6":
			text="仲裁流转";
			break;
		case "7":
			text="再指派";
			break;
		default:
			break;
		}
		
		return text;
	}
}
