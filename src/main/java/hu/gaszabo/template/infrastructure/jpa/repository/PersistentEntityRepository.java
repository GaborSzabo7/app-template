package hu.gaszabo.template.infrastructure.jpa.repository;

import org.springframework.data.repository.NoRepositoryBean;

import hu.gaszabo.template.infrastructure.jpa.persistententity.PersistentEntity;

@NoRepositoryBean
public interface PersistentEntityRepository<E extends PersistentEntity, ID> extends BaseRepository<E, ID> {

}
