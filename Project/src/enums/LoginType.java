package enums;

public enum LoginType {
	ADMIN,
	USER,
	NOLOGIN;
	
	private String typeStr;

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
}
