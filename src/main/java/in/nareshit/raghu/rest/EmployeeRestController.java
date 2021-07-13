package in.nareshit.raghu.rest;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nareshit.raghu.exception.EmployeeNotFoundException;
import in.nareshit.raghu.model.Employee;
import in.nareshit.raghu.response.MessageResponse;
import in.nareshit.raghu.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/employees")
@Api(description = "EMPLOYEE OPERATIONS")
public class EmployeeRestController {

	@Autowired
	private IEmployeeService service;

	//1. Get All employees
	@GetMapping("/all")
	@ApiOperation("FETCH ALL EMPLOYEES")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = service.getAllEmployees();
		return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
	}

	//2. Get one Employee
	@GetMapping("/one/{id}")
	@ApiOperation("FETCH ONE EMPLOYEE BY ID")
	public ResponseEntity<?> getOneEmployee(
			@PathVariable Integer id
			) 
	{
		ResponseEntity<?> response = null;
		Employee employee = service.getOneEmployee(id);
		response = new ResponseEntity<Employee>(
				employee,
				HttpStatus.OK); //200

		return response;
	}

	//3. create one Employee
	@PostMapping("/save")
	public ResponseEntity<String> createEmployee(
			@RequestBody @Valid Employee employee
			) 
	{
		Integer id =  service.saveEmployee(employee);
		String message = "Employee '"+id+"' Created!";
		return new ResponseEntity<String>(message,HttpStatus.CREATED); //201
	}

	//4. delete record by id
	@ApiIgnore
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteEmployee(
			@PathVariable Integer id
			) 
	{
		ResponseEntity<String> response = null;
		try {
			service.deleteEmployee(id);
			String message = "Employee '"+id+"' Deleted!";
			response = new ResponseEntity<String>(
					message,
					HttpStatus.OK);
		} catch (EmployeeNotFoundException e) {
			throw e;
		}
		return response;
	}


	//5. update record
	@PutMapping("/update")
	public ResponseEntity<String> updateEmployee(
			@RequestBody Employee employee
			)
	{
		ResponseEntity<String> response = null;
		try {
			service.updateEmployee(employee);
			String message = "Employee updated!";
			response = new ResponseEntity<String>(
					message, 
					HttpStatus.OK);

		} catch (EmployeeNotFoundException e) {
			throw e;
		}
		return response;
	}
	
	/*@PatchMapping("/update/{id}/{name}")
	public ResponseEntity<String> updateEmployeeName(
			@PathVariable Integer id,
			@PathVariable String name
			)
	{
		ResponseEntity<String> response = null;
		try {
			service.updateEmployeeName(name, id);
			String message = "Employee Name updated!";
			response = new ResponseEntity<String>(
					message, 
					HttpStatus.OK);

		} catch (EmployeeNotFoundException e) {
			throw e;
		}
		
		return response;
	}*/

	@PatchMapping("/update/{id}/{name}")
	public MessageResponse updateEmployeeName(
			@PathVariable Integer id,
			@PathVariable String name
			)
	{
		MessageResponse response = null;
		try {
			service.updateEmployeeName(name, id);
			String message = "Employee Name updated!";
			/*response = new MessageResponse(
						"SUCCESS",
						message,
						"EMPLOYEE",
						new Date().toString()
					);*/
			response = MessageResponse
					.builder()
					.date(new Date().toString())
					.message(message)
					.status(HttpStatus.OK)
					.module("EMPLOYEE")
					.build();
		} catch (EmployeeNotFoundException e) {
			throw e;
		}
		
		return response;
	}
}
