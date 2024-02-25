package com.example.graphqlserver.api.v1;

import com.example.graphqlserver.schema.type.Message;
import com.example.graphqlserver.service.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@Controller
public class ChatController {
	public int c = 0;

	private final ChatServiceImpl chatServiceImpl;

	public ChatController(@Autowired ChatServiceImpl chatServiceImpl) {
		this.chatServiceImpl = chatServiceImpl;
	}

	@MutationMapping("sendMessage")
	public Message sendMessage(@Argument String chatId, @Argument String content) {
		Message message = new Message("id-" + c, chatId, LocalDateTime.now().toString(), content); // Assume Message is a proper class
		chatServiceImpl.emit(message);

		return message;
	}

	@SubscriptionMapping("newChatMessage")
	public Flux<Message> newChatMessage(@Argument String chatId) {
		System.out.println("New sub");
		return chatServiceImpl.getMessages(chatId);
	}

	@SubscriptionMapping("time")
	public Flux<String> time() {
		System.out.println("New sub");
		return Flux.interval(Duration.ofSeconds(1)).map((i) -> LocalDateTime.now().toString()).log();
	}


}
