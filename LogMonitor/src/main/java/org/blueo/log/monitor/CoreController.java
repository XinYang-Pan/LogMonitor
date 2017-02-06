package org.blueo.log.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;

@RestController
public class CoreController {
	@Autowired
	private CoreService coreService;

	@RequestMapping("/content")
	@SendTo("/content/123")
	public String getContent() {
		return Joiner.on("<br/>").join(coreService.getContent());
	}

}
