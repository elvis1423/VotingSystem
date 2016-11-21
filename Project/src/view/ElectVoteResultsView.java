package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElectVoteResultsView {
	private String electVoteName;
	private Integer electVoteId;
	private Integer isDifferential;
	private Map<String, List<ElectVoteResultView>> voterAndResultsMap;
	private Map<String, Map<String, Integer>> decisionAndOptionCountMap;
	private Map<String, String> optionAndFinalResultMap; 
	private List<String> allDecisions;
	private List<String> allOptions;

	public String getElectVoteName() {
		return electVoteName;
	}

	public void setElectVoteName(String electVoteName) {
		this.electVoteName = electVoteName;
	}

	public Integer getIsDifferential() {
		return isDifferential;
	}

	public void setIsDifferential(Integer isDifferential) {
		this.isDifferential = isDifferential;
	}

	public Map<String, List<ElectVoteResultView>> getVoterAndResultsMap() {
		return voterAndResultsMap;
	}

	public void setVoterAndResultsMap(
			Map<String, List<ElectVoteResultView>> voterAndResultsMap) {
		this.voterAndResultsMap = voterAndResultsMap;
	}

	public List<String> getAllDecisions() {
		return allDecisions;
	}

	public void setAllDecisions(List<String> allDecisions) {
		this.allDecisions = allDecisions;
	}
	
	public void addToAllDecisions(String decision) {
		if (this.allDecisions == null) {
			this.allDecisions = new ArrayList<String>();
		}
		this.allDecisions.add(decision);
	}

	public List<String> getAllOptions() {
		return allOptions;
	}

	public void setAllOptions(List<String> allOptions) {
		this.allOptions = allOptions;
	}
	
	public void addToAllOptions(String option) {
		if (this.allOptions == null) {
			this.allOptions = new ArrayList<String>();
		}
		this.allOptions.add(option);
	}

	public Map<String, Map<String, Integer>> getDecisionAndOptionCountMap() {
		return decisionAndOptionCountMap;
	}

	public void setDecisionAndOptionCountMap(
			Map<String, Map<String, Integer>> decisionAndOptionCountMap) {
		this.decisionAndOptionCountMap = decisionAndOptionCountMap;
	}

	public Map<String, String> getOptionAndFinalResultMap() {
		return optionAndFinalResultMap;
	}

	public void setOptionAndFinalResultMap(Map<String, String> optionAndFinalResultMap) {
		this.optionAndFinalResultMap = optionAndFinalResultMap;
	}

	public Integer getElectVoteId() {
		return electVoteId;
	}

	public void setElectVoteId(Integer electVoteId) {
		this.electVoteId = electVoteId;
	}

}
