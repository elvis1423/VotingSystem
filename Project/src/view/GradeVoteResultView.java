package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradeVoteResultView {
	private String voteName;
	private List<CandidateView> candidates = new ArrayList<CandidateView>();
	
	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	public List<CandidateView> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<CandidateView> candidates) {
		this.candidates = candidates;
	}
	
	public void sortCandidateViewList() {
		Collections.sort(this.candidates);
	}
	
	public boolean hasSecondIndexView() {
		if (this.candidates.size() == 0) {
			return false;
		}
		for (CandidateView candidateView : candidates) {
			for (GradeVoteFirstIndexView firstIndexView : candidateView.getFirstIndexViews()) {
				if (firstIndexView.getSecondIndexes().size() == 0) {
					continue;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		GradeVoteResultView gradeVoteResultView = new GradeVoteResultView();
		List<CandidateView> candidateViews = new ArrayList<CandidateView>();
		CandidateView candidateView1 = new CandidateView();
		candidateView1.setCandidateName("Benny");
		candidateViews.add(candidateView1);
		
		CandidateView candidateView2 = new CandidateView();
		candidateView2.setCandidateName("Elvis");
		candidateViews.add(candidateView2);
		
		CandidateView candidateView3 = new CandidateView();
		candidateView3.setCandidateName("Denny");
		candidateViews.add(candidateView3);
		
		CandidateView candidateView4 = new CandidateView();
		candidateView4.setCandidateName("Carol");
		candidateViews.add(candidateView4);
		
		CandidateView candidateView5 = new CandidateView();
		candidateView5.setCandidateName("Adam");
		candidateViews.add(candidateView5);
		
		gradeVoteResultView.setCandidates(candidateViews);
		gradeVoteResultView.sortCandidateViewList();
		
		for (CandidateView candidateView : candidateViews) {
			System.out.println(candidateView.getCandidateName());
		}
	}
}
