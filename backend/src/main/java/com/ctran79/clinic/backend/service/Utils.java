package com.ctran79.clinic.backend.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Utils {
  public static JasperReport getCompiledReport(String reportName)
      throws JRException, FileNotFoundException {
    File reportFile = ResourceUtils.getFile("classpath:reports/" + reportName + ".jrxml");
    return JasperCompileManager.compileReport(new FileInputStream(reportFile));
  }
}
