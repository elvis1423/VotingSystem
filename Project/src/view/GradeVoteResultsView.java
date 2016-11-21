package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeVoteResultsView {
	private String gradeVoteName;
	private Map<String, GradeVoteResultView> voterAndResultMap = new HashMap<String, GradeVoteResultView>();
	private List<GradeVoteFinalResultView> gradeVoteFinalResults = new ArrayList<GradeVoteFinalResultView>();

	public String getGradeVoteName() {
		return gradeVoteName;
	}

	public void setGradeVoteName(String gradeVoteName) {
		this.gradeVoteName = gradeVoteName;
	}

	public Map<String, GradeVoteResultView> getVoterAndResultMap() {
		return voterAndResultMap;
	}

	public void setVoterAndResultMap(Map<String, GradeVoteResultView> voterAndResultMap) {
		this.voterAndResultMap = voterAndResultMap;
	}

	public List<GradeVoteFinalResultView> getGradeVoteFinalResults() {
		return gradeVoteFinalResults;
	}

	public void setGradeVoteFinalResults(List<GradeVoteFinalResultView> gradeVoteFinalResults) {
		this.gradeVoteFinalResults = gradeVoteFinalResults;
	}
}
