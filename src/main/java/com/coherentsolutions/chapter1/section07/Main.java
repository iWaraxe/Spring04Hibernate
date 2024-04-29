package com.coherentsolutions.chapter1.section07;

import com.coherentsolutions.chapter1.entity.Employee;

public class Main {
    public static void main(String[] args) {
        // Example of using get()
        Employee employee = DataAccess.getEmployee(7);
        if (employee != null) {
            System.out.println("Employee Retrieved using get(): " + employee.getName());
        } else {
            System.out.println("No employee found with this ID using get()");
        }

        // Example of using load()
        try {
            Employee loadedEmployee = DataAccess.loadEmployee(1);
            System.out.println("Employee Retrieved using load(): " + loadedEmployee.getName());
        } catch (Exception e) {
            System.out.println("No employee found with this ID using load(), or error loading employee");
        }

        // Example of using HQL
        System.out.println("All employees retrieved using HQL:");
        for (Employee emp : DataAccess.findAllEmployees()) {
            System.out.println("Employee: " + emp.getName());
        }
    }
}
