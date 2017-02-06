package org.blueo.log.monitor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogMonitorApplicationTests {

	@Test
	public void contextLoads() {

		String pattern = "yyyy-MM-dd";
		String format = LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
		String string = "/app/thorin/pricer/log/pricer.yyyy-MM-dd.log".replaceAll(pattern, format);
		System.out.println(string);
	}

}
