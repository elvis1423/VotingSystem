package enums;

public enum ElectVoteDecisionType {
	agree("同意"),
	oppose("反对"),
	abstain("弃权");
	
	private String cName;
	public String getCName() {
		return this.cName;
	}
	
	ElectVoteDecisionType(String cName) {
		this.cName = cName;
	}
}
