package es.santander.nuar.migrationproject.utils;

import es.santander.darwin.logging.helper.SecurityLogData;

public class LoggerUtil {

	SecurityLogData sld;

	public LoggerUtil(SecurityLogData sld) {
		this.sld = sld;
	}

	public void modifyStatus(String status) {
		sld.setSecurityLogData(status);
	}

	public String getStatus() {
		return sld.getSecurityLogData();
	}

}
