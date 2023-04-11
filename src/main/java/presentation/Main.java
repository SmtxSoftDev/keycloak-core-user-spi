package presentation;


import domain.entities.User;
import infrastructure.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            User user = session.get(User.class, "1");
            System.out.println(user);
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}