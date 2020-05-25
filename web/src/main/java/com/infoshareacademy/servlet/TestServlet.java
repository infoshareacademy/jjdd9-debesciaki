package com.infoshareacademy.servlet;

import com.infoshareacademy.entity.Role;
import com.infoshareacademy.entity.User;
import com.infoshareacademy.service.RoleService;
import com.infoshareacademy.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class TestServlet extends HttpServlet {
    @Inject
    UserService userService;

    @Inject
    RoleService roleService;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        if (req.getParameter("init") != "") {
            initRoles();
        }
        Role role = roleService.findRoleById(Long.parseLong(req.getParameter("roleId"))).get();
        user.setMail(req.getParameter("mail"));
        user.setRole(role);
        userService.saveUser(user);
    }

    private void initRoles() {
        Role role1 = new Role();
        Role role2 = new Role();
        Role role3 = new Role();
        role1.setName("SuperAdmin");
        role1.setId(1L);
        role2.setName("Admin");
        role2.setId(2L);
        role3.setName("User");
        role3.setId(3L);
        this.roleService.saveRole(role1);
        this.roleService.saveRole(role2);
        this.roleService.saveRole(role3);
    }
}