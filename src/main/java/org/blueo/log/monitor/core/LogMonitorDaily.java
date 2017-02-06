package org.blueo.log.monitor.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class LogMonitorDaily extends LogMonitorSimple {
	private ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
	private String pattern = "yyyy-MM-dd";
	
	{
		scheduler.setDaemon(true);
		scheduler.initialize();
		Trigger trigger = new CronTrigger("0 0 0 * * ?");
		scheduler.schedule(this::restart, trigger);
	}
	
	public LogMonitorDaily() {
		super();
	}

	public LogMonitorDaily(String name, String file) {
		super(name, file);
	}
	
	@Override
	protected String calculateFileName() {
		String format = LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
		return this.getFile().replaceAll(pattern, format);
	}
	
}
