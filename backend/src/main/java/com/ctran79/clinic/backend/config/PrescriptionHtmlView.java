package com.ctran79.clinic.backend.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("forexView")
public class PrescriptionHtmlView /*extends AbstractView*/ {
//    private JasperReport currencyRatesReport;
//
//    @Override
//    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
//                                           HttpServletResponse response) throws Exception {
//        response.setContentType("text/html");
//        List<CurrencyRate> rates = (List<CurrencyRate>) model.get("todayCurrencyRates");
//        //data source
//        JRDataSource dataSource = getDataSource(rates);
//        //compile jrxml template and get report
//        JasperReport report = getReport();
//        //fill the report with data source objects
//        JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, dataSource);
//        //export to html
//        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
//        exporter.exportReport();
//    }{
}
