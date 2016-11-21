package view;

import java.util.ArrayList;
import java.util.List;

import bean.GradeVoteFirstIndex;

public class GradeVoteConfig {
	public static String PARAM_GRADE_VOTE_INDEX = "indexes";
	
	private String voteName;
	private Integer isAnonymous;
	private List<String> candidates;
	private List<GradeVoteFirstIndex> indexes = new ArrayList<GradeVoteFirstIndex>();
	private String startDate;
	private String endDate;

	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	public List<String> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<String> candidates) {
		this.candidates = candidates;
	}

	public List<GradeVoteFirstIndex> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<GradeVoteFirstIndex> indexes) {
		this.indexes = indexes;
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Integer isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
}
