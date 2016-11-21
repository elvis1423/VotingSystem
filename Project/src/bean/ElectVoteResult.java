package bean;

import java.util.Date;

/**
 * 投票结果
 * @author zhy
 *
 */
public class ElectVoteResult {

	
	private Integer id;
	
	/**
	 * 投票主键
	 */
	private Integer electVoteId;
	
	/**
	 * 投票选项主键
	 */
	private Integer electVoteOptionId;
	
	/**
	 * 投票结果
	 * 投票决定中文名
	 */
	private String electVoteResult;
	
	/**
	 * 用户主键
	 */
	private Integer userId;
	
	/**
	 * 投票人名称
	 */
	private String voterName;
	
	/**
	 * 投票时间
	 */
	private Date createTime;

	public ElectVoteResult() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getElectVoteId() {
		return electVoteId;
	}

	public void setElectVoteId(Integer electVoteId) {
		this.electVoteId = electVoteId;
	}

	public Integer getElectVoteOptionId() {
		return electVoteOptionId;
	}

	public void setElectVoteOptionId(Integer electVoteOptionId) {
		this.electVoteOptionId = electVoteOptionId;
	}

	public String getElectVoteResult() {
		return electVoteResult;
	}

	public void setElectVoteResult(String electVoteResult) {
		this.electVoteResult = electVoteResult;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
