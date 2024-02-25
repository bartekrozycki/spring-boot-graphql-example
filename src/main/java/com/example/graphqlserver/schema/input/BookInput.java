package com.example.graphqlserver.schema.input;

public record BookInput(String id, String name, int pageCount, String authorId) {
}
