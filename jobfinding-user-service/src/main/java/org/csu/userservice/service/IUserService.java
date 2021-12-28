package org.csu.userservice.service;



import org.csu.userservice.domain.UserCompany;
import org.csu.userservice.domain.UserPerson;

/**
 * Created by lenovo' on 2018/3/2.
 */
public interface IUserService {
    public UserPerson findUserPersonByEmailAndPwd(String email, String password);
    public void insertUserPerson(UserPerson userPerson);
    public void insertUserCompany(UserCompany userCompany);

}
