package hu.gaszabo.template.infrastructure.concurrent;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@AutoConfigureAfter(TaskExecutionAutoConfiguration.class)
public class TaskExecutionConfiguration {

	@Bean
	public TaskExecutor eventTaskExecutor() {
		ThreadPoolTaskExecutor eventTaskExecutor = new ThreadPoolTaskExecutor();
		eventTaskExecutor.setCorePoolSize(8);
		eventTaskExecutor.setMaxPoolSize(64);
		eventTaskExecutor.setQueueCapacity(64);
		eventTaskExecutor.setThreadNamePrefix("event-");
		eventTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		eventTaskExecutor.setRejectedExecutionHandler(null);
		return eventTaskExecutor;
	}

}
