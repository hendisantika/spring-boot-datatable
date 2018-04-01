package com.hendisantika.springbootdatatable.controller;

import com.google.gson.Gson;
import com.hendisantika.springbootdatatable.domain.User;
import com.hendisantika.springbootdatatable.domain.UserModel;
import com.hendisantika.springbootdatatable.pagination.DataTableRequest;
import com.hendisantika.springbootdatatable.pagination.DataTableResults;
import com.hendisantika.springbootdatatable.pagination.PaginationCriteria;
import com.hendisantika.springbootdatatable.repository.GenericRepo;
import com.hendisantika.springbootdatatable.repository.UserRepository;
import com.hendisantika.springbootdatatable.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-datatable
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/04/18
 * Time: 17.59
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BaseController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private GenericRepo genericRepo;

    /**
     * The entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "name", defaultValue = "World") String name) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("userModel", new UserModel());
        List<UserModel> userList = genericRepo.getUserModel();
        mv.addObject("userlist", userList);
        return mv;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        return "users";
    }

    @RequestMapping(value = "/users/paginated", method = RequestMethod.GET)
    @ResponseBody
    public String listUsersPaginated(HttpServletRequest request, HttpServletResponse response, Model model) {

        DataTableRequest<User> dataTableInRQ = new DataTableRequest<User>(request);
        PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();

        String baseQuery = "SELECT id as id, name as name, salary as salary, (SELECT COUNT(1) FROM USER) AS total_records  FROM USER";
        String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);

        System.out.println(paginatedQuery);

        Query query = entityManager.createNativeQuery(paginatedQuery, UserModel.class);

        @SuppressWarnings("unchecked")
        List<UserModel> userList = query.getResultList();

        DataTableResults<UserModel> dataTableResult = new DataTableResults<UserModel>();
        dataTableResult.setDraw(dataTableInRQ.getDraw());
        dataTableResult.setListOfDataObjects(userList);
        if (!AppUtil.isObjectEmpty(userList)) {
            dataTableResult.setRecordsTotal(userList.get(0).getTotalRecords()
                    .toString());
            if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
                dataTableResult.setRecordsFiltered(userList.get(0).getTotalRecords()
                        .toString());
            } else {
                dataTableResult.setRecordsFiltered(Integer.toString(userList.size()));
            }
        }
        return new Gson().toJson(dataTableResult);
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute UserModel userModel, Model model) {
        if (null != userModel) {

            if (!AppUtil.isObjectEmpty(userModel.getId()) &&
                    !AppUtil.isObjectEmpty(userModel.getName()) &&
                    !AppUtil.isObjectEmpty(userModel.getSalary())) {

                User u = new User();
                u.setId(userModel.getId());
                u.setName(userModel.getName());
                u.setSalary(userModel.getSalary());
                userRepo.save(u);
            }
        }
        return "redirect:/";
    }

}