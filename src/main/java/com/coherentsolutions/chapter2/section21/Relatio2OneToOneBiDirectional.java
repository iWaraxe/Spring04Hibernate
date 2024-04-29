package com.coherentsolutions.chapter2.section21;

import com.coherentsolutions.chapter2.section21.entity.Detail;
import com.coherentsolutions.chapter2.section21.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Relatio2OneToOneBiDirectional {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            Employee employee = new Employee("Oleg", "Hibernatov", "Training Center", 5000);
            Detail detail = new Detail("Minsk", "375297514533", "oleg@gmail.com");

            employee.setEmpDetail(detail);
            detail.setEmployee(employee);

            session.beginTransaction();
            session.save(detail);
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}

