package org.csu.Information.service;


import org.csu.Information.domain.AdminResume;


public interface UserInformService {
    public void updateAdminState(AdminResume adminResume);
    public void insertAdminInfo(AdminResume adminResume);
}
