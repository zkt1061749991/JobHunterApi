package org.csu.Information.dao;


import org.csu.Information.domain.AdminResume;

public interface InformationDAO {
    public void updateAdminState(AdminResume adminResume);
    void insertAdminInfo(AdminResume adminResume);


}
