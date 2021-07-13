package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.model.Employee;

public interface IEmployeeService {

	Integer saveEmployee(Employee e);
	void updateEmployee(Employee e);
	void deleteEmployee(Integer id);
	
	Employee getOneEmployee(Integer id);
	List<Employee> getAllEmployees();
	
	void updateEmployeeName(String name, Integer id);
}
