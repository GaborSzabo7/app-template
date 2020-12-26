package hu.gaszabo.template.infrastructure.jpa.repository;

import org.springframework.data.repository.NoRepositoryBean;

import hu.gaszabo.template.infrastructure.jpa.persistententity.AuditingPersistentEntity;

@NoRepositoryBean
public interface AuditingPersistentEntityRepository<E extends AuditingPersistentEntity, ID> extends BaseRepository<E, ID> {

}
