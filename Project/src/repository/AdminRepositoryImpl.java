package repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import bean.AtomicGradeVote;
import bean.User;
import bean.VoteConfig;

public class AdminRepositoryImpl extends BaseRepository implements AdminRepository{
	@Override
	public void createUser(User user) {
		getSession().save(user);
	}
	
	@Override
	public void createGradeVoteConfig(VoteConfig gradeVoteConfig) {
		getSession().save(gradeVoteConfig);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoteConfig> loadCompletedGradeVotes() {
		Session hibernateSession = getSession();
		String hsqlString = "from VoteConfig where isDeleted=0 and isActive=0 order by startDate desc";
		Query query = hibernateSession.createQuery(hsqlString);
		List<VoteConfig> gradeVoteConfigs = (ArrayList<VoteConfig>) query.list();
		return gradeVoteConfigs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VoteConfig> loadAllGradeVoteConfigs() {
		Session hibernateSession = getSession();
		String hsqlString = "from VoteConfig where isDeleted=0 order by startDate desc";
		Query query = hibernateSession.createQuery(hsqlString);
		List<VoteConfig> gradeVoteConfigs = (ArrayList<VoteConfig>) query.list();
		return gradeVoteConfigs;
	}

	@Override
	public boolean updateGradeVoteStatus(Integer gradeVoteId, boolean isPublish) {
		Session hibernateSession = getSession();
		VoteConfig voteConfig = (VoteConfig)hibernateSession.load(VoteConfig.class, gradeVoteId);
		if (isPublish) {
			voteConfig.setIsActive(1);
		} else {
			voteConfig.setIsActive(0);
		}
		hibernateSession.update(voteConfig);
		return true;
	}
	
	@Override
	public void deleteGradeVote(Integer gradeVoteId) {
		Session hibernateSession = getSession();
		VoteConfig voteConfig = (VoteConfig)hibernateSession.load(VoteConfig.class, gradeVoteId);
		voteConfig.setIsDeleted(1);
		hibernateSession.update(voteConfig);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AtomicGradeVote> loadGradeVoteResults(Integer gradeVoteId) {
		// TODO Auto-generated method stub
		Session hibernateSession = getSession();
        String hsqlString = "from AtomicGradeVote where vote.id=?";
		Query query = hibernateSession.createQuery(hsqlString);
		query.setParameter(0, gradeVoteId);
		List<AtomicGradeVote> atomicGradeVotes = (ArrayList<AtomicGradeVote>) query.list();
		return atomicGradeVotes;
	}
}
