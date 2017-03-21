import bean.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * Created by apple on 17/2/25.
 */
public class CriteriaTest {

    private static SessionFactory sessionFactory;

    Logger logger = LoggerFactory.getLogger(getClass());

    static {
        File file = new File("/Users/apple/develop/IdeaProjects/javaeeLearn/twoday/src/main/resources/hibernate.cfg.xml");
        sessionFactory = new Configuration().configure(file).buildSessionFactory();
    }


    public void testCriteria() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        Criteria crit = session.createCriteria(Employee.class).add(Restrictions.eq("salary", 29));
        crit.setMaxResults(50);
        List<Employee> emps = crit.list();
        for (Employee ee : emps
                ) {
            System.out.println(ee);
        }
        ts.commit();
        session.close();
    }

    public void criteria2() {
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(Employee.class);
        Employee employee = new Employee();
        employee.setFirstName("111");
        crit.add(Example.create(employee).excludeZeroes())
                .setProjection(Projections.count("id"));
        List list = crit.list();
        logger.info(list.toString());
    }


    public void criteria3() {
        Session session = sessionFactory.openSession();
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Employee.class);
        Employee employee = new Employee();
        employee.setFirstName("111");
        detachedCriteria.add(Example.create(employee).excludeZeroes())
                .setProjection(Property.forName("id") );
        Integer count = (Integer) detachedCriteria.getExecutableCriteria(session).uniqueResult();
        logger.info(String.valueOf(count));
    }

}
