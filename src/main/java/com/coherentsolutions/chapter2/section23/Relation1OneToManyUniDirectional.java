package com.coherentsolutions.chapter2.section23;

import com.coherentsolutions.chapter2.section23.entity.Department;
import com.coherentsolutions.chapter2.section23.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Relation1OneToManyUniDirectional  {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();

            Department department = new Department("HR", 500, 1500);
            Employee employee1 = new Employee("Jon", "Snow", 800);
            Employee employee2 = new Employee("Emilia", "Platter", 1000);

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
