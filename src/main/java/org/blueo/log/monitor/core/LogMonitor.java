package org.blueo.log.monitor.core;

import java.util.List;

public interface LogMonitor {

	void start();

	void stop();

	void restart();

	List<String> getContent();

}