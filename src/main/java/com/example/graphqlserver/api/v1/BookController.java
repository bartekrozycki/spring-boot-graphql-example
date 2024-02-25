package com.example.graphqlserver.api.v1;

import com.example.graphqlserver.schema.input.BookInput;
import com.example.graphqlserver.service.AuthorServiceImpl;
import com.example.graphqlserver.service.BookServiceImpl;
import com.example.graphqlserver.schema.type.Author;
import com.example.graphqlserver.schema.type.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class BookController {

	private final BookServiceImpl bookService;
	private final AuthorServiceImpl authorService;

	public BookController(
			@Autowired BookServiceImpl bookService,
			@Autowired AuthorServiceImpl authorService
	) {
		this.bookService = bookService;
		this.authorService = authorService;
	}

	@QueryMapping
	public List<Book> allBooks() {
		return bookService.getAll();
	}



	@SchemaMapping
	public Author author(Book book) {
		return authorService.getById(book.authorId());
	}



	@QueryMapping
	public Book bookById(@Argument String id) {
		return bookService.getById(id);
	}


	@MutationMapping
	public Book newBook(@Argument BookInput book) {
		return bookService.newBook(book);
	}

	@SchemaMapping
	public BigDecimal price(Book book) {
		if (book.id().equalsIgnoreCase("book-1")) {
			return new BigDecimal("12.3");
		}
		return bookService.fetchBookPrice();
	}
}
