package org.csu.userservice.service.impl;


import org.csu.userservice.dao.UserDAO;
import org.csu.userservice.domain.UserCompany;
import org.csu.userservice.domain.UserPerson;
import org.csu.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("IUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDAO userdao;

    @Override
    public UserPerson findUserPersonByEmailAndPwd(String email, String password) {
        return userdao.findUserPersonByEmailAndPwd(email, password);
    }
    @Override
    public void insertUserPerson(UserPerson userPerson) {
        userdao.insertUserPerson(userPerson);
    }

    @Override
    public void insertUserCompany(UserCompany userCompany) {
        userdao.insertUserCompany(userCompany);
    }

}
