package bean;

/**
 * 投票选项
 * @author zhy
 *
 */
public class ElectVoteOption {

	private Integer id;
	
	/**
	 * 投票选项名称
	 */
	private String name;
	
	/**
	 * 投票选项内容
	 */
	private String content;
	
	/**
	 * 投票选项链接
	 */
	private String imgUrl;
	
	/**
	 * 投票主键
	 */
	private Integer electVoteId;

	public ElectVoteOption() {
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getElectVoteId() {
		return electVoteId;
	}

	public void setElectVoteId(Integer electVoteId) {
		this.electVoteId = electVoteId;
	}
	
	
}
