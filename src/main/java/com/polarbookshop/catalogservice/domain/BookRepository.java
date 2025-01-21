package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
// 엔터티(Book)와 기본 키 유형(Long)을 지정하면서 CRUD 연산을 제공하는 리포지터리를 확장한다.
public interface BookRepository extends CrudRepository<Book, Long> { 

	Optional<Book> findByIsbn(String isbn);		// 실행 시간에 스프링 데이터에 의해 구현이 제공되는 메서드

	boolean existsByIsbn(String isbn);

//	Book save(Book book);
//
//	void deleteByIsbn(String isbn);
	
	@Modifying
	@Transactional		// 메서드가 트랜잭션으로 실행됨을 나타낸다.
	@Query("delete from Book where isbn = :isbn")	// 스프링 데이터가 메서드 구현에 사용할 쿼리를 사용한다.
	void deleteByIsbn(String isbn);

}
