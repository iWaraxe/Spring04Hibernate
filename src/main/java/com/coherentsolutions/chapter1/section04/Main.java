package com.coherentsolutions.section04;

import com.coherentsolutions.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main {

    public static void main(String[] args) {
        Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        Session session = con.buildSessionFactory(reg).openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee emp = new Employee();
            emp.setId(1);
            emp.setName("John");
            emp.setSurname("Doe");
            emp.setDepartment("IT");
            emp.setSalary(50000);
            session.save(emp);
            tx.commit(); // Commit the transaction to save the data
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
