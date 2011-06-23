/**
 * 
 */
package com.sree.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.reports.IReportEngine;

/**
 * @author srinivasr
 * 
 */
@SuppressWarnings( { "serial" })
@Component("report")
@Scope(value = "request")
public class Report extends BaseBean {

	@Autowired
	private IReportEngine reportEngine;

	@SuppressWarnings( { "unchecked" })
	public void generateReport() {
		Map params = new HashMap();
		String reportName = "newRepo";
		params.put("username", getLoginUserName());
		reportEngine.genarateReport(reportName, params);
	}

	public IReportEngine getReportEngine() {
		return reportEngine;
	}

	public void setReportEngine(IReportEngine reportEngine) {
		this.reportEngine = reportEngine;
	}
}
