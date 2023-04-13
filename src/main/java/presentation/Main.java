package presentation;


import domain.entities.User;
import infrastructure.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            Query<User> query = session.createQuery("from User where Login = :Login");
            query.setParameter("Login", "user-1");
            User users = query.uniqueResult();
            System.out.println(users);

        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}