package bean;

/**
 * 投票决定
 * @author zhy
 *
 */
public class ElectVoteDecision {

	private Integer id;
	
	/**
	 * 投票决定中文名
	 */
	private String cName;
	
	/**
	 * 投票决定英文名
	 */
	private String eName;
	
	/**
	 * 投票主键
	 */
	private Integer electVoteId;

	public ElectVoteDecision() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public Integer getElectVoteId() {
		return electVoteId;
	}

	public void setElectVoteId(Integer electVoteId) {
		this.electVoteId = electVoteId;
	}
	
}
