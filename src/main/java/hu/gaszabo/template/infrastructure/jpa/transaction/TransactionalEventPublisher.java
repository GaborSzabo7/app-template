package hu.gaszabo.template.infrastructure.jpa.transaction;

import static java.util.Objects.requireNonNull;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;
import static org.springframework.util.Assert.isTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import hu.gaszabo.template.model.Event;
import hu.gaszabo.template.model.EventRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Scope(value = SCOPE_PROTOTYPE)
@Slf4j
public class TransactionalEventPublisher implements EventPublisher {

	private final ApplicationEventPublisher publisher;

	private final EventRepository repository;

	@Autowired
	public TransactionalEventPublisher( //
			@Qualifier("applicationEventPublisher") final ApplicationEventPublisher publisher, //
			@Qualifier("eventRepository") final EventRepository repository) {
		this.publisher = requireNonNull(publisher, "publisher can't be null");
		this.repository = requireNonNull(repository, "repository can't be null");
	}

	@Override
	public void publish(final ApplicationEvent event) {
		log.debug("Attemting to publish event: {}", event);
		isTrue(TransactionSynchronizationManager.isSynchronizationActive(), "Event can't publish outside transaction");

		Optional<EventPublisherSynchronization> activeSynchronization = findActiveSynchronization();
		if (activeSynchronization.isPresent()) {
			log.debug("Active EventPublisherSynchronization can be found");
			EventPublisherSynchronization synchronization = activeSynchronization.get();
			synchronization.registerEvent(event);
		} else {
			log.debug("Register EventPublisherSynchronization");
			TransactionSynchronizationManager.registerSynchronization(new EventPublisherSynchronization(event));
		}
	}

	private Optional<EventPublisherSynchronization> findActiveSynchronization() {
		return TransactionSynchronizationManager.getSynchronizations() //
				.stream() //
				.filter(s -> s instanceof EventPublisherSynchronization) //
				.map(s -> EventPublisherSynchronization.class.cast(s)) //
				.findAny();
	}

	private final class EventPublisherSynchronization implements TransactionSynchronization {

		private final List<ApplicationEvent> events = new ArrayList<>();

		public EventPublisherSynchronization(final ApplicationEvent event) {
			events.add(event);
		}

		public void registerEvent(final ApplicationEvent event) {
			events.add(event);
		}

		@Override
		public void beforeCommit(final boolean readOnly) {
			events.forEach(event -> {
				log.debug("Save event: {}", event);
				repository.save(Event.of(event.getClass().getSimpleName(), event.toString()));
			});
		}

		@Override
		public void afterCommit() {
			events.forEach(event -> {
				log.debug("Publishing event: {}", event);
				publisher.publishEvent(event);
			});
		}

	}

}
