package tacos.email;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ConfigurationProperties(prefix = "tacocloud.api")
@Component
public class ApiProperties {
	
	private String url;

	@PostConstruct
	public void postConstruct() {
		System.out.println(String.format("===> url = %s", url));
	}
}
