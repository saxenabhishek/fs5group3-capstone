package com.fidelity.models;

public abstract class Report {
    private String reportType;
    public Report(String reportType)
    {
        this.reportType = reportType;
    }
    public abstract void generateReport();
}