package com.testDolcer.pojo;

public class Report {
	
	private String host;
	private String icmp_ping;
	private String tcp_ping;
	private String trace;
	
	public Report(String host) {
		super();
		this.host = host;
	}
	
	public Report(String host, String icmp_ping, String tcp_ping, String trace) {
		super();
		this.host = host;
		this.icmp_ping = icmp_ping;
		this.tcp_ping = tcp_ping;
		this.trace = trace;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIcmp_ping() {
		return icmp_ping;
	}

	public void setIcmp_ping(String icmp_ping) {
		this.icmp_ping = icmp_ping;
	}

	public String getTcp_ping() {
		return tcp_ping;
	}

	public void setTcp_ping(String tcp_ping) {
		this.tcp_ping = tcp_ping;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

}
