package bean;


public class GradeVoteSecondIndex {
	private Integer id;
	private String name;
	private float weight;
	private GradeVoteFirstIndex gradeVoteFirstIndex;

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

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public GradeVoteFirstIndex getGradeVoteFirstIndex() {
		return gradeVoteFirstIndex;
	}

	public void setGradeVoteFirstIndex(GradeVoteFirstIndex gradeVoteFirstIndex) {
		this.gradeVoteFirstIndex = gradeVoteFirstIndex;
	}
	
	public int getWeightInInteger() {
		return (int) this.weight;
	}
}
