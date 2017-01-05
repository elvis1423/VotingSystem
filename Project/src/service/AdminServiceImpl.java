package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;
import repository.AdminRepository;
import view.CandidateView;
import view.GradeVoteConfig;
import view.GradeVoteFinalResultView;
import view.GradeVoteFirstIndexView;
import view.GradeVoteResultView;
import view.GradeVoteResultsView;
import view.GradeVoteSecondIndexView;
import bean.AtomicGradeVote;
import bean.Candidate;
import bean.GradeVoteFirstIndex;
import bean.GradeVoteSecondIndex;
import bean.User;
import bean.VoteConfig;
import convertor.ViewToBeanConvertor;

public class AdminServiceImpl implements AdminService {
	private AdminRepository adminRepository;
	@Override
	public void convertAndSaveVoteConfig(String gradeVoteConfigJsonStr) {
		JSONObject gradeVoteConfigJsonObject = JSONObject.fromObject(gradeVoteConfigJsonStr);
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put(GradeVoteConfig.PARAM_GRADE_VOTE_INDEX, GradeVoteFirstIndex.class);
		classMap.put(GradeVoteFirstIndex.PARAM_SECOND_INDEXES, GradeVoteSecondIndex.class);
		GradeVoteConfig gradeVoteConfig = (GradeVoteConfig) JSONObject.toBean(gradeVoteConfigJsonObject, GradeVoteConfig.class, classMap);
		VoteConfig voteConfig = ViewToBeanConvertor.convertToVoteConfig(gradeVoteConfig);
		adminRepository.createGradeVoteConfig(voteConfig);
	}
	
	@Override
	public List<VoteConfig> getCompletedGradeVoteConfigs() {
		List<VoteConfig> gradeVoteConfigs = adminRepository.loadCompletedGradeVotes();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		for (VoteConfig gradeVoteConfig : gradeVoteConfigs) {
			gradeVoteConfig.setStartDateStr(dateFormat.format(gradeVoteConfig.getStartDate()));
			gradeVoteConfig.setCompleteDateStr(dateFormat.format(gradeVoteConfig.getCompleteDate()));
		}
		return gradeVoteConfigs;
	}

	@Override
	public List<VoteConfig> getAllGradeVoteConfigs() {
		List<VoteConfig> gradeVoteConfigs = adminRepository.loadAllGradeVoteConfigs();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		for (VoteConfig gradeVoteConfig : gradeVoteConfigs) {
			gradeVoteConfig.setStartDateStr(dateFormat.format(gradeVoteConfig.getStartDate()));
			gradeVoteConfig.setCompleteDateStr(dateFormat.format(gradeVoteConfig.getCompleteDate()));
		}
		return gradeVoteConfigs;
	}
	
	@Override
	public boolean publishGradeVoteOrNot(Integer gradeVoteId, boolean isPublish) {
		return adminRepository.updateGradeVoteStatus(gradeVoteId, isPublish);
	}
	
	@Override
	public void deleteGradeVote(Integer gradeVoteId) {
		adminRepository.deleteGradeVote(gradeVoteId);
	}

	public void convertAndsaveUserInfo(String UserInfoJsonStruname, String UserInfoJsonStrpword) {
		User user = ViewToBeanConvertor.convertToUserInfo(UserInfoJsonStruname, UserInfoJsonStrpword);
		adminRepository.createUser(user);
	}

	public void setAdminRepository(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	@Override
	public GradeVoteResultsView getGradeVoteResultsView(Integer gradeVoteId, boolean isAnonymous) {
		GradeVoteResultsView gradeVoteResultsView = new GradeVoteResultsView();
		Map<String, GradeVoteResultView> voterAndGradeVoteResultMap = getVoterAndGradeVoteResultMap(gradeVoteId, isAnonymous);
		gradeVoteResultsView.setVoterAndResultMap(voterAndGradeVoteResultMap);
		for (Entry<String, GradeVoteResultView> voterAndGradeVoteResultSet : voterAndGradeVoteResultMap.entrySet()) {
			gradeVoteResultsView.setGradeVoteName(voterAndGradeVoteResultSet.getValue().getVoteName());
			break;
		}
		
		Map<String, Map<String, Float>> candidateNameAndVoterGradeMapMap = new HashMap<String, Map<String,Float>>();
		for (Entry<String, GradeVoteResultView> voterAndGradeVoteResultSet : voterAndGradeVoteResultMap.entrySet()) {
			String voter = voterAndGradeVoteResultSet.getKey();
			GradeVoteResultView gradeVoteResultView = voterAndGradeVoteResultSet.getValue();
			for (CandidateView candidateView : gradeVoteResultView.getCandidates()) {
				if (candidateNameAndVoterGradeMapMap.containsKey(candidateView.getCandidateName())) {
					Map<String, Float> voterGradeMap = candidateNameAndVoterGradeMapMap.get(candidateView.getCandidateName());
					voterGradeMap.put(voter, candidateView.getResult());
				} else {
					Map<String, Float> voterGradeMap = new HashMap<String, Float>();
					voterGradeMap.put(voter, candidateView.getResult());
					candidateNameAndVoterGradeMapMap.put(candidateView.getCandidateName(), voterGradeMap);
				}
			}
		}
		List<GradeVoteFinalResultView> gradeVoteFinalResultsWithoutMaxAndMinValue = calculateGradeVoteFinalResultsWithoutMaxAndMinValue(candidateNameAndVoterGradeMapMap);
		Collections.sort(gradeVoteFinalResultsWithoutMaxAndMinValue);
		gradeVoteResultsView.setGradeVoteFinalResults(gradeVoteFinalResultsWithoutMaxAndMinValue);
		return gradeVoteResultsView;
	}
	
	private List<GradeVoteFinalResultView> calculateGradeVoteFinalResultsWithoutMaxAndMinValue(Map<String, Map<String, Float>> candidateNameAndVoterGradeMapMap) {
		List<GradeVoteFinalResultView> gradeVoteFinalResults = new ArrayList<GradeVoteFinalResultView>();
		for (Entry<String, Map<String, Float>> candidateNameAndVoterGradeMapSet : candidateNameAndVoterGradeMapMap.entrySet()) {
			GradeVoteFinalResultView gradeVoteFinalResultView = new GradeVoteFinalResultView();
			gradeVoteFinalResultView.setCandidateName(candidateNameAndVoterGradeMapSet.getKey());
			List<Float> gradesForOneCandidate = new ArrayList<Float>();
			for (Entry<String, Float> voterGradeSet : candidateNameAndVoterGradeMapSet.getValue().entrySet()) {
				gradesForOneCandidate.add(voterGradeSet.getValue());
			}
			Collections.sort(gradesForOneCandidate);
			if (gradesForOneCandidate.size() <= 2) {
				Float gradeSum = new Float(0);
				for (Float grade : gradesForOneCandidate) {
					gradeSum += grade;
				}
				if (gradesForOneCandidate.size() == 0) {
					gradeVoteFinalResultView.setGrade(0);
				} else {
					gradeVoteFinalResultView.setGrade(gradeSum/gradesForOneCandidate.size());
				}
			} else {
				Float gradeSum = new Float(0);
				for (int i = 1; i < gradesForOneCandidate.size()-1; i++) {
					gradeSum += gradesForOneCandidate.get(i);
				}
				gradeVoteFinalResultView.setGrade(gradeSum/gradesForOneCandidate.size());
			}
			gradeVoteFinalResults.add(gradeVoteFinalResultView);
		}
		return gradeVoteFinalResults;
	}

	@Override
	public Map<String, GradeVoteResultView> getVoterAndGradeVoteResultMap(Integer gradeVoteId, boolean isAnonymous) {
		Map<String, GradeVoteResultView> voterAndGradeVoteResultMap = new HashMap<String, GradeVoteResultView>();
		if(!isAnonymous) {
			Map<String, List<AtomicGradeVote>> voterAndAtomicGradeVotesMap = new HashMap<String, List<AtomicGradeVote>>();
			List<AtomicGradeVote> gradeVoteResults = adminRepository.loadGradeVoteResults(gradeVoteId);
			for (AtomicGradeVote atomicGradeVoteResult : gradeVoteResults) {
				String voter = atomicGradeVoteResult.getUser().getUsername();
				if (voterAndAtomicGradeVotesMap.get(voter) == null) {
					List<AtomicGradeVote> newAtomicGradeVotes = new ArrayList<AtomicGradeVote>();
					newAtomicGradeVotes.add(atomicGradeVoteResult);
					voterAndAtomicGradeVotesMap.put(voter, newAtomicGradeVotes);
				} else {
					voterAndAtomicGradeVotesMap.get(voter).add(atomicGradeVoteResult);
				}
			}
			
			for (Entry<String, List<AtomicGradeVote>> voterAndAtomicGradeVotesSet : voterAndAtomicGradeVotesMap.entrySet()) {
				String voter = voterAndAtomicGradeVotesSet.getKey();
				GradeVoteResultView gradeVoteResultView = new GradeVoteResultView();
				Map<CandidateView, List<GradeVoteFirstIndexView>> candidateFirstIndexViewsMap = new HashMap<CandidateView, List<GradeVoteFirstIndexView>>();
				for (int i = 0; i < voterAndAtomicGradeVotesSet.getValue().size(); i++) {
					AtomicGradeVote atomicGradeVote = voterAndAtomicGradeVotesSet.getValue().get(i);
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
				gradeVoteResultView.sortCandidateViewList();
				voterAndGradeVoteResultMap.put(voter, gradeVoteResultView);
			}
		}
		return voterAndGradeVoteResultMap;
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
	
	public static void main(String[] args) {
		List<Float> gradesFloats = new ArrayList<Float>();
		gradesFloats.add((float) 85);
		gradesFloats.add((float) 96);
		gradesFloats.add((float) 75);
		gradesFloats.add((float) 81);
		gradesFloats.add((float) 66);
		Collections.sort(gradesFloats);
		for (Float grade : gradesFloats) {
			System.out.println(grade);
		}
	}

}
