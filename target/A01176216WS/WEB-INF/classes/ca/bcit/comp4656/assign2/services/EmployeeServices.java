package ca.bcit.comp4656.assign2.services;

import java.util.List;

import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import ca.bcit.comp4656.assign2.jpa.entity.Employee;

public interface EmployeeServices
{

	Employee findEmployeeById(String id );
	List<Employee> getEmployeeList();
	int removeEmployee(String id );
	int addEmployee(Employee employee) throws RollbackException, SecurityException, 
											  IllegalStateException, NotSupportedException, 
											  SystemException, HeuristicMixedException, 
											  HeuristicRollbackException, javax.transaction.RollbackException,
											  java.text.ParseException;
	
	int updateEmployeeById(Employee employee) throws RollbackException, SecurityException,
														 IllegalStateException, NotSupportedException,
														 SystemException, HeuristicMixedException, 
														 HeuristicRollbackException, javax.transaction.RollbackException,
														 java.text.ParseException;
	
}
