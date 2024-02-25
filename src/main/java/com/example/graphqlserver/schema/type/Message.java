package com.example.graphqlserver.schema.type;

public record Message(String id, String chatId, String utc, String value) {
}
