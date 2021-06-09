package app.webSocket;

import app.chat.dto.ChatMessage;
import app.chat.dto.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventHandler {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectionListener(SessionConnectedEvent event){
        System.out.println(event);
        System.out.println("소켓 연결 : "+event.getMessage());
    }

    @EventListener
    public void handleWebSocketDisconnectionListener(SessionDisconnectEvent event){

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String user = (String) headerAccessor.getSessionAttributes().get("user");
        if(user != null){
            System.out.println("User disconnected : "+user);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(MessageType.LEAVE);
            chatMessage.setSender(user);

            messagingTemplate.convertAndSend("/topic/public",chatMessage);
        }
    }


}
