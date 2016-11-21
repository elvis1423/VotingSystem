package repository;

import bean.User;

public interface LoginRepository {
	public User getUser(String userNamne, String password);
	public boolean hasElectVote();
	public boolean hasGradeVote();
}
