package com.h3c.jobhunter.domain;

import java.io.Serializable;

/**
 * Created by lenovo' on 2018/3/5.
 */
public class JobSalaryAnalysis implements Serializable {

    private String jobName;

    private String jobSite;

    private int jobNumber;

    private Double salaryAverage;

    private Double salaryMedian;

    private Double salarySdDeviation;

    private Double salaryMax;

    private Double salaryMin;

    private int salaryAttrOne;

    private int salaryAttrTwo;

    private int salaryAttrThree;

    private int salaryAttrFour;

    private int salaryAttrFive;

    private Double fCity;

    private Double fSchoolRecord;

    private Double fCompanyNature;

    private Double fCompanyScale;

    private Double fExperience;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobSite() {
        return jobSite;
    }

    public void setJobSite(String jobSite) {
        this.jobSite = jobSite;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Double getSalaryAverage() {
        return salaryAverage;
    }

    public void setSalaryAverage(Double salaryAverage) {
        this.salaryAverage = salaryAverage;
    }

    public Double getSalaryMedian() {
        return salaryMedian;
    }

    public void setSalaryMedian(Double salaryMedian) {
        this.salaryMedian = salaryMedian;
    }

    public Double getSalarySdDeviation() {
        return salarySdDeviation;
    }

    public void setSalarySdDeviation(Double salarySdDeviation) {
        this.salarySdDeviation = salarySdDeviation;
    }

    public Double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Double salaryMax) {
        this.salaryMax = salaryMax;
    }

    public Double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public int getSalaryAttrOne() {
        return salaryAttrOne;
    }

    public void setSalaryAttrOne(int salaryAttrOne) {
        this.salaryAttrOne = salaryAttrOne;
    }

    public int getSalaryAttrTwo() {
        return salaryAttrTwo;
    }

    public void setSalaryAttrTwo(int salaryAttrTwo) {
        this.salaryAttrTwo = salaryAttrTwo;
    }

    public int getSalaryAttrThree() {
        return salaryAttrThree;
    }

    public void setSalaryAttrThree(int salaryAttrThree) {
        this.salaryAttrThree = salaryAttrThree;
    }

    public int getSalaryAttrFour() {
        return salaryAttrFour;
    }

    public void setSalaryAttrFour(int salaryAttrFour) {
        this.salaryAttrFour = salaryAttrFour;
    }

    public int getSalaryAttrFive() {
        return salaryAttrFive;
    }

    public void setSalaryAttrFive(int salaryAttrFive) {
        this.salaryAttrFive = salaryAttrFive;
    }

    public Double getfCity() {
        return fCity;
    }

    public void setfCity(Double fCity) {
        this.fCity = fCity;
    }

    public Double getfSchoolRecord() {
        return fSchoolRecord;
    }

    public void setfSchoolRecord(Double fSchoolRecord) {
        this.fSchoolRecord = fSchoolRecord;
    }

    public Double getfCompanyNature() {
        return fCompanyNature;
    }

    public void setfCompanyNature(Double fCompanyNature) {
        this.fCompanyNature = fCompanyNature;
    }

    public Double getfCompanyScale() {
        return fCompanyScale;
    }

    public void setfCompanyScale(Double fCompanyScale) {
        this.fCompanyScale = fCompanyScale;
    }

    public Double getfExperience() {
        return fExperience;
    }

    public void setfExperience(Double fExperience) {
        this.fExperience = fExperience;
    }
}
