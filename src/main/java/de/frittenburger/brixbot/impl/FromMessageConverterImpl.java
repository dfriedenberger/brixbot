package de.frittenburger.brixbot.impl;

import java.util.function.Function;

import de.frittenburger.brixbot.model.ChatMessage;
import de.frittenburger.model.Message;

public class FromMessageConverterImpl implements Function<Message, ChatMessage> {

	@Override
	public ChatMessage apply(Message t) {

		ChatMessage m = new ChatMessage();
		m.setText(t.getText());
		m.setTime(t.getTime());
		return m;
	}

}
