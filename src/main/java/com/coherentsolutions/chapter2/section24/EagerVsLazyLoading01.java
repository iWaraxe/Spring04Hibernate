package com.coherentsolutions.chapter2.section24;

import com.coherentsolutions.chapter2.section22.entity.Department;
import com.coherentsolutions.chapter2.section22.entity.Employee;
import com.github.javafaker.Faker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerVsLaziLoading01 {
    static Faker faker = new Faker();
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();

            Department department = new Department("Sales", 800, 1500);
            Employee employee1 = new Employee(faker.gameOfThrones().character(), faker.gameOfThrones().house(), 800);
            Employee employee2 = new Employee(faker.gameOfThrones().character(), faker.gameOfThrones().house(), 1000);
            Employee employee3 = new Employee(faker.gameOfThrones().character(), faker.gameOfThrones().house(), 1500);

            department.addEmployeeToDepartment(employee1);
            department.addEmployeeToDepartment(employee2);
            department.addEmployeeToDepartment(employee3);

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
