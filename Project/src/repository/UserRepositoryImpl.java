package repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import view.AtomicGradeVoteView;
import bean.AtomicGradeVote;
import bean.Candidate;
import bean.GradeVoteFirstIndex;
import bean.GradeVoteSecondIndex;
import bean.User;
import bean.VoteConfig;

public class UserRepositoryImpl extends BaseRepository implements UserRepository{
	
	@SuppressWarnings("unchecked")
	public List<VoteConfig> loadActiveGradeVoteConfigs() {
		String hsqlString = "from VoteConfig where isDeleted=0 and isActive=1";
		Query query = getSession().createQuery(hsqlString);
		List<VoteConfig> activeGradeVoteConfigs = (ArrayList<VoteConfig>) query.list();
		return activeGradeVoteConfigs;
	}
	
	@SuppressWarnings("unchecked")
	public VoteConfig loadEarliestActiveGradeVote() {
		String hsqlString = "from VoteConfig where isDeleted=0 and isActive=1 order by startDate asc";
		Query query = getSession().createQuery(hsqlString);
		List<VoteConfig> sortedActiveGradeVotes = (ArrayList<VoteConfig>) query.list();
		return sortedActiveGradeVotes.size() > 0 ? sortedActiveGradeVotes.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AtomicGradeVote> loadAtomicGradeVotes(Integer userId, Integer voteConfigId) {
		String hsqlString = "from AtomicGradeVote where user.id=? and vote.id=?";
		Query query = getSession().createQuery(hsqlString);
		query.setParameter(0, userId);
		query.setParameter(1, voteConfigId);
		List<AtomicGradeVote> atomicGradeVotes = (ArrayList<AtomicGradeVote>) query.list();
		return atomicGradeVotes;
	}
	
	@Override
	public void saveAllGradeVotes(List<AtomicGradeVoteView> atomicGradeVoteViews) {
		Session hibernSession = getSession();
		for (AtomicGradeVoteView atomicGradeVoteView : atomicGradeVoteViews) {
			AtomicGradeVote gradeVoteTobeSaved = new AtomicGradeVote();
			User user = (User) hibernSession.load(User.class, atomicGradeVoteView.getUserId());
			VoteConfig config = (VoteConfig) hibernSession.load(VoteConfig.class, atomicGradeVoteView.getVoteConfigId());
			Candidate candidate = (Candidate) hibernSession.load(Candidate.class, atomicGradeVoteView.getCandidateId());
			GradeVoteFirstIndex firstIndex = (GradeVoteFirstIndex) hibernSession.load(GradeVoteFirstIndex.class, atomicGradeVoteView.getFirstIndexId());
			if (atomicGradeVoteView.getSecondIndexId() != null) {
				GradeVoteSecondIndex secondIndex = (GradeVoteSecondIndex) hibernSession.load(GradeVoteSecondIndex.class, atomicGradeVoteView.getSecondIndexId());
				gradeVoteTobeSaved.setSecondIndex(secondIndex);
			} else {
				gradeVoteTobeSaved.setSecondIndex(null);
			}
			gradeVoteTobeSaved.setUser(user);
			gradeVoteTobeSaved.setVote(config);
			gradeVoteTobeSaved.setCandidate(candidate);
			gradeVoteTobeSaved.setFirstIndex(firstIndex);
			gradeVoteTobeSaved.setGrade(atomicGradeVoteView.getGrade());
			gradeVoteTobeSaved.setVoter(atomicGradeVoteView.getVoter());
			
			hibernSession.save(gradeVoteTobeSaved);
		}
	}
	

	@Override
	public AtomicGradeVote loadAtomicGradeVoteBy(AtomicGradeVoteView atomicGradeVoteView) {
		Session hibernSession = getSession();
		String hsqlString = "select grade from AtomicGradeVote where user.id=? and vote.id=? and candidate.id=? and firstIndex.id=?";
		if (atomicGradeVoteView.getSecondIndexId() != null) {
			hsqlString += " and secondIndex.id=?";
		}
		Query query = hibernSession.createQuery(hsqlString);
		query.setParameter(0, atomicGradeVoteView.getUserId());
		query.setParameter(1, atomicGradeVoteView.getVoteConfigId());
		query.setParameter(2, atomicGradeVoteView.getCandidateId());
		query.setParameter(3, atomicGradeVoteView.getFirstIndexId());
		if (atomicGradeVoteView.getSecondIndexId() != null) {
			query.setParameter(4, atomicGradeVoteView.getSecondIndexId());
		}
		@SuppressWarnings("unchecked")
		List<Float> grades = query.list();
		AtomicGradeVote atomicGradeVote = new AtomicGradeVote();
		User user = (User) hibernSession.load(User.class, atomicGradeVoteView.getUserId());
		VoteConfig config = (VoteConfig) hibernSession.load(VoteConfig.class, atomicGradeVoteView.getVoteConfigId());
		Candidate candidate = (Candidate) hibernSession.load(Candidate.class, atomicGradeVoteView.getCandidateId());
		GradeVoteFirstIndex firstIndex = (GradeVoteFirstIndex) hibernSession.load(GradeVoteFirstIndex.class, atomicGradeVoteView.getFirstIndexId());
		if (atomicGradeVoteView.getSecondIndexId() != null) {
			GradeVoteSecondIndex secondIndex = (GradeVoteSecondIndex) hibernSession.load(GradeVoteSecondIndex.class, atomicGradeVoteView.getSecondIndexId());
			atomicGradeVote.setSecondIndex(secondIndex);
		} else {
			atomicGradeVote.setSecondIndex(null);
		}
		atomicGradeVote.setUser(user);
		atomicGradeVote.setVote(config);
		atomicGradeVote.setCandidate(candidate);
		atomicGradeVote.setFirstIndex(firstIndex);
		atomicGradeVote.setGrade(grades.size() == 0 ? 0 : (grades.get(0) == null ? 0 : grades.get(0)));
		atomicGradeVote.setVoter(atomicGradeVoteView.getVoter());
		return atomicGradeVote;
	}
	
}
