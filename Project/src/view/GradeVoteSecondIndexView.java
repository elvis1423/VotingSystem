package view;

public class GradeVoteSecondIndexView {
	private String name;
	private float weight;
	private float grade;
	
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
	
	public int getWeightInInteger() {
		return (int) this.weight;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}
}
