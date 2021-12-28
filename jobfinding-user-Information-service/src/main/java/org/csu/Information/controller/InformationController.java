package org.csu.Information.controller;


import org.csu.Information.domain.AdminResume;
import org.csu.Information.service.UserInformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class InformationController {
    @Resource
    private UserInformService UserInformService;

    @RequestMapping("/Information/setAdminState")
    public String setAdminState(AdminResume adminResume, HttpServletRequest request){
            try{
                UserInformService.updateAdminState(adminResume);
                return "success";
            }catch (Exception e)
            {
                return "failed";
            }
    }
    @RequestMapping("/Information/setAdminInfo")
    public String setAdminInfo(AdminResume adminResume){
        //AdminResume adminResume= new AdminResume();
        UserInformService.insertAdminInfo(adminResume);
        return "success";
    }



    

}
