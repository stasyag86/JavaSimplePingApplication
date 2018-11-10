package com.testDolcer.threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.testDolcer.myTest.StoredResult;

public class TraceRouteThread extends Thread{

	private String hostName;
	private int delay;
	
	public TraceRouteThread(String hostName, int delay) {
		this.hostName = hostName;
		this.delay = delay;
	}

	

	@Override
	public void run() {
		String lastTracertPResult = "";
	    	try {
	    		String command = ("tracert " + this.hostName);
				Process p = Runtime.getRuntime().exec(command);
				BufferedReader inputStream = new BufferedReader(
						new InputStreamReader(p.getInputStream()));

				String s = "";
				// reading output stream of the command
				while ((s = inputStream.readLine()) != null) {
					if (!s.contains("Trace complete") && !s.contains("Request timed out") && !s.equals("")){
						//System.out.println(s);
						lastTracertPResult = s + " " + getCurrentTime();
						System.out.println("lastTracertPResult : " + lastTracertPResult);
					}
				}
				
				//System.out.println("last ping is: "  + lastPingICMPResult);
				Thread.sleep(this.delay * 1000);

			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				StoredResult.storeTraceRouteResult(hostName, lastTracertPResult);
			}
        
	}
	
	private String getCurrentTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
}
