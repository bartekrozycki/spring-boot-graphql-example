package com.example.graphqlserver.service;
import com.example.graphqlserver.schema.type.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Component
public class ChatServiceImpl {

	private final ConcurrentHashMap<String, Consumer<Message>> listeners = new ConcurrentHashMap<>();

	public void emit(Message message) {
		listeners.values().forEach(consumer -> consumer.accept(message));
	}

	public Flux<Message> getMessages(String chatId) {
		return Flux.create(emitter -> {
			Consumer<Message> consumer = emitter::next;
			listeners.put(chatId, consumer);
			emitter.onDispose(() -> listeners.remove(chatId, consumer));
		}, FluxSink.OverflowStrategy.LATEST);
	}
}
