package convertor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bean.Candidate;
import bean.GradeVoteFirstIndex;
import bean.GradeVoteSecondIndex;
import bean.User;
import bean.VoteConfig;
import view.GradeVoteConfig;

public class ViewToBeanConvertor {
	public static VoteConfig convertToVoteConfig(GradeVoteConfig gradeVoteConfig) {
	 	VoteConfig voteConfig = new VoteConfig();
		voteConfig.setIsDeleted(0);
		voteConfig.setVoteName(gradeVoteConfig.getVoteName());
		voteConfig.setIsAnonymous(gradeVoteConfig.getIsAnonymous());
		for (String candidateName : gradeVoteConfig.getCandidates()) {
			Candidate candidate = new Candidate();
			candidate.setName(candidateName);
			voteConfig.addToCandidates(candidate);
		}
		for (GradeVoteFirstIndex gradeVoteIndex : gradeVoteConfig.getIndexes()) {
			for (GradeVoteSecondIndex secondIndex : gradeVoteIndex.getSecondIndexes()) {
				secondIndex.setGradeVoteFirstIndex(gradeVoteIndex);
			}
			voteConfig.addToGradeVoteIndexes(gradeVoteIndex);
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date voteStartDate = dateFormat.parse(gradeVoteConfig.getStartDate());
			Date voteEndDate = dateFormat.parse(gradeVoteConfig.getEndDate());
			voteConfig.setStartDate(voteStartDate);
			voteConfig.setCompleteDate(voteEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return voteConfig;
	}
	
	public static User convertToUserInfo(String UserInfoJsonStruname,String UserInfoJsonStrpword) {
		User user=new User();
		user.setPassword(UserInfoJsonStrpword);
		user.getPassword();
		user.setUsername(UserInfoJsonStruname);
		user.getUsername();
		return user;
	}
}
