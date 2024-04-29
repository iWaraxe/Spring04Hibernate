package com.coherentsolutions.chapter2.section24;

import com.coherentsolutions.chapter2.section24.entity.Department;
import com.coherentsolutions.chapter2.section24.entity.Employee;
import com.github.javafaker.Faker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerVsLazyLoading01 {
    public static final int NUMBER_OF_EMPLOYEES = 10;
    public static final int MINIMUM_SALARY = 800;
    public static final int MAXIMUM_SALARY = 1500;
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

            Department department = new Department("IT", MINIMUM_SALARY, MAXIMUM_SALARY);
            for (int i = 0; i < NUMBER_OF_EMPLOYEES; i++) {
                department.addEmployeeToDepartment(
                        new Employee(
                                faker.name().firstName(),
                                faker.name().lastName(),
                                faker.number().numberBetween(MINIMUM_SALARY,MAXIMUM_SALARY)
                        )
                );
            }

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
