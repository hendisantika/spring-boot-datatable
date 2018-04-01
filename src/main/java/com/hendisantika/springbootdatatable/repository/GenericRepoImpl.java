package com.hendisantika.springbootdatatable.repository;

import com.hendisantika.springbootdatatable.domain.UserModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-datatable
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/04/18
 * Time: 17.50
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class GenericRepoImpl implements GenericRepo {

    @PersistenceContext
    private EntityManager entityManager;

    /* (non-Javadoc)
     * @see com.opencodez.repo.GenericRepo#getUserModel()
     */
    @Override
    public List<UserModel> getUserModel() {

        String qry = "SELECT id as id, name as name, salary as salary, 1 as total_records FROM USER";
        Query query = entityManager.createNativeQuery(qry,
                UserModel.class);

        @SuppressWarnings("unchecked")
        List<UserModel> daoDtolist = query.getResultList();

        return daoDtolist;
    }

}