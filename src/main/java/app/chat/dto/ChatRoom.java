package app.chat.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChatRoom {
	
	private String id;
	private String name;
	private List<WebSocketSession> sessionList = new ArrayList<>();
	
}
