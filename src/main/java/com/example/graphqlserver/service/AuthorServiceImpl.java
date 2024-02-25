package com.example.graphqlserver.service;

import com.example.graphqlserver.schema.type.Author;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthorServiceImpl {
	private final List<Author> authors = new LinkedList<>(Arrays.asList(
			new Author("author-1", "Joshua", "Bloch"),
			new Author("author-2", "Douglas", "Adams"),
			new Author("author-3", "Bill", "Bryson")
	));

	public Author getById(String id) {
		return authors.stream()
				.filter(author -> author.id().equals(id))
				.findFirst()
				.orElse(null);
	}

}
