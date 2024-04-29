package com.coherentsolutions.section09;

import com.coherentsolutions.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UpdateExamples {

    public static void updateEmployee(int id, int newSalary) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Employee employee = session.get(Employee.class, id);
                if (employee != null) {
                    employee.setSalary(newSalary);
                    // Changes are automatically persisted when the transaction commits
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public static void reattachAndMerge(int id, int newSalary) {
        Employee detachedEmployee;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            detachedEmployee = session.get(Employee.class, id);
        }

        if (detachedEmployee != null) {
            detachedEmployee.setSalary(newSalary);
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.merge(detachedEmployee);
                transaction.commit();
            }
        }
    }

    public static void main(String[] args) {
        updateEmployee(1, 60000);
        reattachAndMerge(2, 65000);
        HibernateUtil.shutdown();
    }
}
