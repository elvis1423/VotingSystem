package repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;

import util.DateUtil;
import bean.ElectVote;
import bean.ElectVoteDecision;
import bean.ElectVoteOption;
import bean.ElectVoteResult;

/**
 * 投票数据处理接口实现
 * @author zhy
 *
 */
public class ElectVoteRepositoryImpl extends BaseRepository implements ElectVoteRepository {
	
	/**
	 * 添加或修改投票
	 * @param electVoteJson
	 * 			投票Json
	 * {
		"id": "",
		"name": "",
		"content": "",
		"mayChooseCount": "",
		"isPublish": "",
		"decisions": [
			{
			  "cName": "",
			  "eName": ""
			}, {
			  "cName": "",
			  "eName": ""
			}
		],
		"options": [
			{
			  "name": "",
			  "content": ""
			}, {
			  "name": "",
			  "content": ""
			}
		]
	   }
	 * @throws Exception 
	 */
	public Integer saveOrUpdateElectVote(String adminName, String electVoteJson) throws Exception {
		Session session = getSession();
		// 解析投票实体JSONStr，并保存
		// 转换投票实体JSONStr
		JSONObject object = JSONObject.fromObject(electVoteJson);
		String voteId = object.getString("id");

		// 创建投票实体
		ElectVote vote = null;
		if (voteId != null && !"".equals(voteId)) {
			//TODO: update logic has problem when already has electVoteResult 
			Integer id = Integer.parseInt(voteId);
			vote = (ElectVote) session.get(ElectVote.class, id);
			// 根据投票主键删除所有投票选项
			this.deleteElectVoteOptionsByVoteId(session, id);

			// 根据投票主键删除所有投票决定
			this.deleteElectVoteDecisionsByVoteId(session, id);
		} else {
			vote = new ElectVote();
			vote.setCreateTime(DateUtil._dateForamt(new Date()));
		}

		vote.setName(object.getString("name"));
		vote.setMayChooseCount(object.getInt("mayChooseCount"));
		vote.setContent(object.getString("content"));
		vote.setIsDifferential(object.getInt("isDifferential"));
		vote.setIsAnonymous(object.getInt("isAnonymous"));
		vote.setIsPublish(object.getInt("isPublish"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vote.setStartTime(dateFormat.parse(object.getString("startTimeStr")));
		vote.setStartTime(dateFormat.parse(object.getString("completeTimeStr")));

		session.save(vote);

		// 添加投票决定
		JSONArray decisionArr = object.getJSONArray("decisions");
		if (decisionArr != null && decisionArr.size() > 0) {
			for (Object o : decisionArr) {
				JSONObject obj = JSONObject.fromObject(o);
				ElectVoteDecision decision = new ElectVoteDecision();
				decision.setcName(obj.getString("cName"));
				decision.seteName(obj.getString("eName"));
				decision.setElectVoteId(vote.getId());

				session.save(decision);
			}
		}

		// 添加投票选项
		JSONArray optionArr = object.getJSONArray("options");
		if (optionArr != null && optionArr.size() > 0) {
			for (Object o : optionArr) {
				JSONObject obj = JSONObject.fromObject(o);
				ElectVoteOption option = new ElectVoteOption();
				option.setName(obj.getString("name"));
				option.setContent(obj.getString("content"));
				option.setElectVoteId(vote.getId());
				if (obj.getString("fileName").length() > 0) {
					int lastIndexOfSlash = obj.getString("fileName").lastIndexOf("\\");
					option.setImgUrl("/WEB-INF/electvote/" + adminName + "_" + vote.getName() + "_option" + obj.getString("order") + "/" + obj.getString("fileName").substring(lastIndexOfSlash+1));
				}
				session.save(option);
			}
		}
		return vote.getId();
	}
	
	/**
	 * 保存投票
	 * @param vote
	 * 			投票实体
	 * @throws Exception
	 */
	public void saveElectVote(ElectVote vote) throws Exception {
		getSession().saveOrUpdate(vote);
	}

	/**
	 * 根据投票主键删除投票
	 * @param electVoteId
	 * 			投票主键
	 * @throws Exception
	 */
	public void deleteVoteElectVote(Integer electVoteId) throws Exception {

		Session session = getSession();
		// 删除投票
		// 1.删除投票实体
		ElectVote vote = (ElectVote) session.get(ElectVote.class, electVoteId);
		session.delete(vote);

		// 2.根据投票主键删除投票选项
		this.deleteElectVoteOptionsByVoteId(session, electVoteId);

		// 3.根据投票主键删除投票决定
		this.deleteElectVoteDecisionsByVoteId(session, electVoteId);

		// 4.根据投票主键删除投票结果
		this.deleteElectVoteResultsByVoteId(session, electVoteId);
	}
	
	/**
	 * 查询所有的投票
	 * @return
	 * @throws Exception
	 */
	public List<ElectVote> getElentVotes() throws Exception{
		Query query = getSession().createQuery("from ElectVote e order by createTime desc");
		@SuppressWarnings("unchecked")
		List<ElectVote> list = query.list();
		return list;
	}
	
	/**
	 * 根据投票主键获取投票
	 * @param id
	 * 			投票主键
	 * @return
	 * @throws Exception
	 */
	public ElectVote getElectVoteById(Integer id) throws Exception{
		//根据投票主键获取投票实体
		ElectVote vote = (ElectVote)getSession().get(ElectVote.class, id);
		return vote;
	}
	
	/**
	 * 根据投票主键获取所有投票选项
	 * @param voteId
	 * 			投票主键
	 * @return
	 * @throws Exception
	 */
	public List<ElectVoteOption> listElectVoteOptionsByVoteId(Integer voteId) throws Exception{
		Query query = getSession().createQuery("from ElectVoteOption e where e.electVoteId = :voteId");
		query.setInteger("voteId", voteId);
		
		@SuppressWarnings("unchecked")
		List<ElectVoteOption> list = query.list();
		return list;
	}
	
	/**
	 * 根据投票主键删除所有的投票选项
	 * @param voteId
	 * 			投票主键
	 * @throws Exception
	 */
	public void deleteElectVoteOptionsByVoteId(Integer voteId) throws Exception{
		Session session = getSession();
		this.deleteElectVoteOptionsByVoteId(session,voteId);
	}
	
	/**
	 * 根据投票主键删除所有的投票选项
	 * @param session
	 * 			Hibernate Session
	 * @param voteId
	 * 			投票主键
	 */
	private void deleteElectVoteOptionsByVoteId(Session session, Integer voteId) {
		Query query = session.createQuery("from ElectVoteOption e where e.electVoteId = :voteId");
		query.setInteger("voteId", voteId);
		@SuppressWarnings("unchecked")
		List<ElectVoteOption> list = query.list();

		if (list != null && list.size() > 0) {
			for (ElectVoteOption option : list) {
				session.delete(option);
			}
		}
	}
	
	/**
	 * 根据投票选项主键获取投票选项
	 * @param voteOptionId
	 * 			投票选项主键
	 * @throws Exception
	 */
	public void deleteElectVoteOption(Integer voteOptionId) throws Exception {
		Session session = getSession();
		ElectVoteOption voteOption = (ElectVoteOption) session.get(ElectVoteOption.class, voteOptionId);
		session.delete(voteOption);
	}
	
	/**
	 * 根据投票主键获取所有的投票决定
	 * @param voteId
	 * 			投票主键
	 * @return
	 * @throws Exception
	 */
	public List<ElectVoteDecision> listElectVoteDecisionByVoteId(Integer voteId) throws Exception{
		
		Session session = getSession();
		Query query = session.createQuery("from ElectVoteDecision e where e.electVoteId = :voteId");
		query.setInteger("voteId", voteId);
		
		@SuppressWarnings("unchecked")
		List<ElectVoteDecision> list = query.list();
		return list;
	}
	
	/**
	 * 根据投票主键获取所有的投票决定
	 * @param voteId
	 * 			投票主键
	 * @throws Exception
	 */
	public void deleteElectVoteDecisionsByVoteId(Integer voteId) throws Exception{
		Session session = getSession();
		this.deleteElectVoteDecisionsByVoteId(session,voteId);
	}
	
	/**
	 * 根据投票主键获取所有投票决定
	 * @param session
	 * 			Hibernate Session
	 * @param voteId
	 * 			投票主键
	 */
	private void deleteElectVoteDecisionsByVoteId(Session session,
			Integer voteId) {
		Query query = session.createQuery("from ElectVoteDecision e where e.electVoteId = :voteId");
		query.setInteger("voteId", voteId);
		@SuppressWarnings("unchecked")
		List<ElectVoteDecision> list = query.list();

		if (list != null && list.size() > 0) {
			for (ElectVoteDecision decision : list) {
				session.delete(decision);
			}
		}
	}
	
	/**
	 * 添加投票结果
	 * @param result
	 * 			投票结果实体
	 * @throws Exception
	 */
	public void saveElectVoteResult(ElectVoteResult result) throws Exception {
		Session session = getSession();
		result.setCreateTime(DateUtil._dateForamt(new Date()));
		session.save(result);
	}
	
	
	/**
	 * 添加投票结果
	 * @param resultsJson
	 * 			投票结果字符串
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveElectVoteResults(String resultsJson) throws Exception {
		if (resultsJson == null || "".equals(resultsJson))
			throw new Exception("投票失败");

		// 转换投票实体JSONStr
		JSONArray electVoteResultsJsonArray = JSONArray.fromObject(resultsJson);
		List<ElectVoteResult> electVoteResults = (ArrayList<ElectVoteResult>) JSONArray.toCollection(electVoteResultsJsonArray, ElectVoteResult.class);
		if (isUserAlreadyElectVoted(electVoteResults.get(0).getUserId(), electVoteResults.get(0).getElectVoteId())) {
			return;
		}
		for (ElectVoteResult electVoteResult : electVoteResults) {
			electVoteResult.setCreateTime(DateUtil._dateForamt(new Date()));
			getSession().save(electVoteResult);
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean isUserAlreadyElectVoted(Integer userId, Integer electVoteId) {
		String hsqlString = "from ElectVoteResult where userId=? and electVoteId=?";
		Query query = getSession().createQuery(hsqlString);
		query.setParameter(0, userId);
		query.setParameter(1, electVoteId);
		List<ElectVoteResult> electVoteResults = (ArrayList<ElectVoteResult>) query.list();
		return electVoteResults.size() == 0 ? false : true;
	}
	
	/**
	 * 根据投票主键删除所有投票结果
	 * @param voteId
	 * 			投票主键
	 * @throws Exception
	 */
	public void deleteElectVoteResultsByVoteId(Integer voteId) throws Exception{
		this.deleteElectVoteResultsByVoteId(getSession(),voteId);
	}
	
	/**
	 * 根据投票主键删除所有投票结果
	 * @param session
	 * 			Hibernate session
	 * @param voteId
	 * 			投票主键
	 */
	private void deleteElectVoteResultsByVoteId(Session session,Integer voteId){
		
		Query query = session.createQuery("from ElectVoteResult e where e.electVoteId = :voteId");
		query.setInteger("voteId", voteId);
		@SuppressWarnings("unchecked")
		List<ElectVoteResult> list = query.list();
		
		if(list != null && list.size() > 0){
			for(ElectVoteResult result : list){
				session.delete(result);
			}
		}
	}
	
	/**
	 * 根据投票主键获取所有投票结果
	 * @param voteId
	 * 			投票主键
	 * @return
	 * @throws Exception
	 */
	public List<ElectVoteResult> getElectVoteResultByVoteId(Integer voteId) throws Exception{
		
		Session session = getSession();
		
		Query query = session.createQuery("from ElectVoteResult e where e.electVoteId = :voteId");
		query.setInteger("voteId", voteId);
		@SuppressWarnings("unchecked")
		List<ElectVoteResult> list = query.list();
		
		return list;
	}
	
	/**
	 * 根据投票主键及投票选项主键获取获取所有投票结果
	 * @param voteId
	 * 			投票主键
	 * @param voteOptionId
	 * 			投票选项主键
	 * @return
	 * @throws Exception
	 */
	public List<ElectVoteResult> getElectVoteResultByVoteId(Integer voteId,Integer voteOptionId) throws Exception{
		Query query = getSession().createQuery("from ElectVoteResult e where e.electVoteId = :voteId and e.electVoteOptionId = :voteOptionId");
		query.setInteger("voteId", voteId);
		query.setInteger("voteOptionId", voteOptionId);
		@SuppressWarnings("unchecked")
		List<ElectVoteResult> list = query.list();
		return list;
	}

	@Override
	public ElectVoteOption getElectVoteOptionBy(Integer electVoteOptionId) {
		return (ElectVoteOption) getSession().get(ElectVoteOption.class, electVoteOptionId);
	}

	@Override
	public boolean updateElectVoteStatus(Integer electVoteId, boolean isPublish) {
		Session hibernateSession = getSession();
		ElectVote voteConfig = (ElectVote)hibernateSession.load(ElectVote.class, electVoteId);
		if (isPublish) {
			voteConfig.setIsPublish(1);
		} else {
			voteConfig.setIsPublish(0);
		}
		hibernateSession.update(voteConfig);
		return true;
	}

	@Override
	public ElectVote loadEarliestPublishedElectVote() {
		String hsqlString = "from ElectVote where isPublish=1 order by startTime asc";
		Query query = getSession().createQuery(hsqlString);
		List<ElectVote> sortedPublishedElectVotes = (ArrayList<ElectVote>) query.list();
		return sortedPublishedElectVotes.size() > 0 ? sortedPublishedElectVotes.get(0) : null;
	}
	
}
