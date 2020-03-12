package ca.bcit.comp4656.assign2.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries( {
	@NamedQuery( name= "Employee.getEmployeeList" , query="select e from Employee e" ),
	@NamedQuery( name= "Employee.findById", query= "select e from Employee e where e.ID= :id" ),
	@NamedQuery( name= "Employee.removeById", query= "DELETE from Employee e where e.ID= :id" ),
	@NamedQuery( name= "Employee.updateById", query= "update Employee e set e.firstName= :firstName, e.lastName= :lastName where e.ID= :id")
	} )
@Table(name="A01176216_Employees")
public class Employee
{
	@Id
	@NotNull
	private String ID;
	
	@Column
	@NotNull
	private String firstName;
	
	@Column
	@NotNull
	private String lastName;
	
	@Column
	@NotNull
	private String dob;

	public String getId() {
		return ID;
	}

	public void setId(String id2) {
		this.ID = id2;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + ID + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dob="+ dob +"]";
	}
}
