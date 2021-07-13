package in.nareshit.raghu.response;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {

	private HttpStatus status;
//	private String status;
	private String message;
	private String module;
	private String date;
}
