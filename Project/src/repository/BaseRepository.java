package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.User;

public class BaseRepository {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public User getUserBy(Integer userId) {
		return (User) getSession().get(User.class, userId);
	}
}
