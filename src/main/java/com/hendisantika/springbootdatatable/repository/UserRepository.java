package com.hendisantika.springbootdatatable.repository;

import com.hendisantika.springbootdatatable.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-datatable
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/04/18
 * Time: 17.51
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM USER", nativeQuery = true)
    List<User> findAllByUsernames(List<String> listOfUsernames);
}