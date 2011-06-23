package com.sree.reports;

import java.awt.print.PrinterException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;


public interface IPrintService  {

	public void loadPrintServers();
	
	public void print(JasperPrint jasperPrint) throws PrinterException, JRException; 

}