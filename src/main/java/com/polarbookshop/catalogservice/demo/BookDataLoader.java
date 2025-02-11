package com.polarbookshop.catalogservice.demo;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;

@Component
@Profile("testdata")
public class BookDataLoader {

	private final BookRepository bookRepository;

	public BookDataLoader(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void loadBookTestData() {
		bookRepository.deleteAll();  // 빈 데이터베이스로 시작하기 위해 기존 책이 있다면 모두 삭제한다.
		// 프레임워트가 내부적으로 식별자와 버전에 대한 할당 값을 처리한다.
		var book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90, "Publisher1");
		var book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90, "Publisher2");
		
//		bookRepository.save(book1);
//		bookRepository.save(book2);
		
		bookRepository.saveAll(List.of(book1, book2));	// 여러 객체를 한꺼번에 저장한다.
	}
}
