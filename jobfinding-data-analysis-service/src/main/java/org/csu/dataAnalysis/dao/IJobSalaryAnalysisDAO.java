package com.h3c.jobhunter.dao;

import com.h3c.jobhunter.domain.JobSalaryAnalysis;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by lenovo' on 2018/3/5.
 */
public interface IJobSalaryAnalysisDAO {

    List<JobSalaryAnalysis> selectSalaryListByJobName(String jobName);

    List<JobSalaryAnalysis> selectSalaryAnalysisListByJobSite(String jobSite);

    JobSalaryAnalysis selectSalaryByJobNameAndSite(String jobName, String jobSite);

    List<JobSalaryAnalysis> selectMainSalaryListByJobName(String jobName);
}
