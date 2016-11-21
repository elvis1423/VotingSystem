package view;

import java.util.ArrayList;
import java.util.List;

public class CandidateView implements Comparable<CandidateView>{
	
	private String candidateName;
	private List<GradeVoteFirstIndexView> firstIndexViews = new ArrayList<GradeVoteFirstIndexView>();
	private float result;
	
	public String getCandidateName() {
		return candidateName;
	}
	
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public List<GradeVoteFirstIndexView> getFirstIndexViews() {
		return firstIndexViews;
	}

	public void setFirstIndexViews(List<GradeVoteFirstIndexView> firstIndexeViews) {
		this.firstIndexViews = firstIndexeViews;
	}

	public float getResult() {
		return result;
	}

	public void setResult(float result) {
		this.result = result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CandidateView)) {
			return false;
		}
		CandidateView anotherCandidateView = (CandidateView) o;
		if (this.getCandidateName() == null) {
			return anotherCandidateView.getCandidateName() == null;
		}
		return this.getCandidateName().equals(anotherCandidateView.getCandidateName());
	}
	
	@Override
	public int hashCode() {
		int hashCode = 37;
		if (candidateName != null) {
			hashCode = 37 * hashCode + candidateName.hashCode();
		}
		return hashCode;
	}

	@Override
	public int compareTo(CandidateView o) {
		if (this.candidateName.compareTo(o.candidateName) > 0) {
			return 1;
		} 
		if (this.candidateName.compareTo(o.candidateName) < 0) {
			return -1;
		}
		return 0;
	}
}
