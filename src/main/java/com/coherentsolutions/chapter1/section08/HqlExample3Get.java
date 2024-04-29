package com.coherentsolutions.section08;

import com.coherentsolutions.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HqlExample3Get {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            //To remove JPA language checks => settings/inspections/query/JPA/query language checks
            List<Employee> emps = session.createQuery("FROM Employee " +
                            "WHERE name = 'Igor' AND salary > 5000") // name is not the column name, it's class field name.
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
