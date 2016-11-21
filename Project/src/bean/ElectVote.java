package bean;

import java.util.Date;

/**
 * 投票实体类
 * @author zhy
 *
 */
public class ElectVote {

	private Integer id;
	
	/**
	 * 投票名称
	 */
	private String name;
	
	/**
	 * 投票内容
	 */
	private String content;
	
	/**
	 * 是否差额投票，默认为等额，0等额投票，1差额投票
	 */
	private Integer isDifferential;
	
	/**
	 * 可以投几次
	 */
	private Integer mayChooseCount;
	
	/**
	 * 是否发布，默认为不发布，0未发布，1发布
	 */
	private Integer isPublish;
	
	/**
	 * 是否发布，默认为不发布，0实名，1匿名
	 */
	private Integer isAnonymous;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	private Date startTime;
	private Date completeTime;
	private String createTimeStr;

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public ElectVote() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsDifferential() {
		return isDifferential;
	}

	public void setIsDifferential(Integer isDifferential) {
		this.isDifferential = isDifferential;
	}

	public Integer getMayChooseCount() {
		return mayChooseCount;
	}

	public void setMayChooseCount(Integer mayChooseCount) {
		this.mayChooseCount = mayChooseCount;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date CompleteTime) {
		this.completeTime = CompleteTime;
	}

	public Integer getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Integer isAnonymous) {
		this.isAnonymous = isAnonymous;
	}	
}
