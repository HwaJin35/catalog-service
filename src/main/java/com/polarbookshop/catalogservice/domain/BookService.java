package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

	public Book editBookDetails(String isbn, Book book) {
		return bookRepository.findByIsbn(isbn)
				.map(existingBook -> {
					// 기존 책의 식별자(id)와, 기존 책 버전(version) 사용 시 업데이트가 성공하면 자동으로 증가한다.
					var bookToUpdate = new Book(
							existingBook.id(),
							existingBook.isbn(),
							book.title(),
							book.author(),
							book.price(),
							book.publisher(),
							existingBook.createdDate(),	// 기존 책 레코드의 생성 날짜 사
							existingBook.lastModifiedDate(),	// 기존 책 레코드의 마지막 수정 날짜 사용, 업데이트가 성공하면 스프링 데이터에 의해 자동으로 변경된다.
							existingBook.version());
					return bookRepository.save(bookToUpdate);
				})
				.orElseGet(() -> addBookToCatalog(book));
	}

}
