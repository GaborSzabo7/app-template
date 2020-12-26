package hu.gaszabo.template.model;

import org.springframework.stereotype.Repository;

import hu.gaszabo.template.infrastructure.jpa.repository.PersistentEntityRepository;

@Repository
public interface EventRepository extends PersistentEntityRepository<Event, Long> {

}
