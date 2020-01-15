package de.frittenburger.brixbot.impl;

import java.util.function.Function;

import de.frittenburger.brixbot.model.ChatMessage;
import de.frittenburger.model.Message;

public class ToMessageConverterImpl implements Function<ChatMessage, Message> {

	@Override
	public Message apply(ChatMessage t) {
		
		Message m = new Message();
		m.setAvatar("bot");
		m.setTime(t.getTime());
		m.setText(t.getText());
		return m;
	}

}
