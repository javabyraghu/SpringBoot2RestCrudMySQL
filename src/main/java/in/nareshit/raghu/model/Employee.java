package in.nareshit.raghu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="emptab")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
	@Column(name="eid")
	private Integer empId;
	
	@Column(name="ename")
	@NotNull(message = "Employee Name Can not be Null")
	private String empName;
	
	@Column(name="esal")
	@NotNull(message = "Employee Salary must be give")
	private Double empSal;
	
	@Column(name="edept")
	@NotNull(message = "Dept name must be given")
	@Size(min = 3, message = "Dept name must have at least 3 chars")
	private String empDept;
	
	@Column(name="ehra")
	private Double empHra;
	
	@Column(name="eta")
	private Double empTa;
}
