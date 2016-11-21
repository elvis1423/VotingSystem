package bean;

public class AtomicGradeVote {
	private Integer id;
	private User user;
	private VoteConfig vote;
	private Candidate candidate;
	private GradeVoteFirstIndex firstIndex;
	private GradeVoteSecondIndex secondIndex;
	private float grade;
	private String voter;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VoteConfig getVote() {
		return vote;
	}

	public void setVote(VoteConfig vote) {
		this.vote = vote;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public GradeVoteFirstIndex getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(GradeVoteFirstIndex firstIndex) {
		this.firstIndex = firstIndex;
	}

	public GradeVoteSecondIndex getSecondIndex() {
		return secondIndex;
	}

	public void setSecondIndex(GradeVoteSecondIndex secondIndex) {
		this.secondIndex = secondIndex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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