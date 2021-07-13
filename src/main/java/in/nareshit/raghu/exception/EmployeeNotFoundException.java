package in.nareshit.raghu.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2381213413491777177L;

	public EmployeeNotFoundException() {
		super();
	}
	
	public EmployeeNotFoundException(String messsage) {
		super(messsage);
	}
}
