package repository;

import java.util.List;

import view.AtomicGradeVoteView;
import bean.AtomicGradeVote;
import bean.VoteConfig;

public interface UserRepository {
	public List<VoteConfig> loadActiveGradeVoteConfigs();

	public VoteConfig loadEarliestActiveGradeVote();

	public void saveAllGradeVotes(List<AtomicGradeVoteView> gradeVotes);

	public List<AtomicGradeVote> loadAtomicGradeVotes(Integer userId, Integer voteConfigId);

	public AtomicGradeVote loadAtomicGradeVoteBy(AtomicGradeVoteView atomicGradeVoteView);
}
