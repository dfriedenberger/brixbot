package de.frittenburger.brixbot.interfaces;

import de.frittenburger.brixbot.model.ChatMessage;

public interface Skill {

	boolean match(ChatMessage inMessage);

	ChatMessage process(ChatMessage inMessage);

}
