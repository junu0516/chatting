package app.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import app.chat.dto.ChatMessage;

@Controller
public class ChatController {
	
	@GetMapping("/")
	public String showMainView() {
		
		return "/views/mainView";
	}
	
	/*
	 * WebSocketConfig에 명시된대로,
	 * 
	 * /user로 포함된 uri 요청을 통해 들어온 메시지는 모두 @MessageMapping 어노테이션이 달린 메소드로 매핑됨 
	 * 
	 * 
	 * */
	
	@MessageMapping("/sent")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

		System.out.println("Message Mapping : sendMessage");
		System.out.println(chatMessage);
		return chatMessage;
	}
	
	@MessageMapping("/entrance")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage,
							   SimpMessageHeaderAccessor headerAccessor) {

		System.out.println("Message Mapping : addUser");
		headerAccessor.getSessionAttributes().put("user", chatMessage.getSender());
		
		return chatMessage;
	}
	
	
}
