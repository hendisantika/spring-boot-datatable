package com.hendisantika.springbootdatatable.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-datatable
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/04/18
 * Time: 17.47
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String salary;


    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the salary
     */
    public String getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }
//	/**
//	 * @return the totalRecords
//	 */
//	public Integer getTotalRecords() {
//		return totalRecords;
//	}
//	/**
//	 * @param totalRecords the totalRecords to set
//	 */
//	public void setTotalRecords(Integer totalRecords) {
//		this.totalRecords = totalRecords;
//	}


    public User() {
    }


    public User(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}