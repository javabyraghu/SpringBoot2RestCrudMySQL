package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.model.Employee;

public interface EmployeeRepository 
	extends JpaRepository<Employee, Integer> {

	@Modifying
	@Query("UPDATE Employee SET empName=:name WHERE empId=:id")
	public void updateEmployeeName(String name, Integer id);
}
