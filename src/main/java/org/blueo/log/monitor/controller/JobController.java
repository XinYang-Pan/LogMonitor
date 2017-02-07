package org.blueo.log.monitor.controller;

import java.util.List;

import org.blueo.log.monitor.service.CoreService;
import org.blueo.log.monitor.vo.LogMonitorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@RestController
public class JobController {
	@Autowired
	private CoreService coreService;

	@RequestMapping("/content")
	public String content() {
		return Joiner.on("<br/>").join(coreService.getContent());
	}

	@RequestMapping("/restart")
	public String restart() {
		coreService.restartAll();
		return "success";
	}
	
	// name is the key
	@RequestMapping("/addOrUpdateMonitor")
	public String addMonitor(LogMonitorVo logMonitorVo) {
		return "success";
	}

	// name is the key
	@RequestMapping("/deleteMonitor")
	public String deleteMonitor(String name) {
		return "success";
	}

	// name is the key
	@RequestMapping("/listMonitor")
	public List<LogMonitorVo> listMonitor() {
		return Lists.newArrayList();
	}

}
