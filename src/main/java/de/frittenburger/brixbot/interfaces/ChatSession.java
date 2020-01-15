package de.frittenburger.brixbot.interfaces;

import java.util.List;

import de.frittenburger.brixbot.model.ChatMessage;

public interface ChatSession {

	List<ChatMessage> getMessages(long offset, int limit);

	void addMessage(ChatMessage message);

}
