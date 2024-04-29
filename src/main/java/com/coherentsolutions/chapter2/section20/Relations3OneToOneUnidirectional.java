package com.coherentsolutions.chapter2.section20;

import com.coherentsolutions.chapter2.entity.Detail;
import com.coherentsolutions.chapter2.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Relations2OneToOneUnidirectional {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();

            Employee employee = new Employee("Oleg", "Hibernatov", "Training Center", 5000);
            Detail detail = new Detail("Minsk", "375297514533", "oleg@gmail.com");
            employee.setEmpDetail(detail);

            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
