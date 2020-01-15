package de.frittenburger.brixbot.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.frittenburger.brixbot.interfaces.ChatSession;
import de.frittenburger.brixbot.interfaces.Skill;
import de.frittenburger.brixbot.interfaces.SkillManager;
import de.frittenburger.brixbot.model.ChatMessage;

public class SkillManagerImpl implements SkillManager {

	private final List<Skill> skills = new ArrayList<>();

	private SkillManagerImpl() {}
	
	@Override
	public void addSkill(Skill skill) {
		skills.add(skill);
	}
	
	@Override
	public ChatMessage process(ChatSession chatSession, ChatMessage inMessage) {

		
		for(Skill skill : skills )
		{
			if(skill.match(inMessage))
				return skill.process(inMessage);
		}

		ChatMessage defaultMessage = new ChatMessage();
		defaultMessage.setTime(new Date().getTime());
		defaultMessage.setText("I didn't understand what you said.");
		return defaultMessage;
	}
	
	private static SkillManager instance = new SkillManagerImpl();
	public static SkillManager getInstance() {
		return instance;
	}
	
	
	

}
