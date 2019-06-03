package tacos.email;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

@Configuration
public class TacoOrderEmailIntegrationConfig {
	
//	@Bean
//	public MessageChannel orderChannel() {
//		return new PublishSubscribeChannel();
//	}
	
//	@ServiceActivator(inputChannel = "orderChannel")

//	@Bean
//	public IntegrationFlow orderFlow() {
//		... 
//		.channel("orderChannel")
//		... 
//		.get();
//	}
	
	
//	@Bean
//	public MessageChannel orderChannel() {
//		return new QueueChannel();
//	}
//	
//	@ServiceActivator(inputChannel = "orderChannel",
//			poller = @Poller(fixedRate = "1000"))
	
//	@Filter(inputChannel = "numberChannel",
//			outputChannel = "evenNumberChannel")
//	public boolean evenNumberFilter(Integer number) {
//		return number % 2 == 0;
//	}
//	@Bean
//	public IntegrationFlow evenNumberFlow(AtomicInteger integerSource) {
//		return IntegrationFlows
//				...
//				.<Integer>filter((p) -> p % 2 == 0)
//				...
//				.get();
//	}
	
}
