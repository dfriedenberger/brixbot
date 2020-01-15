package de.frittenburger.brixbot.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.frittenburger.brixbot.interfaces.ChatSession;
import de.frittenburger.brixbot.model.ChatMessage;

public class ChatSessionImpl implements ChatSession {


	private final String sessionId;
	private List<ChatMessage> messages = new ArrayList<>();

	private ChatSessionImpl(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public List<ChatMessage> getMessages(long offset, int limit) {
		return messages.stream().filter(m -> m.getTime() >= offset).limit(limit).collect(Collectors.toList());
	}
	
	@Override
	public void addMessage(ChatMessage message) {
		messages.add(message);
	}
	
	public static ChatSession getSession(String sessionId) {
		return new ChatSessionImpl(sessionId);
	}

	

	

	

}
