package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import repository.ElectVoteRepository;
import view.ElectVoteResultView;
import view.ElectVoteResultsView;
import bean.ElectVote;
import bean.ElectVoteDecision;
import bean.ElectVoteOption;
import bean.ElectVoteResult;
import bean.User;
import enums.ElectVoteDecisionType;

public class ElectVoteServiceImpl implements ElectVoteService {
	
	private ElectVoteRepository electVoteRepository;

	@Override
	public Integer saveOrUpdateElectVote(String adminName, String electVoteJson) throws Exception{

		return electVoteRepository.saveOrUpdateElectVote(adminName, electVoteJson);
	}
	
	@Override
	public void saveElectVote(ElectVote vote) throws Exception {
		this.electVoteRepository.saveElectVote(vote);
	}

	@Override
	public void deleteVoteElectVote(Integer electVoteId) throws Exception{

		electVoteRepository.deleteVoteElectVote(electVoteId);
	}
	
	/**
	 * 查询所有的投票
	 * @return
	 * @throws Exception
	 */
	public List<ElectVote> getElectVotes() throws Exception{
		List<ElectVote> electVoteconfigs = electVoteRepository.getElentVotes();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		for (ElectVote electVoteconfig : electVoteconfigs) {
		electVoteconfig.setCreateTimeStr(dateFormat.format(electVoteconfig.getCreateTime()));
		}
		return electVoteconfigs;
	}

	@Override
	public ElectVote getElectVoteById(Integer id)  throws Exception{

		return electVoteRepository.getElectVoteById(id);
	}

	@Override
	public List<ElectVoteOption> listElectVoteOptionsByVoteId(Integer voteId)  throws Exception{
		
		return electVoteRepository.listElectVoteOptionsByVoteId(voteId);
	}

	@Override
	public void deleteElectVoteOptionsByVoteId(Integer voteId)  throws Exception{
		
		electVoteRepository.deleteElectVoteOptionsByVoteId(voteId);
	}

	@Override
	public void deleteElectVoteOption(Integer voteOptionId)  throws Exception{
		
		electVoteRepository.deleteElectVoteOption(voteOptionId);
	}

	@Override
	public List<ElectVoteDecision> listElectVoteDecisionByVoteId(Integer voteId)  throws Exception{
		return electVoteRepository.listElectVoteDecisionByVoteId(voteId);
	}

	@Override
	public void deleteElectVoteDecisionsByVoteId(Integer voteId)  throws Exception{
		
		electVoteRepository.deleteElectVoteDecisionsByVoteId(voteId);
	}

	@Override
	public void saveElectVoteResult(ElectVoteResult result)  throws Exception{
		
		electVoteRepository.saveElectVoteResult(result);
	}
	
	/**
	 * 添加投票结果
	 * @param resultsJson
	 * 			投票结果字符串
	 * @throws Exception
	 */
	public void saveElectVoteResults(String resultsJson) throws Exception{
		
		electVoteRepository.saveElectVoteResults(resultsJson);
	}

	@Override
	public void deleteElectVoteResultsByVoteId(Integer voteId)  throws Exception{

		electVoteRepository.deleteElectVoteResultsByVoteId(voteId);
	}

	@Override
	public List<ElectVoteResult> getElectVoteResultByVoteId(Integer voteId)  throws Exception{

		return electVoteRepository.getElectVoteResultByVoteId(voteId);
	}

	@Override
	public List<ElectVoteResult> getElectVoteResultByVoteId(Integer voteId,
			Integer voteOptionId)  throws Exception{
		
		return electVoteRepository.getElectVoteResultByVoteId(voteId, voteOptionId);
	}

	public void setElectVoteRepository(ElectVoteRepository electVoteRepository) {
		this.electVoteRepository = electVoteRepository;
	}

	@Override
	public ElectVoteResultsView constructElectVoteDetailResultBy(Integer electVoteId) throws Exception{
		ElectVoteResultsView electVoteResultsView = new ElectVoteResultsView();
		ElectVote electVote = getElectVoteById(electVoteId);
		electVoteResultsView.setElectVoteName(electVote.getName());
		electVoteResultsView.setElectVoteId(electVoteId);
		electVoteResultsView.setIsDifferential(electVote.getIsDifferential());
		
		List<ElectVoteDecision> electVoteDecisions = listElectVoteDecisionByVoteId(electVoteId);
		for (ElectVoteDecision electVoteDecision : electVoteDecisions) {
			electVoteResultsView.addToAllDecisions(electVoteDecision.getcName());
		}
		List<ElectVoteOption> electVoteOptions = listElectVoteOptionsByVoteId(electVoteId);
		for (ElectVoteOption electVoteOption : electVoteOptions) {
			electVoteResultsView.addToAllOptions(electVoteOption.getName());
		}
		
		Map<Integer, String> electVoteOptionIdAndNameMap = new HashMap<Integer, String>();
		for (ElectVoteOption electVoteOption : electVoteOptions) {
			electVoteOptionIdAndNameMap.put(electVoteOption.getId(), electVoteOption.getName());
		}
		
		List<ElectVoteResult> electVoteResults = getElectVoteResultByVoteId(electVoteId);
		Map<String, List<ElectVoteResultView>> voterAndResultsMap = convertToVoterAndResultViewsMap(electVoteResults, electVoteOptionIdAndNameMap);
		electVoteResultsView.setVoterAndResultsMap(voterAndResultsMap);
		
		Map<String, Map<String, Integer>> decisionAndOptionCountMap = convertToDecisionAndOptionCountMap(voterAndResultsMap);
		electVoteResultsView.setDecisionAndOptionCountMap(decisionAndOptionCountMap);
		
		Map<String, String> optionAndFinalResultMap = convertToOptionAndFinalResultMap(decisionAndOptionCountMap, electVoteOptions, voterAndResultsMap.size());
		electVoteResultsView.setOptionAndFinalResultMap(optionAndFinalResultMap);
		return electVoteResultsView;
	}
	
	private Map<String, String> convertToOptionAndFinalResultMap(Map<String, Map<String, Integer>> decisionAndOptionCountMap,
			List<ElectVoteOption> allElectVoteOptions, int voterCount) {
		Map<String, String> optionAndFinalResultMap = new HashMap<String, String>();
		for (ElectVoteOption electVoteOption : allElectVoteOptions) {
			Map<String, Integer> optionAndCountMap = decisionAndOptionCountMap.get(ElectVoteDecisionType.agree.getCName());
			if (optionAndCountMap == null) {
				optionAndFinalResultMap.put(electVoteOption.getName(), "未通过");
			} else {
				Integer agreeCount = optionAndCountMap.get(electVoteOption.getName());
				if (agreeCount != null && agreeCount > voterCount/2) {
					optionAndFinalResultMap.put(electVoteOption.getName(), "通过");
				} else {
					optionAndFinalResultMap.put(electVoteOption.getName(), "未通过");
				}
			}
		}
		return optionAndFinalResultMap;
	}

	private Map<String, Map<String, Integer>> convertToDecisionAndOptionCountMap(Map<String, List<ElectVoteResultView>> voterAndResultsMap) {
		Map<String, Map<String, Integer>> decisionAndOptionCountMap = new HashMap<String, Map<String, Integer>>();
		for (List<ElectVoteResultView> electVoteResults : voterAndResultsMap.values()) {
			for (ElectVoteResultView electVoteResultView : electVoteResults) {
				Map<String, Integer> optionCountMap = decisionAndOptionCountMap.get(electVoteResultView.getDecision());
				if (optionCountMap == null) {
					optionCountMap = new HashMap<String, Integer>();
					optionCountMap.put(electVoteResultView.getOptionName(), 1);
					decisionAndOptionCountMap.put(electVoteResultView.getDecision(), optionCountMap);
				} else {
					Integer countUnderOption = optionCountMap.get(electVoteResultView.getOptionName());
					if (countUnderOption == null) {
						optionCountMap.put(electVoteResultView.getOptionName(), 1);
					} else {
						optionCountMap.put(electVoteResultView.getOptionName(), countUnderOption + 1);
					}
				}
			}
		}
		return decisionAndOptionCountMap;
	}

	private Map<String, List<ElectVoteResultView>> convertToVoterAndResultViewsMap(List<ElectVoteResult> electVoteResults, Map<Integer, String> electVoteOptionIdAndNameMap) {
		Map<String, List<ElectVoteResultView>> voterAndResultViewMap = new HashMap<String, List<ElectVoteResultView>>();
		for (ElectVoteResult electVoteResult : electVoteResults) {
			String voter = null;
			if (electVoteResult.getVoterName() != null) {
				voter = electVoteResult.getVoterName();
			} else {
				User user = electVoteRepository.getUserBy(electVoteResult.getUserId());
				voter = user.getUsername();
			}
			String optionName = electVoteOptionIdAndNameMap.get(electVoteResult.getElectVoteOptionId());
			ElectVoteResultView electVoteResultView = new ElectVoteResultView();
			electVoteResultView.setOptionName(optionName);
			electVoteResultView.setDecision(electVoteResult.getElectVoteResult());
			if (voterAndResultViewMap.get(voter) == null) {
				List<ElectVoteResultView> electVoteResultViews = new ArrayList<ElectVoteResultView>();
				electVoteResultViews.add(electVoteResultView);
				voterAndResultViewMap.put(voter, electVoteResultViews);
			} else {
				voterAndResultViewMap.get(voter).add(electVoteResultView);
			}
		}
		return voterAndResultViewMap;
	}

	@Override
	public boolean publishElectVoteOrNot(Integer electVoteId, boolean isPublish) {
		return electVoteRepository.updateElectVoteStatus(electVoteId, isPublish);
	}

	@Override
	public ElectVote getEarliestPublishedElectVote() {
		return electVoteRepository.loadEarliestPublishedElectVote();
	}

	@Override
	public User getUser(Integer userId) {
		return electVoteRepository.getUserBy(userId);
	}
}
