package com.h3c.jobhunter.service;

import com.h3c.jobhunter.domain.CJob;
import com.h3c.jobhunter.domain.Job;
import com.h3c.jobhunter.domain.SalaryFactor;

import java.util.List;

/**
 * Created by lenovo' on 2018/3/11.
 */
public interface IRecommendService {

    public List<CJob> getCJobsByJobName(String jobName, String jobSite);

    public List<CJob> getCJobsByName(String jobName);

    public void setSalaryFactor(SalaryFactor salaryFactor);
}
