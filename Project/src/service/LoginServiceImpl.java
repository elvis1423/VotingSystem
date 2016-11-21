package service;

import bean.User;
import repository.LoginRepository;
import view.LoginStatus;
import enums.LoginType;

public class LoginServiceImpl implements LoginService {
	private LoginRepository loginRepository;

	@Override
	public LoginStatus getLoginStatus(String userName, String password) {
		User user = loginRepository.getUser(userName, password);
		LoginStatus loginStatus = new LoginStatus();
		if (user == null) {
			return null;
		} else {
			loginStatus.setUserId(user.getId());
			if (user.getUsername().equals("admin")) {
				loginStatus.setLoginType(LoginType.ADMIN);
				return loginStatus;
			} else {
				loginStatus.setLoginType(LoginType.USER);
				return loginStatus;
			}
		}
	}
	
	public void setLoginRepository(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public boolean hasElectVote() {
		return this.loginRepository.hasElectVote();
	}

	@Override
	public boolean hasGradeVote() {
		return this.loginRepository.hasGradeVote();
	}
}
