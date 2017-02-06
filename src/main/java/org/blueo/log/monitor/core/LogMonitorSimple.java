package org.blueo.log.monitor.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

public class LogMonitorSimple {
	private final Logger log = LoggerFactory.getLogger(LogMonitorSimple.class);
	//
	private String name = "NoName";
	private String command = "/usr/bin/tail";
	private String argf = "-F";
	private String file;
	private int maxLine = 100;
	// 
	private LinkedList<String> content = Lists.newLinkedList();
	private Process process;

	public LogMonitorSimple() {
	}
	
	public LogMonitorSimple(String name, String file) {
		this.name = name;
		this.file = file;
	}

	public void start() {
		try {
			String[] command;
			if (SystemUtils.IS_OS_WINDOWS) {
				// for windows test
				command = new String[] { "C:/Program Files/Java/jdk1.8.0_91/bin/java", "-version" };
			} else {
				// real use
				Assert.notNull(file);
				command = new String[] { this.command, argf, calculateFileName() };
			}
			process = new ProcessBuilder(command).redirectErrorStream(true).start();
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = input.readLine()) != null) {
				content.add(line);
				this.checkForEviction();
			}
			int exitVal = process.waitFor();
			log.info("Exited with error code " + exitVal);
		} catch (Exception e) {
			log.error("Error", e);
			throw new RuntimeException(e);
		}
	}
	
	public void stop() {
		if (process != null) {
			try {
				process.destroyForcibly().waitFor();
			} catch (InterruptedException e) {
				log.error("Error", e);
				throw new RuntimeException(e);
			}
		}
	}

	public void restart() {
		this.stop();
		this.start();
		log.info("{} is restarted!", this.name);
	}
	
	protected String calculateFileName() {
		return file;
	}
	
	private void checkForEviction() {
		while (content.size() > maxLine) {
			content.removeLast();
		}
	}

	public List<String> getContent() {
		return Lists.newArrayList(content);
	}

	// -----------------------------
	// ----- Get Set ToString HashCode Equals
	// -----------------------------

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogMonitor [command=");
		builder.append(command);
		builder.append(", argf=");
		builder.append(argf);
		builder.append(", file=");
		builder.append(file);
		builder.append(", maxLine=");
		builder.append(maxLine);
		builder.append("]");
		return builder.toString();
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getArgf() {
		return argf;
	}

	public void setArgf(String argf) {
		this.argf = argf;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getMaxLine() {
		return maxLine;
	}

	public void setMaxLine(int maxLine) {
		this.maxLine = maxLine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
