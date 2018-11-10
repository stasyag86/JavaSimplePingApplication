package com.testDolcer.interfaces;

import java.io.IOException;

import com.testDolcer.pojo.Report;

public interface ISendReport {
	
	public void sendReportToURL (String url , Report report) throws IOException;

}
