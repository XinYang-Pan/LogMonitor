package org.blueo.log.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class CoreService {
	private List<String> list = Lists.newArrayList();

	@PostConstruct
	public void init() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(this::startProcess);
	}

	private void startProcess() {
		try {
			String[] command;
			if (SystemUtils.IS_OS_WINDOWS) {
				// for windows test
				command = new String[] { "C:/Program Files/Java/jdk1.8.0_91/bin/java", "-version" };
			} else {
				// real use
				command = new String[] { "/usr/bin/tail", "-f", "/app/thorin/pricer/log/pricer.2017-02-04.log" };
			}
			Process p = new ProcessBuilder(command).redirectErrorStream(true).start();
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
				list.add(line);
			}
			int exitVal = p.waitFor();
			System.out.println("Exited with error code " + exitVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getContent() {
		return Lists.newArrayList(list);
	}

}
