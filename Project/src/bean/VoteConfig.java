package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteConfig {
	private Integer id;
	//0 stands for grade vote, 1 stands for elect vote
	private Integer isDeleted;
	private String voteName;
	private List<Candidate> candidates = new ArrayList<Candidate>();
	private List<GradeVoteFirstIndex> gradeVoteIndexes = new ArrayList<GradeVoteFirstIndex>();
	
	private Integer isAnonymous;
	private Date startDate;
	private String startDateStr;
	private Date completeDate;
	private String completeDateStr;
	private Integer isActive = 0;

	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}
	
	public void addToCandidates(Candidate candidate) {
		candidate.setVoteConfig(this);
		candidates.add(candidate);
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
	
	public void addToGradeVoteIndexes(GradeVoteFirstIndex gradeVoteIndex) {
		gradeVoteIndex.setVoteConfig(this);
		gradeVoteIndexes.add(gradeVoteIndex);
	}

	public List<GradeVoteFirstIndex> getGradeVoteIndexes() {
		return gradeVoteIndexes;
	}

	public void setGradeVoteIndexes(List<GradeVoteFirstIndex> gradeVoteIndexes) {
		this.gradeVoteIndexes = gradeVoteIndexes;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getCompleteDateStr() {
		return completeDateStr;
	}

	public void setCompleteDateStr(String completeDateStr) {
		this.completeDateStr = completeDateStr;
	}

	public boolean hasSecondIndex() {
		if (this.gradeVoteIndexes == null || this.gradeVoteIndexes.size() == 0) {
			return false;
		}
		for (GradeVoteFirstIndex firstIndex : this.gradeVoteIndexes) {
			if (firstIndex.getSecondIndexes() == null || firstIndex.getSecondIndexes().size() == 0) {
				continue;
			} else {
				return true;
			}
		}
		return false;
	}

	public Integer getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Integer isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
}
