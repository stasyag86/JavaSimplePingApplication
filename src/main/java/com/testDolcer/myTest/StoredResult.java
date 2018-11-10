package com.testDolcer.myTest;

import java.util.concurrent.ConcurrentHashMap;

import com.testDolcer.pojo.Report;

public class StoredResult {
	
	private static ConcurrentHashMap<String, Report> hostReportMap;
	
	public static ConcurrentHashMap<String, Report> getMap(){
		if (hostReportMap == null){
			hostReportMap = new ConcurrentHashMap<String, Report>();
		}
		return hostReportMap;
	}
	
	public static void storePingResult(String host , String lastPingResult){
		ConcurrentHashMap<String, Report> tempMap = getMap();
		if (tempMap.containsKey(host)){
			Report reportForHost = tempMap.get(host);
			reportForHost.setHost(host);
			reportForHost.setIcmp_ping(lastPingResult);
			tempMap.put(host, reportForHost);
		}else{
			Report newReport = new Report(host);
			newReport.setIcmp_ping(lastPingResult);
			tempMap.put(host, newReport);
		}
	}
	
	public static void storePingTCPResult(String host , String lastPingTCPResult){
		ConcurrentHashMap<String, Report> tempMap = getMap();
		if (tempMap.containsKey(host)){
			Report reportForHost = tempMap.get(host);
			reportForHost.setHost(host);
			reportForHost.setTcp_ping(lastPingTCPResult);
			tempMap.put(host, reportForHost);
		}else{
			Report newReport = new Report(host);
			newReport.setTcp_ping(lastPingTCPResult);
			tempMap.put(host, newReport);
		}
	}
	
	public static void storeTraceRouteResult(String host , String lastTraceRoutResult){
		ConcurrentHashMap<String, Report> tempMap = getMap();
		if (tempMap.containsKey(host)){
			Report reportForHost = tempMap.get(host);
			reportForHost.setHost(host);
			reportForHost.setTrace(lastTraceRoutResult);
			tempMap.put(host, reportForHost);
		}else{
			Report newReport = new Report(host);
			newReport.setTrace(lastTraceRoutResult);
			tempMap.put(host, newReport);
		}
	}

}
