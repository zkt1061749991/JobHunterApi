package com.h3c.jobhunter.service.impl;

import com.h3c.jobhunter.dao.IJobDAO;
import com.h3c.jobhunter.domain.CJob;
import com.h3c.jobhunter.domain.Job;
import com.h3c.jobhunter.domain.SalaryFactor;
import com.h3c.jobhunter.service.IRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo' on 2018/3/11.
 */
@Service("IRecommendService")
public class RecommendServiceImpl implements IRecommendService {

    @Autowired
    private IJobDAO jobDAO;

    @Override
    public List<CJob> getCJobsByJobName(String jobName, String jobSite) {
        return jobDAO.findCJobsByJobName(jobName, jobSite);
    }

    @Override
    public List<CJob> getCJobsByName(String jobName) {
        return jobDAO.findCJobsByName(jobName);
    }

    @Override
    public void setSalaryFactor(SalaryFactor salaryFactor) {
        jobDAO.upsertJobSalaryFactor(salaryFactor);
    }
}
