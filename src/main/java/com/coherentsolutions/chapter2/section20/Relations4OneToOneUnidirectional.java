package com.coherentsolutions.chapter2.section20;

import com.coherentsolutions.chapter2.entity.Detail;
import com.coherentsolutions.chapter2.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Relations3OneToOneUnidirectional {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {

            session.beginTransaction();
            Employee emp = session.get(Employee.class, 1);

            System.out.println(emp.getEmpDetail()); // we don't write SQL but rather use cascade

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
