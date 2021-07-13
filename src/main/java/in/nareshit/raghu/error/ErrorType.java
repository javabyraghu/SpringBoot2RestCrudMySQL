package in.nareshit.raghu.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorType {

	private String dateTime;
	private String moduleInfo;
	private String message;
	private String type;
	
}
