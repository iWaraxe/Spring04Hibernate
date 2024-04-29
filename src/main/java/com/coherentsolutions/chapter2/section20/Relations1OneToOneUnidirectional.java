package com.coherentsolutions.chapter2.section20;

import com.coherentsolutions.chapter2.section20.entity.Detail;
import com.coherentsolutions.chapter2.section20.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Relations1OneToOneUnidirectional {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();

            Employee employee = new Employee("Igor", "Waraxe", "Training Center", 4000);
            Detail detail = new Detail("Wrocław", "572 110 799", "igorwaraxe@gmail.com");
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
