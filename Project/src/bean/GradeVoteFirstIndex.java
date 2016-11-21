package bean;

import java.util.ArrayList;
import java.util.List;


public class GradeVoteFirstIndex {
	public static String PARAM_SECOND_INDEXES = "secondIndexes";
	private Integer id;
	private String name;
	private float weight;
	private List<GradeVoteSecondIndex> secondIndexes = new ArrayList<GradeVoteSecondIndex>();
	private VoteConfig voteConfig;

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

	public VoteConfig getVoteConfig() {
		return voteConfig;
	}

	public void setVoteConfig(VoteConfig voteConfig) {
		this.voteConfig = voteConfig;
	}
	
	public void addToSecondIndexes(GradeVoteSecondIndex secondIndex) {
		secondIndex.setGradeVoteFirstIndex(this);
		secondIndexes.add(secondIndex);
	}

	public List<GradeVoteSecondIndex> getSecondIndexes() {
		return secondIndexes;
	}

	public void setSecondIndexes(List<GradeVoteSecondIndex> secondIndexes) {
		this.secondIndexes = secondIndexes;
	}
	
	public int getWeightInInteger() {
		return (int) this.weight;
	}
}
