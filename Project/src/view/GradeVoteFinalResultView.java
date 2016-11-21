package view;

public class GradeVoteFinalResultView implements Comparable<GradeVoteFinalResultView>{
	private String candidateName;
	private float grade;

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof GradeVoteFinalResultView)) {
			return false;
		}
		GradeVoteFinalResultView anotherGradeVoteFinalResulView = (GradeVoteFinalResultView) o;
		if (this.getCandidateName() == null) {
			return anotherGradeVoteFinalResulView.getCandidateName() == null;
		}
		return this.getCandidateName().equals(anotherGradeVoteFinalResulView.getCandidateName());
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
	public int compareTo(GradeVoteFinalResultView o) {
		if (this.candidateName.compareTo(o.candidateName) > 0) {
			return 1;
		} 
		if (this.candidateName.compareTo(o.candidateName) < 0) {
			return -1;
		}
		return 0;
	}
}
