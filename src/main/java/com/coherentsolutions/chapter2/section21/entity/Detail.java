package com.coherentsolutions.chapter2.section21.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "city")
    private String city;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "empDetail", // Must be equal to the field name in Employee class
            cascade = CascadeType.ALL) // No need to write JoinColumn, we just show that this connection is made in field
            //cascade = {CascadeType.PERSIST, CascadeType.REFRESH}) // Cascade doesn't work on removal
    private Employee employee;

    public Detail() {
    }

    public Detail(String city, String phoneNumber, String email) {
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", com.coherentsolutions.chapter2.section20.entity.Detail.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("city='" + city + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .add("email='" + email + "'")
                .toString();
    }
}
