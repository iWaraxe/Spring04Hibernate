package com.coherentsolutions.chapter2.section25;

import com.coherentsolutions.chapter2.section25.entity.Child;
import com.coherentsolutions.chapter2.section25.entity.Section;
import com.github.javafaker.Faker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Relation2ManyToMany {
    public static final int NUMBER_OF_SECTIONS = 3;

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

            // Create a Child
            Child child = new Child("Igor", 13);

            session.beginTransaction();

            // Create and save Sections
            for (int i = 0; i < NUMBER_OF_SECTIONS; i++) {
                Section section = new Section(faker.funnyName().name());
                session.save(section); // Save each Section
                child.addSectionToChild(section); // Add Section to Child
            }

            // Save Child
            session.save(child);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            if (session != null) {
                session.close();
            }
            factory.close();
        }
    }
}
