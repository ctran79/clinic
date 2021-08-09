package com.ctran79.clinic.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ctran79
 */

@Controller
@RequestMapping("/print")
public class PrintController {

    protected void setResponseMetadata(HttpServletResponse response, String filename) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
    }

    @GetMapping(path = "/order/{id}")
    public void printOrder(HttpServletResponse response, @PathVariable Long id) {
        try {
            setResponseMetadata(response, "prescription.pdf");
//            JasperPrint jasperPrint = reportService.getTrainingAsReport(mode, trainingIds);
//
//            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (Exception ex) {
            throw new RuntimeException("Exception while generating training print: " + ex.getMessage());
        }
    }
}
