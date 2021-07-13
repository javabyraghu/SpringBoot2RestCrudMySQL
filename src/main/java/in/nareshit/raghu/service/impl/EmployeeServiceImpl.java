package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.exception.EmployeeNotFoundException;
import in.nareshit.raghu.model.Employee;
import in.nareshit.raghu.repo.EmployeeRepository;
import in.nareshit.raghu.service.IEmployeeService;
import in.nareshit.raghu.util.EmployeeUtil;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private EmployeeUtil util;

	/**
	 * It will do caculation of hra,ta 
	 * insert data into db table 
	 */

	public Integer saveEmployee(Employee e) {
		util.calculateData(e);
		e = repo.save(e);
		return e.getEmpId();
	}

	/*private void calculateData(Employee e) {
		double sal = e.getEmpSal();
		double hra = sal * 20/100.0;
		double ta = sal * 12/100.0;

		e.setEmpHra(hra);
		e.setEmpTa(ta);
	}*/

	/**
	 * Read id from given object
	 * if id not null and exist db then call update
	 * else throw exception
	 */
	public void updateEmployee(Employee e) {
		if( e.getEmpId() != null && 
				repo.existsById(e.getEmpId())
				) 
		{
			util.calculateData(e);
			repo.save(e);
		} else {
			throw new EmployeeNotFoundException(
					(
							e.getEmpId()==null?
									"No Id is provided!!" :
										"Employee '"+e.getEmpId()+"' not exist"
							)
					);
		}
	}

	/***
	 * First check given id exist in Database 
	 * 	If exist perform Delete operation
	 *  else throw exception 
	 */
	public void deleteEmployee(Integer id) {
		//repo.deleteById(id);
		repo.delete(getOneEmployee(id));
	}

	/***
	 * If given id exist in database load object and return same
	 * else throw exception
	 */
	public Employee getOneEmployee(Integer id) {
		Optional<Employee> opt = repo.findById(id);
		if(opt.isPresent())
			return opt.get();
		else
			throw new EmployeeNotFoundException("Employee '"+id+"' not exist");
		/*return repo.findById(id)
				.orElseThrow(
						()-> new EmployeeNotFoundException(
								"Employee '"+id+"' not exist")
						);*/
	}

	/***
	 * Fetch all rows from Database table 
	 * 
	 */
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}
	
	@Transactional
	public void updateEmployeeName(String name, Integer id) {
		
		if(repo.existsById(id))
			repo.updateEmployeeName(name, id);
		
		else throw new EmployeeNotFoundException(
				"Employee '"+id+"' not exist");
	}

}
