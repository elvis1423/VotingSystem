package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import repository.UserRepository;
import view.AtomicGradeVoteView;
import view.CandidateView;
import view.GradeVoteFirstIndexView;
import view.GradeVoteResultView;
import view.GradeVoteSecondIndexView;
import bean.AtomicGradeVote;
import bean.Candidate;
import bean.GradeVoteFirstIndex;
import bean.GradeVoteSecondIndex;
import bean.VoteConfig;

public class UserServiceImpl implements UserService{
	private UserRepository userRepository;
	
	@Override
	public VoteConfig getEarliestActiveConfigGradeVote() {
		return userRepository.loadEarliestActiveGradeVote();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void convertAndSaveAtomicGradeVotes(String atomicGradeVotesJsonStr) {
		JSONArray atomicGradeVotesJsonArray = JSONArray.fromObject(atomicGradeVotesJsonStr);
		List<AtomicGradeVoteView> atomicGradeVotes = (ArrayList<AtomicGradeVoteView>) JSONArray.toCollection(atomicGradeVotesJsonArray, AtomicGradeVoteView.class);
		if (atomicGradeVotes != null && atomicGradeVotes.size() > 0) {
			if (!isGradeVoteAlreadySaved(atomicGradeVotes.get(0))) {
				userRepository.saveAllGradeVotes(atomicGradeVotes);
			}
		}
	}
	
	private boolean isGradeVoteAlreadySaved(AtomicGradeVoteView atomicGradeVoteView) {
		List<AtomicGradeVote> atomicGradeVotes = userRepository.loadAtomicGradeVotes(atomicGradeVoteView.getUserId(), atomicGradeVoteView.getVoteConfigId());
		return atomicGradeVotes.size() > 0 ? true : false;
	}


	@SuppressWarnings("unchecked")
	@Override
	public GradeVoteResultView constructGradeVoteResultView(String atomicGradeVotesJsonStr) {
		GradeVoteResultView gradeVoteResultView = new GradeVoteResultView();
		JSONArray atomicGradeVotesJsonArray = JSONArray.fromObject(atomicGradeVotesJsonStr);
		List<AtomicGradeVoteView> atomicGradeVotes = (ArrayList<AtomicGradeVoteView>) JSONArray.toCollection(atomicGradeVotesJsonArray, AtomicGradeVoteView.class);
		
		Map<CandidateView, List<GradeVoteFirstIndexView>> candidateFirstIndexViewsMap = new HashMap<CandidateView, List<GradeVoteFirstIndexView>>();
		
		for (int i = 0; i < atomicGradeVotes.size(); i++) {
			AtomicGradeVoteView atomicGradeVoteView = atomicGradeVotes.get(i); 
			AtomicGradeVote atomicGradeVote = userRepository.loadAtomicGradeVoteBy(atomicGradeVoteView);
			if (i == 0) {
				gradeVoteResultView.setVoteName(atomicGradeVote.getVote().getVoteName());
			}
			Candidate candidate = atomicGradeVote.getCandidate();
			CandidateView candidateView = new CandidateView();
			candidateView.setCandidateName(candidate.getName());
			GradeVoteFirstIndex firstIndex = atomicGradeVote.getFirstIndex();
			GradeVoteSecondIndex secondIndex = atomicGradeVote.getSecondIndex();
			if (candidateFirstIndexViewsMap.get(candidateView) == null) {
				List<GradeVoteFirstIndexView> firstIndexViews = constructFirstIndexViewListWhenNotExistInMapValue(atomicGradeVote);
				candidateFirstIndexViewsMap.put(candidateView, firstIndexViews);
			} else {
				List<GradeVoteFirstIndexView> existFirstIndexViews = candidateFirstIndexViewsMap.get(candidateView);
				boolean isFirstIndexAlreadyExistInMapValue = false;
				for (int j = 0; j < existFirstIndexViews.size(); j++) {
					GradeVoteFirstIndexView firstIndexView = existFirstIndexViews.get(j);
					if (firstIndexView.getName().equals(firstIndex.getName())) {
						isFirstIndexAlreadyExistInMapValue = true;
						if (secondIndex != null) {
							GradeVoteSecondIndexView secondIndexView = new GradeVoteSecondIndexView();
							secondIndexView.setName(secondIndex.getName());
							secondIndexView.setWeight(secondIndex.getWeight());
							secondIndexView.setGrade(atomicGradeVote.getGrade());
							firstIndexView.getSecondIndexes().add(secondIndexView);
						}
					}
				}
				if (!isFirstIndexAlreadyExistInMapValue) {
					List<GradeVoteFirstIndexView> firstIndexViews = constructFirstIndexViewListWhenNotExistInMapValue(atomicGradeVote);
					for (GradeVoteFirstIndexView firstIndexView : firstIndexViews) {
						existFirstIndexViews.add(firstIndexView);
					}
				}
			}
			
		}
		for (Entry<CandidateView, List<GradeVoteFirstIndexView>> entry : candidateFirstIndexViewsMap.entrySet()) {
			CandidateView candidateViewFromMap = entry.getKey();
			candidateViewFromMap.setFirstIndexViews(entry.getValue());
			calculateGradeVoteResult(candidateViewFromMap);
			gradeVoteResultView.getCandidates().add(candidateViewFromMap);
		}
		return gradeVoteResultView;
	}

	private void calculateGradeVoteResult(CandidateView candidateView) {
		float firstWweightSum = 0;
		float gradeSumOverWeight = 0;
		for (GradeVoteFirstIndexView firstIndexView : candidateView.getFirstIndexViews()) {
			firstWweightSum += firstIndexView.getWeight();
		}
		for (GradeVoteFirstIndexView firstIndexView : candidateView.getFirstIndexViews()) {
			if (firstIndexView.getSecondIndexes().size() == 0) {
				gradeSumOverWeight += firstIndexView.getGrade() * firstIndexView.getWeight(); 
			} else {
				for (GradeVoteSecondIndexView secondIndexView : firstIndexView.getSecondIndexes()) {
					gradeSumOverWeight += secondIndexView.getGrade() * secondIndexView.getWeight();
				}
			}
		}
		candidateView.setResult(gradeSumOverWeight/firstWweightSum); 
	}

	private List<GradeVoteFirstIndexView> constructFirstIndexViewListWhenNotExistInMapValue(AtomicGradeVote atomicGradeVote) {
		GradeVoteFirstIndex firstIndex = atomicGradeVote.getFirstIndex();
		GradeVoteSecondIndex secondIndex = atomicGradeVote.getSecondIndex();
		List<GradeVoteFirstIndexView> firstIndexViews = new ArrayList<GradeVoteFirstIndexView>();
		GradeVoteFirstIndexView firstIndexeView = new GradeVoteFirstIndexView();
		firstIndexeView.setName(firstIndex.getName());
		firstIndexeView.setWeight(firstIndex.getWeight());
		firstIndexeView.setGrade(atomicGradeVote.getGrade());
		if (secondIndex != null) {
			GradeVoteSecondIndexView secondIndexView = new GradeVoteSecondIndexView();
			secondIndexView.setName(secondIndex.getName());
			secondIndexView.setWeight(secondIndex.getWeight());
			secondIndexView.setGrade(atomicGradeVote.getGrade());
			List<GradeVoteSecondIndexView> secondIndexViews = new ArrayList<GradeVoteSecondIndexView>();
			secondIndexViews.add(secondIndexView);
			firstIndexeView.setSecondIndexes(secondIndexViews);
		}
		firstIndexViews.add(firstIndexeView);
		return firstIndexViews;
	}
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
