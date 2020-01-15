package de.frittenburger.brixbot.controller;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.frittenburger.api.BotApi;
import de.frittenburger.brixbot.impl.ChatSessionImpl;
import de.frittenburger.brixbot.impl.FromMessageConverterImpl;
import de.frittenburger.brixbot.impl.SkillManagerImpl;
import de.frittenburger.brixbot.impl.ToMessageConverterImpl;
import de.frittenburger.brixbot.interfaces.ChatSession;
import de.frittenburger.brixbot.interfaces.SkillManager;
import de.frittenburger.brixbot.model.ChatMessage;
import de.frittenburger.model.Message;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/")
public class BotController implements BotApi {

	private static final Logger logger = LogManager.getLogger(BotController.class);
	private static final SkillManager skillManager = SkillManagerImpl.getInstance();

	private static final Function<ChatMessage,Message> toMessageConverter = new ToMessageConverterImpl();
	
	private static final Function<Message,ChatMessage> fromMessageConverter = new FromMessageConverterImpl();

    @Override
    public ResponseEntity<Message> chatPost(Message inMessage) {
    	
    	logger.info("recv {}",inMessage);

    	ChatMessage chatInMessage = fromMessageConverter.apply(inMessage);
    	ChatSession chatSession = ChatSessionImpl.getSession("xxx");
    	chatSession.addMessage(chatInMessage);
    	
    	ChatMessage chatOutMessage = skillManager.process(chatSession,chatInMessage);
    	Message outMessage = toMessageConverter.apply(chatOutMessage);
    	
    	logger.info("send {}",outMessage);

    	return ok(outMessage);
    };
    
	
    @Override
    public ResponseEntity<List<Message>> history(@Valid Long offset, @Valid Integer limit) {
    	
    	ChatSession chatSession = ChatSessionImpl.getSession("xxx");
    	
    	if(offset == null) offset = 0L;
    	if(limit == null) limit = 100;

    	List<ChatMessage> messages = chatSession.getMessages(offset.longValue(),limit.intValue());
    	return ok(messages.stream().map(m -> toMessageConverter.apply(m)).collect(Collectors.toList()));
    }
	
	
	
}
