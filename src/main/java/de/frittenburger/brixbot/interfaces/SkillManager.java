package de.frittenburger.brixbot.interfaces;

import de.frittenburger.brixbot.model.ChatMessage;

public interface SkillManager {

	ChatMessage process(ChatSession chatSession, ChatMessage inMessage);

	void addSkill(Skill skill);

}
