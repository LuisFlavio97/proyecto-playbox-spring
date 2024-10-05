package com.cibertec.models.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

@Service
public class ReporteServiceImpl implements IReporteService {

    @Autowired
    private DataSource dataSource;

    @Override
    public byte[] generaReporte(String reportName, Map<String, Object> parameters) throws Exception {
        // Obtener Fecha y Hora actual para el reporte
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);

        // Pasando parámetros al jasper
        parameters.put("IMAGE_DIR", this.getClass().getResource("/img/").toString());
        parameters.put("REPOR_DIR", "classpath:/reportes/");
        parameters.put("FECHA_REPORTE", "Fecha de Reporte: Hoy, " + formattedDateTime);

        try (InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/" + reportName + ".jrxml");
                Connection connection = dataSource.getConnection()) {
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }

    @Override
    public String generaReportetHtml(String reportName, Map<String, Object> parameters) throws Exception {
        // Obtener Fecha y Hora actual para el reporte
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);

        // Pasando parámetros al jasper
        parameters.put("IMAGE_DIR", this.getClass().getResource("/img/").toString());
        parameters.put("REPOR_DIR", "classpath:/reportes/");
        parameters.put("FECHA_REPORTE", "Fecha de Reporte: Hoy, " + formattedDateTime);

        try (InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/" + reportName + ".jrxml");
                Connection connection = dataSource.getConnection()) {
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            HtmlExporter exporter = new HtmlExporter();
            ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(htmlStream));

            SimpleHtmlReportConfiguration reportConfig = new SimpleHtmlReportConfiguration();
            SimpleHtmlExporterConfiguration exportConfig = new SimpleHtmlExporterConfiguration();

            exporter.setConfiguration(reportConfig);
            exporter.setConfiguration(exportConfig);

            exporter.exportReport();

            return htmlStream.toString("UTF-8");
        }
    }
}

