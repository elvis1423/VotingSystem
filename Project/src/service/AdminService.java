package service;

import java.util.List;
import java.util.Map;

import view.GradeVoteResultView;
import view.GradeVoteResultsView;
import bean.VoteConfig;

public interface AdminService {
	public void convertAndSaveVoteConfig(String gradeVoteConfigJsonStr);

	public List<VoteConfig> getAllGradeVoteConfigs();
	
	public List<VoteConfig> getCompletedGradeVoteConfigs();
	
	public boolean publishGradeVoteOrNot(Integer gradeVoteId, boolean isPublish);
	
	public void convertAndsaveUserInfo(String UserInfoJsonStruname,String UserInfoJsonStrpword);
	
	public Map<String, GradeVoteResultView> getVoterAndGradeVoteResultMap(Integer gradeVoteId, boolean isAnonymous);
	
	public GradeVoteResultsView getGradeVoteResultsView(Integer gradeVoteId, boolean isAnonymous);

	public void deleteGradeVote(Integer gradeVoteId);
}
