package com.coherentsolutions.chapter1.section06;

import com.coherentsolutions.chapter1.entity.Employee;
import com.github.javafaker.Faker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PopulateDB {
    public static final int NUMBER_OF_RECORDS = 30;

    private static Faker faker = new Faker();

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

            for (int i = 0; i<NUMBER_OF_RECORDS; i++) {
                try {
                    transaction = session.beginTransaction();
                    Employee employee = new Employee(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.commerce().department(),
                        faker.number().numberBetween(5000,10000));
                    System.out.println(employee);
                    session.save(employee);
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction != null) transaction.rollback();
                    e.printStackTrace();
                }
            }
        System.out.println(NUMBER_OF_RECORDS + " employees saved successfully");
        session.close();
        sessionFactory.close();
    }
}
