package com.sree.reports;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.HashMap;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("printServiceImpl")
public class PrintServiceImpl implements IPrintService {

	private static final Logger logger = Logger.getLogger(PrintServiceImpl.class);
	
	private Map<String, PrintService> printServices = new HashMap<String, PrintService>();
	
	public Map<String, PrintService> getPrintServices() {
		return printServices;
	}

	public void setPrintServices(Map<String, PrintService> printServices) {
		this.printServices = printServices;
	}
	
	public void loadPrintServers() {
		try {
			printServices.clear();
			PrintService[] tempPrintServices = PrintServiceLookup.lookupPrintServices(null, null);
			for (int i = 0; i < tempPrintServices.length; i++) {
				printServices.put(tempPrintServices[i].getName(), tempPrintServices[i]);
				logger.info("Printer with name "+ tempPrintServices[i].getName() + " is found");
			} 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	
	
	public void print(JasperPrint jasperPrint) {
		PrinterJob job = PrinterJob.getPrinterJob(); 
		/* Create an array of PrintServices */ 
		PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null); 
		PrintService default_printer = PrintServiceLookup.lookupDefaultPrintService(); 

		int selectedService = 0; 
		/* Scan found services to see if anyone suits our needs */ 
		for(int i = 0; i < services.length;i++){ 
			if(services[i].getName().toUpperCase().equals(default_printer.getName().toUpperCase())){ 
				selectedService = i; 
			} 
		} 
		logger.info("Default Printer :: "+default_printer);
		try{
		job.setPrintService(services[selectedService]); 
		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet(); 
		printRequestAttributeSet.add(MediaSizeName.ISO_A4); 
		printRequestAttributeSet.add(new Copies(1)); 
		JRPrintServiceExporter exporter = new JRPrintServiceExporter(); 
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
		/* We set the selected service and pass it as a paramenter */ 
		exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[selectedService]); 
		exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services[selectedService].getAttributes()); 
		exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet); 
		exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE); 
		exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE); 
		exporter.exportReport();
		}catch(PrinterException e){
			logger.info("Exception while printing the report :: "+ e);
		}catch(JRException e){
			logger.info("JR Exception while printing the report :: "+ e);
		}catch(Exception e){
			logger.info("Exception"+e);
		}

	}
	
	/*@SuppressWarnings("unused")
	private static void printPdfFile(String fileName, PrintService printService)
			throws Exception {
		FileInputStream fis = null;
		BufferedInputStream bufferedInputStream = null;
		ByteBuffer bb = null;

		try {
			File f = new File(fileName);
			fis = new FileInputStream(f);
			bufferedInputStream = new BufferedInputStream(fis);
			byte bytes[] = new byte[fis.available()];
			bufferedInputStream.read(bytes);
			bb = ByteBuffer.wrap(bytes);
			PDFFile pdfFile = new PDFFile(bb);
			PDFPrintPage pages = new PDFPrintPage(pdfFile);
			PrinterJob pjob = PrinterJob.getPrinterJob();
			PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
			Paper paper = new Paper();
			paper.setSize(800.0, 1600.0);
			paper.setImageableArea(0, 0, 800.0, 1600.0);
			pf.setPaper(paper);
			pjob.setJobName(f.getName());
			pjob.setPrintService(printService);
			Book book = new Book();
			book.append(pages, pf, pdfFile.getNumPages());
			pjob.setPageable(book);
			//pjob.print();
			
		} catch (Exception e) {
			logger.error("Print Failed", e);
			throw new Exception(e);
		} finally {
			bb.clear();
			try {
				fis.close();
				bufferedInputStream.close();
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				fis = null;
				bufferedInputStream = null;
			}
		}
	}*/

}


