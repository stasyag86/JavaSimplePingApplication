package com.testDolcer.myTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.testDolcer.interfaces.ISendReport;
import com.testDolcer.pojo.Report;
import com.testDolcer.threads.PingICMPThread;
import com.testDolcer.threads.PingTCPThread;
import com.testDolcer.threads.TraceRouteThread;

public class App 
{	
	
	private final static String JASMIN_HOST = "jasmin.com";
	private final static String ORANUM_HOST = "oranum.com";
	private final static String GIVEN_URL="";
	
    public static void main( String[] args )
    {
    	postReportResult(JASMIN_HOST);
    	postReportResult(ORANUM_HOST);
    }
    
    private static void postReportResult(String hostName){
    	PingICMPThread pingThread = new PingICMPThread(hostName, 5);
    	pingThread.start();
    	
    	PingTCPThread pingTCPThread = new PingTCPThread("https://" + hostName, 5);
    	pingTCPThread.start();
    	
    	TraceRouteThread traceRouteThread = new TraceRouteThread(hostName, 5);
    	traceRouteThread.start();
    	
    	try {
    		pingThread.join();
    		pingTCPThread.join();
    		traceRouteThread.join();
    		
    		ConcurrentHashMap<String, Report> map = StoredResult.getMap();
        	Report report = map.get(hostName);
        	
        	ISendReport sendReport = new SendReportImp();
        	try {
				sendReport.sendReportToURL(GIVEN_URL, report);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
    	
    	
    }
    
   
}
