package com.coherentsolutions.section08;

import com.coherentsolutions.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HqlExample1Get {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            //To remove JPA language checks => settings/inspections/query/JPA/query language checks
            List<Employee> emps = session.createQuery("from Employee") // Class name but not the table name, we work with Java Code
                            .getResultList();

            for (Employee e: emps){
                System.out.println(e);
            }

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
