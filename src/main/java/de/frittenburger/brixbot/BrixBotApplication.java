package de.frittenburger.brixbot;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.frittenburger.brixbot.impl.SkillManagerImpl;
import de.frittenburger.brixbot.interfaces.SkillManager;
import de.frittenburger.brixmodul.impl.PingSkill;

@SpringBootApplication
public class BrixBotApplication {

	public static void main(String[] args) throws IOException {
		
		SkillManager skillManager = SkillManagerImpl.getInstance();
		skillManager.addSkill(new PingSkill());
		SpringApplication.run(BrixBotApplication.class, args);
	}
}
