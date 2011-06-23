package com.sree.reports;

import java.util.Map;

public interface IReportEngine {

	@SuppressWarnings("unchecked")
	public void genarateReport(String reportName, Map params);

}