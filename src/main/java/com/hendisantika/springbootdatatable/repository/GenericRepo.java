package com.hendisantika.springbootdatatable.repository;

import com.hendisantika.springbootdatatable.domain.UserModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-datatable
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/04/18
 * Time: 17.49
 * To change this template use File | Settings | File Templates.
 */
public interface GenericRepo {

    List<UserModel> getUserModel();

}