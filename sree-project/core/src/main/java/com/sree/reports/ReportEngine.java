/**
 * 
 */
package com.sree.reports;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sree.service.IBaseService;

/**
 * @author Sree
 *
 */
@Transactional
@Service("reportEngine")
public class ReportEngine implements IReportEngine {
	
	@Autowired
	private IBaseService baseService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IPrintService printService;
	
	public IPrintService getPrintService() {
		return printService;
	}

	public void setPrintService(IPrintService printService) {
		this.printService = printService;
	}

	private Logger logger = Logger.getLogger(ReportEngine.class);
	
	@SuppressWarnings("unchecked")
	public void genarateReport(String reportName, Map params){
		Session session = (Session)entityManager.getDelegate();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		String reportsFolder = "reports/";
		String reportFileExtension = ".jrxml";
		String applicationRealPath = servletContext.getRealPath("/");
		String reportType = (String)request.getParameter("reportType");
		String jrxmlFilePath = applicationRealPath+reportsFolder+reportName+reportFileExtension;
		response.setHeader ("Content-Disposition", "inline; filename="+ reportName + "." + reportType);
		File jrXMLfile = new File(jrxmlFilePath);
		try {
			JasperDesign jasperDesign = JRXmlLoader.load(jrXMLfile);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			params.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, session);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params);
			//JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\xyz.pdf");
			try {
				if(reportType.equals("PDF")){
					/*try {
						printService.print(jasperPrint); 
					} catch (PrinterException e) {
						logger.info("Printer Exception");
						e.printStackTrace();
					}*/
					logger.info("Generating PDF");
					response.setContentType( "application/pdf" );
					JRPdfExporter pdfExporter = new JRPdfExporter();
					pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					pdfExporter.exportReport();
				}
				else{
					response.setContentType( "application/xls" );
					JRXlsExporter xlsExporter = new JRXlsExporter();
					xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					xlsExporter.exportReport();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			//JasperViewer.viewReport(jasperPrint);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	public IBaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
