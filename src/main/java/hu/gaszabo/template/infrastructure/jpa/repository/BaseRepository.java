package hu.gaszabo.template.infrastructure.jpa.repository;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.querydsl.core.types.Predicate;

@NoRepositoryBean
interface BaseRepository<A, ID> extends PagingAndSortingRepository<A, ID>, QuerydslPredicateExecutor<A> {

	@Override
	List<A> findAll(Predicate predicate);

}
