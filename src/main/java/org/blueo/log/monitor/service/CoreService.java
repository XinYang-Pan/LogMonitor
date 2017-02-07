package org.blueo.log.monitor.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.blueo.log.monitor.core.LogMonitor;
import org.blueo.log.monitor.core.LogMonitorDaily;
import org.springframework.stereotype.Service;

@Service
public class CoreService {
	private LogMonitor logMonitor = new LogMonitorDaily("test", "/app/thorin/pricer/log/pricer.yyyy-MM-dd.log");

	@PostConstruct
	public void init() {
		logMonitor.start();
	}

	public List<String> getContent() {
		return logMonitor.getContent();
	}

	public void restartAll() {
		logMonitor.restart();
	}

}
