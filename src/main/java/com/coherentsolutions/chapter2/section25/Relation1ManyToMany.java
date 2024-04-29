package com.coherentsolutions.chapter2.section25;

import com.coherentsolutions.chapter2.section25.entity.Child;
import com.coherentsolutions.chapter2.section25.entity.Section;
import com.github.javafaker.Faker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Relation1ManyToMany {
    public static final int NUMBER_OF_CHILDREN = 10;
    static Faker faker = new Faker();

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();

            Section section1 = new Section("Dance");
            for (int i = 0; i < NUMBER_OF_CHILDREN; i++) {
                section1.addChildToSection(
                        new Child(
                                faker.name().firstName(),
                                faker.number().randomDigitNotZero()
                        )
                );
            }

            session.beginTransaction();
            session.save(section1);
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
