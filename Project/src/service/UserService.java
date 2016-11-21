package service;

import view.GradeVoteResultView;
import bean.VoteConfig;

public interface UserService {
	public VoteConfig getEarliestActiveConfigGradeVote();
	public void convertAndSaveAtomicGradeVotes(String atomicGradeVotesJsonStr);
	public GradeVoteResultView constructGradeVoteResultView(String atomicGradeVotesJsonStr);
}
