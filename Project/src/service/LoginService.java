package service;

import view.LoginStatus;

public interface LoginService {
	public LoginStatus getLoginStatus(String userName, String password);
	public boolean hasElectVote();
	public boolean hasGradeVote();
}
