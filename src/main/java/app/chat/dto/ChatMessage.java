package app.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class ChatMessage {
	
	private MessageType type;
	private String sender;
	private String content;
	
}
