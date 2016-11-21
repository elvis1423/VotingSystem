package view;

import java.util.ArrayList;
import java.util.List;

public class GradeVoteFirstIndexView {
	private String name;
	private float weight;
	private float grade;
	private List<GradeVoteSecondIndexView> secondIndexes = new ArrayList<GradeVoteSecondIndexView>();

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

	public List<GradeVoteSecondIndexView> getSecondIndexes() {
		return secondIndexes;
	}

	public void setSecondIndexes(List<GradeVoteSecondIndexView> secondIndexes) {
		this.secondIndexes = secondIndexes;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}
}
