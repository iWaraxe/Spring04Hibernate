package com.coherentsolutions.chapter2.section22;

import com.coherentsolutions.chapter2.section22.entity.Department;
import com.coherentsolutions.chapter2.section22.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Relation1OneToManyBiDirectional {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();

            Department department = new Department("IT", 300, 1200);
            Employee employee1 = new Employee("Igor", "Waraxe", 800);
            Employee employee2 = new Employee("Maryia", "Lapina", 1200);

            department.addEmployeeToDepartment(employee1);
            department.addEmployeeToDepartment(employee2);

            session.beginTransaction();
            session.save(department);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
