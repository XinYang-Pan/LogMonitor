package org.blueo.log.monitor.vo;

import org.blueo.log.monitor.core.MonitorType;

public class LogMonitorVo {
	private String name;
	private MonitorType monitorType;
	private String filePath;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogMonitorVo [name=");
		builder.append(name);
		builder.append(", monitorType=");
		builder.append(monitorType);
		builder.append(", filePath=");
		builder.append(filePath);
		builder.append("]");
		return builder.toString();
	}

	public MonitorType getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(MonitorType monitorType) {
		this.monitorType = monitorType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
