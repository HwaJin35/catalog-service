package com.polarbookshop.catalogservice.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book (
		@Id			// 이 필드를 엔터티에 대한 기본 키로 식별한다.
		Long id,  

        @NotBlank(message = "The book ISBN must be defined.")
		@Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The ISBN format must be valid.")
        String isbn,

        @NotBlank(message = "The book title must be defined.")
        String title,

        @NotBlank(message = "The book author must be defined.")
        String author,

        @NotNull(message = "The book price must be defined.")
        @Positive(message = "The book price must be greater than zero.")
        Double price,
        
        @CreatedDate	// 엔터티가 생성된 때
        Instant createdDate,
        
        @LastModifiedDate	// 엔터티가 마지막으로 수정된 
        Instant lastModifiedDate,
        
        @Version		// 낙관적 잠금을 위해 사용되는 엔터티 버전 번호
        int version
){
	public static Book of(
			String isbn, String title, String author, Double price
			) {
		return new Book(
				null, isbn, title, author, price, null, null, 0		// id가 Null이고 버전이 0이면 새로운 엔터티로 인식한다.
				);
	}
}
