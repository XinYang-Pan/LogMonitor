package org.blueo.log.monitor;

import java.util.List;

import javax.annotation.PostConstruct;

import org.blueo.log.monitor.core.LogMonitorSimple;
import org.blueo.log.monitor.core.LogMonitorDate;
import org.springframework.stereotype.Service;

@Service
public class CoreService {
	private LogMonitorSimple logMonitorSimple = new LogMonitorDate("test", "/app/thorin/pricer/log/pricer.yyyy-MM-dd.log");

	@PostConstruct
	public void init() {
		Thread t = new Thread(logMonitorSimple::start);
		t.start();
	}

	public List<String> getContent() {
		return logMonitorSimple.getContent();
	}

}
