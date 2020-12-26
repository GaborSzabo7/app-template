package hu.gaszabo.template.infrastructure.jpa.transaction;

public interface EventPublisher {

	void publish(ApplicationEvent event);

}
