import application.FootballApplication;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StartApp {


    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        FootballApplication footballApplication = new FootballApplication();
        footballApplication.start();

    }
}