package repository;

import java.util.List;

import bean.AtomicGradeVote;
import bean.User;
import bean.VoteConfig;

public interface AdminRepository {
	
	public void createUser(User user);
	
	public void createGradeVoteConfig(VoteConfig gradeVoteConfig);
	
	public List<VoteConfig> loadCompletedGradeVotes();
	
	public List<VoteConfig> loadAllGradeVoteConfigs();
	
	public boolean updateGradeVoteStatus(Integer gradeVoteId, boolean isPublish);
	
	public List<AtomicGradeVote> loadGradeVoteResults(Integer gradeVoteId);

	public void deleteGradeVote(Integer gradeVoteId);

}
