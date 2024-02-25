package com.example.graphqlserver.service;

import com.example.graphqlserver.schema.input.BookInput;
import com.example.graphqlserver.schema.type.Book;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class BookServiceImpl {

	private final List<Book> books = new LinkedList<>(Arrays.asList(
			new Book("book-1", "Effective Java", 416, "author-1"),
			new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
			new Book("book-3", "Down Under", 436, "author-3")
	));

	public List<Book> getAll() {
		return books;
	}

	public Book getById(String id) {
		return books.stream()
				.filter(book -> book.id().equals(id))
				.findFirst()
				.orElse(null);
	}

	public Book newBook(BookInput bookInput) {


		var book = new Book(bookInput.id(), bookInput.name(), bookInput.pageCount(), bookInput.authorId());

		books.add(book);

		return book;
	}


	public BigDecimal fetchBookPrice() {
		return BigDecimal.ONE;
	}
}
