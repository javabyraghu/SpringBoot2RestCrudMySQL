package in.nareshit.raghu.util;

import org.springframework.stereotype.Component;

import in.nareshit.raghu.model.Employee;

@Component
public class EmployeeUtil {

	public void calculateData(Employee e) {
		double sal = e.getEmpSal();
		double hra = sal * 20/100.0;
		double ta = sal * 12/100.0;

		e.setEmpHra(hra);
		e.setEmpTa(ta);		
	}

}
