package action;

import service.LoginService;
import view.LoginStatus;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import enums.LoginType;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;

	private String username;
	private String password;
	private String message;
	
	private boolean hasElectVote;
	private boolean hasGradeVote;

	@Override
	public String execute() throws Exception {
		System.out.println(username);
		LoginStatus loginStatus = loginService.getLoginStatus(username, password);
		if (loginStatus == null) {
			setMessage(new String("账号或密码错误"));
			return INPUT;
		} else {
			ActionContext.getContext().getSession().put("loginStatus", loginStatus);
			if (loginStatus.getLoginType() == LoginType.ADMIN) {
				return SUCCESS;
			} else {
				setHasElectVote(loginService.hasElectVote());
				setHasGradeVote(loginService.hasGradeVote());
				return LOGIN;
			}
		}
	}
	
	public String logout() {
		ActionContext.getContext().getSession().clear();
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public boolean getHasElectVote() {
		return hasElectVote;
	}

	public void setHasElectVote(boolean hasElectVote) {
		this.hasElectVote = hasElectVote;
	}

	public boolean getHasGradeVote() {
		return hasGradeVote;
	}

	public void setHasGradeVote(boolean hasGradeVote) {
		this.hasGradeVote = hasGradeVote;
	}
}
