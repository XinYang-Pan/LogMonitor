package org.blueo.log.monitor.vo;

import java.util.List;

import org.blueo.log.monitor.core.LogMonitor;
import org.blueo.log.monitor.core.LogMonitorDaily;

import com.google.common.collect.Lists;

/**
details sample:
name  command arg
server=/app/thorin/server/log/server.yyyy-MM-dd.log |grep 'ERROR'
pricer=/app/thorin/pricer/log/pricer.yyyy-MM-dd.log |grep 'ERROR'
 */
public class LogJob {
	private String name;
	private String details;
	private List<LogMonitor> logMonitors = Lists.newArrayList();

	public LogJob() {
	}

	public LogJob(String name, String details) {
		this.name = name;
		this.details = details;
	}

	public void start() {
		if (!logMonitors.isEmpty()) {
			for (LogMonitor logMonitor : logMonitors) {
				logMonitor.stop();
				logMonitors.clear();
			}
		}
		String lines[] = details.split("\\r?\\n");
		for (String line : lines) {
			String[] split = line.split("=", 2);
			LogMonitor logMonitor = new LogMonitorDaily(split[0], split[1]);
			logMonitor.start();
			logMonitors.add(logMonitor);
		}
	}

	public List<String> getContent() {
		List<String> content = Lists.newArrayList();
		for (LogMonitor logMonitor : logMonitors) {
			content.addAll(logMonitor.getContent());
		}
		return content;
	}

	// -----------------------------
	// ----- Get Set ToString HashCode Equals
	// -----------------------------

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogJob [name=");
		builder.append(name);
		builder.append(", details=");
		builder.append(details);
		builder.append(", logMonitors=");
		builder.append(logMonitors);
		builder.append("]");
		return builder.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<LogMonitor> getLogMonitors() {
		return logMonitors;
	}

	public void setLogMonitors(List<LogMonitor> logMonitors) {
		this.logMonitors = logMonitors;
	}

}
