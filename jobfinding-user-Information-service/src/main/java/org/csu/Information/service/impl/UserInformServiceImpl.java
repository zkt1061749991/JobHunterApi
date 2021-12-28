package org.csu.Information.service.impl;


import org.csu.Information.dao.InformationDAO;
import org.csu.Information.domain.AdminResume;
import org.csu.Information.service.UserInformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


@Service("UserInformService")
public class UserInformServiceImpl implements UserInformService {


    @Autowired
    private InformationDAO informationDAO;

    @Override
    public void updateAdminState(AdminResume adminResume) {
        informationDAO.updateAdminState(adminResume);
    }

    @Override
    public void insertAdminInfo(AdminResume adminResume) {
        informationDAO.insertAdminInfo(adminResume);
    }

}
