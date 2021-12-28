package com.h3c.jobhunter.domain;

import java.io.Serializable;

/**
 * Created by lenovo' on 2018/3/21.
 */
public class JobRecommend implements Serializable {

    private String email;

    private String jobName;

    private String companyName;

    private String jobSite;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobSite() {
        return jobSite;
    }

    public void setJobSite(String jobSite) {
        this.jobSite = jobSite;
    }
}
