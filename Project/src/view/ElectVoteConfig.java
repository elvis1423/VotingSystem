package view;

import java.util.List;
import java.util.Map;

import bean.ElectVote;
import bean.ElectVoteOption;

public class ElectVoteConfig {
	private ElectVote electVote;
	private List<ElectVoteOption> options;
	private Map<String, String> decisionMap;
	
	public ElectVote getElectVote() {
		return electVote;
	}
	public void setElectVote(ElectVote electVote) {
		this.electVote = electVote;
	}
	public List<ElectVoteOption> getOptions() {
		return options;
	}
	public void setOptions(List<ElectVoteOption> options) {
		this.options = options;
	}
	public Map<String, String> getDecisionMap() {
		return decisionMap;
	}
	public void setDecisionMap(Map<String, String> decisionMap) {
		this.decisionMap = decisionMap;
	}
}
