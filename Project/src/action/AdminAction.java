package action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ParameterAware;

import service.AdminService;
import view.GradeVoteResultsView;
import bean.VoteConfig;

import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport  implements ParameterAware {
	private static final long serialVersionUID = -1035936541941914664L;
	private AdminService adminService;
	private Map<String, String[]> parameters;  //needed by ParameterAware
	private List<VoteConfig> allGradeVoteConfigs = new ArrayList<VoteConfig>();
	
	private Integer gradeVoteId;
	private InputStream inputStream;
	private String work;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String saveUserInfo() throws Exception {
		String[] uname = (String[]) parameters.get("username");
		String[] pword = (String[]) parameters.get("password");
		String[] ident = (String[]) parameters.get("identifypassword");
		if(uname !=null&&equals(pword)==equals(ident)){
		String UserInfoJsonStruname = new String(uname[0]);
		String UserInfoJsonStrpword = new String(pword[0]);
		adminService.convertAndsaveUserInfo( UserInfoJsonStruname, UserInfoJsonStrpword);
		return SUCCESS;} else 
			return "FAILED";
		
	}
	
	public String viewGradeVoteDetailResult() {
		String[] gradeVoteResultIdStrs = (String[]) parameters.get("gradeVoteId");
		String gradeVoteResultIdStr = new String(gradeVoteResultIdStrs[0]);
		GradeVoteResultsView gradeVoteResultsView2 = adminService.getGradeVoteResultsView(Integer.valueOf(gradeVoteResultIdStr), false);
		try {
			JSONObject gradeVoteResultsViewJsonObject = JSONObject.fromObject(gradeVoteResultsView2);
			inputStream = new ByteArrayInputStream(gradeVoteResultsViewJsonObject.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String deleteGradeVote() {
		try {
			adminService.deleteGradeVote(gradeVoteId);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	public String dePublishGradeVode() {
		try {
			adminService.publishGradeVoteOrNot(gradeVoteId, false);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	public String publishGradeVote() {
		try {
			adminService.publishGradeVoteOrNot(gradeVoteId, true);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	public String saveVoteConfig() throws Exception {
		String[] params = (String[]) parameters.get("voteConfigJsonStr");
		String voteConfigJsonStr = new String(params[0]);
		adminService.convertAndSaveVoteConfig(voteConfigJsonStr);
		return SUCCESS;
	}
	
	public String viewGradeVoteResult() {
		setAllGradeVoteConfigs(adminService.getCompletedGradeVoteConfigs());
		return SUCCESS;
	}
	
	public String viewGradeVodeConfig() {
		setAllGradeVoteConfigs(adminService.getAllGradeVoteConfigs());
		return SUCCESS;
	}
	
	public String deleteGradeVodeConfig() {
		return SUCCESS;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public List<VoteConfig> getAllGradeVoteConfigs() {
		return allGradeVoteConfigs;
	}

	public void setAllGradeVoteConfigs(List<VoteConfig> allGradeVoteConfigs) {
		this.allGradeVoteConfigs = allGradeVoteConfigs;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public void setGradeVoteId(Integer gradeVoteId) {
		this.gradeVoteId = gradeVoteId;
	}
}
