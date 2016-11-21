package service;

import java.util.List;

import view.ElectVoteResultsView;
import bean.ElectVote;
import bean.ElectVoteDecision;
import bean.ElectVoteOption;
import bean.ElectVoteResult;
import bean.User;

/**
 * 投票业务处理接口
 * @author zhy
 *
 */
public interface ElectVoteService {

	public ElectVoteResultsView constructElectVoteDetailResultBy(Integer electVoteId) throws Exception;
	/**
	 * 添加或修改投票
	 * @param electVoteJson
	 * 			投票Json
	 * @throws Exception
	 */
	public Integer saveOrUpdateElectVote(String adminName, String electVoteJson) throws Exception;
	
	/**
	 * 保存投票
	 * @param vote
	 * 			投票实体
	 * @throws Exception
	 */
	public void saveElectVote(ElectVote vote) throws Exception;
	
	/**
	 * 根据投票主键删除投票
	 * @param electVoteId
	 * 			投票主键
	 * @throws Exception
	 */
	public void deleteVoteElectVote(Integer electVoteId) throws Exception;
	
	/**
	 * 查询所有的投票
	 * @return
	 * @throws Exception
	 */
	public List<ElectVote> getElectVotes() throws Exception;
	
	
	/**
	 * 根据投票主键获取投票
	 * @param id
	 * 			投票主键
	 * @return
	 * @throws Exception
	 */
	public ElectVote getElectVoteById(Integer id) throws Exception;
	
	
	/**
	 * 根据投票主键获取所有投票选项
	 * @param voteId
	 * 			投票主键
	 * @return
	 * @throws Exception
	 */
	public List<ElectVoteOption> listElectVoteOptionsByVoteId(Integer voteId) throws Exception;
	
	
	/**
	 * 根据投票主键删除所有的投票选项
	 * @param voteId
	 * 			投票主键
	 * @throws Exception
	 */
	public void deleteElectVoteOptionsByVoteId(Integer voteId) throws Exception;
	
	
	/**
	 * 根据投票选项主键获取投票选项
	 * @param voteOptionId
	 * 			投票选项主键
	 * @throws Exception
	 */
	public void deleteElectVoteOption(Integer voteOptionId) throws Exception;
	
	
	/**
	 * 根据投票主键获取所有的投票决定
	 * @param voteId
	 * 			投票主键
	 * @return
	 * @throws Exception
	 */
	public List<ElectVoteDecision> listElectVoteDecisionByVoteId(Integer voteId) throws Exception;
	
	
	/**
	 * 根据投票主键获取所有的投票决定
	 * @param voteId
	 * 			投票主键
	 * @throws Exception
	 */
	public void deleteElectVoteDecisionsByVoteId(Integer voteId) throws Exception;
	
	
	/**
	 * 添加投票结果
	 * @param result
	 * 			投票结果实体
	 * @throws Exception
	 */
	public void saveElectVoteResult(ElectVoteResult result) throws Exception;
	
	
	/**
	 * 添加投票结果
	 * @param resultsJson
	 * 			投票结果字符串
	 * @throws Exception
	 */
	public void saveElectVoteResults(String resultsJson) throws Exception;
	
	
	/**
	 * 根据投票主键删除所有投票结果
	 * @param voteId
	 * 			投票主键
	 * @throws Exception
	 */
	public void deleteElectVoteResultsByVoteId(Integer voteId) throws Exception;
	
	
	/**
	 * 根据投票主键获取所有投票结果
	 * @param voteId
	 * 			投票主键
	 * @return
	 * @throws Exception
	 */
	public List<ElectVoteResult> getElectVoteResultByVoteId(Integer voteId) throws Exception;
	
	
	/**
	 * 根据投票主键及投票选项主键获取获取所有投票结果
	 * @param voteId
	 * 			投票主键
	 * @param voteOptionId
	 * 			投票选项主键
	 * @return
	 * @throws Exception
	 */
	public List<ElectVoteResult> getElectVoteResultByVoteId(Integer voteId,Integer voteOptionId) throws Exception;
	
	boolean publishElectVoteOrNot(Integer electVoteId, boolean isPublish);
	
	public ElectVote getEarliestPublishedElectVote();
	
	public User getUser(Integer userId);

}
