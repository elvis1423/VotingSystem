package view;

public class AtomicGradeVoteView {
	private Integer userId;
	private Integer voteConfigId;
	private Integer candidateId;
	private Integer firstIndexId;
	private Integer secondIndexId;
	private float grade;
	private String voter;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getVoteConfigId() {
		return voteConfigId;
	}
	public void setVoteConfigId(Integer voteConfigId) {
		this.voteConfigId = voteConfigId;
	}
	public Integer getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}
	public Integer getFirstIndexId() {
		return firstIndexId;
	}
	public void setFirstIndexId(Integer firstIndexId) {
		this.firstIndexId = firstIndexId;
	}
	public Integer getSecondIndexId() {
		return secondIndexId;
	}
	public void setSecondIndexId(Integer secondIndexId) {
		this.secondIndexId = secondIndexId;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public String getVoter() {
		return voter;
	}
	public void setVoter(String voter) {
		this.voter = voter;
	}
}
