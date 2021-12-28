package org.csu.userservice.dao;

import org.csu.userservice.domain.UserCompany;
import org.csu.userservice.domain.UserPerson;

public interface UserDAO {

    UserPerson findUserPersonByEmailAndPwd(String email, String password);
    void insertUserPerson(UserPerson userPerson);
    void insertUserCompany(UserCompany userCompany);
}
