package de.frittenburger.brixmodul.impl;

import java.util.Date;

import de.frittenburger.brixbot.interfaces.Skill;
import de.frittenburger.brixbot.model.ChatMessage;

public class PingSkill implements Skill {

	@Override
	public boolean match(ChatMessage inMessage) {
		return inMessage.getText().trim().equals("ping");
	}

	@Override
	public ChatMessage process(ChatMessage inMessage) {
		ChatMessage msg = new ChatMessage();
		msg.setTime(new Date().getTime());
		msg.setText("pong");
		return msg;
	}

}
