package data.repositories;

import domain.entities.User;
import domain.interfaces.IUserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements IUserRepository {

    private SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findUserById(String id) {
        try(Session session = sessionFactory.openSession()){
            return session.get(User.class, id);
        }
    }

    @Override
    public User findUserByLogin(String Login) {
        try(Session session = sessionFactory.openSession()){
            Query<User> query = session.createQuery("from User where Login = :Login");
            query.setParameter("Login", Login);
            return  query.uniqueResult();
        }
    }

    @Override
    public User findUserByEmail(String Email) {
        try(Session session = sessionFactory.openSession()){
            Query<User> query = session.createQuery("from User where Email = :Email");
            query.setParameter("Email", Email);
            return  query.uniqueResult();
        }
    }

    @Override
    public List<User> findUsers(String query) {

        try(Session session = sessionFactory.openSession()){
            Query<User> userQuery =
                    session.createQuery("from User where (FirstName = :FirstName OR LastName = %:LastName%)");
            userQuery.setParameter("FirstName", query);
            userQuery.setParameter("LastName", query);
            return userQuery.list();
        }
    }

    @Override
    public boolean validateCredentials(String Login, String Password) {
        return findUserByLogin(Login).getPassword().equals(Password);
    }

    @Override
    public boolean updateCredentials(String Login, String Password) {
        return true;
    }
}