package repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import bean.ElectVote;
import bean.User;
import bean.VoteConfig;

public class LoginRepositoryImpl extends BaseRepository implements LoginRepository {
	@Override
	public User getUser(String userName, String password) {
		Query queryuery = getSession().createQuery("from User where username=?" + "and password=?");
		queryuery.setString(0,userName);
		queryuery.setString(1,password);
		List<User> result = (ArrayList<User>)queryuery.list();
		return result.size() > 0 ? result.get(0) : null;
	}

	@Override
	public boolean hasElectVote() {
		Query query = getSession().createQuery("from ElectVote where isPublish=1");
		List<ElectVote> result = (ArrayList<ElectVote>)query.list();
		return result.size() > 0 ? true : false;
	}

	@Override
	public boolean hasGradeVote() {
		Query query = getSession().createQuery("from VoteConfig where isActive=1");
		List<VoteConfig> result = (ArrayList<VoteConfig>)query.list();
		return result.size() > 0 ? true : false;
	}

}
