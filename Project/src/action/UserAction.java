package action;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import service.UserService;
import view.GradeVoteResultView;
import bean.VoteConfig;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ParameterAware{
	private static final long serialVersionUID = -183238090409414996L;
	private Map<String, String[]> parameters;  //needed by ParameterAware
	private UserService userService;  //injected by spring
	private VoteConfig earliestActiveGradeVote;  //used for UI: usergradevote.jsp
	private boolean isGradeVoteHasSecondIndex;  //used for UI: usergradevote.jsp
	private GradeVoteResultView userGradeVote;  //used for UI: postusergradevote.jsp
	
	public String retrieveEarliestActiveGradeVote() {
		Object object = ActionContext.getContext().getSession().get("loginStatus");
		if (object == null) {
			return "unloged";
		}
		VoteConfig earliestActiveConfigGradeVote = userService.getEarliestActiveConfigGradeVote();
		setEarliestActiveGradeVote(earliestActiveConfigGradeVote);
		if (earliestActiveConfigGradeVote == null) {
			setGradeVoteHasSecondIndex(false);
		} else {
			setGradeVoteHasSecondIndex(earliestActiveConfigGradeVote.hasSecondIndex());
		}
		return SUCCESS;
	}
		
	public String saveGradeVote() {
		String[] params = (String[]) parameters.get("atomicGradeVotesJsonStr");
		String atomicGradeVotesJsonStr = new String(params[0]);
		System.out.println(atomicGradeVotesJsonStr);
		userService.convertAndSaveAtomicGradeVotes(atomicGradeVotesJsonStr);
		GradeVoteResultView gradeVoteResultView = userService.constructGradeVoteResultView(atomicGradeVotesJsonStr);
		setUserGradeVote(gradeVoteResultView);
		setGradeVoteHasSecondIndex(gradeVoteResultView.hasSecondIndexView());
		return SUCCESS;
	}
	
	@Override
	public void setParameters(Map<String, String[]> arg0) {
		parameters = arg0;
	}

	public VoteConfig getEarliestActiveGradeVote() {
		return earliestActiveGradeVote;
	}

	public void setEarliestActiveGradeVote(VoteConfig earliestActiveGradeVote) {
		this.earliestActiveGradeVote = earliestActiveGradeVote;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public GradeVoteResultView getUserGradeVote() {
		return userGradeVote;
	}

	public void setUserGradeVote(GradeVoteResultView userGradeVote) {
		this.userGradeVote = userGradeVote;
	}

	public boolean getIsGradeVoteHasSecondIndex() {
		return isGradeVoteHasSecondIndex;
	}

	public void setGradeVoteHasSecondIndex(boolean isGradeVoteHasSecondIndex) {
		this.isGradeVoteHasSecondIndex = isGradeVoteHasSecondIndex;
	}

}
