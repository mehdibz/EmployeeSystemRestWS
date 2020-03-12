package ca.web.employee.jpa.data.services;


import java.text.ParseException;
import java.util.List;

import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import ca.web.employee.jpa.data.EmployeeFacade;
import ca.web.employee.jpa.data.entity.Employee;


public class EmployeeServicesImpl implements EmployeeServices
{	
	
	@Override
	public Employee findEmployeeById( String id )
	{
		return EmployeeFacade.getInstance().findEmployeeById(id);
	}

	@Override
	public List<Employee> getEmployeeList() {
		return EmployeeFacade.getInstance().getEmployeeList();
	}

	@Override
	public int removeEmployee(String id) {
		return EmployeeFacade.getInstance().removeEmployee(id);
	}

	@Override
	public int addEmployee(Employee employee) throws RollbackException, SecurityException, IllegalStateException, NotSupportedException, SystemException, HeuristicMixedException, HeuristicRollbackException, javax.transaction.RollbackException,ParseException {
		return EmployeeFacade.getInstance().addEmployee(employee);
	}
	
	@Override
	public int updateEmployeeById(Employee employee) throws RollbackException, SecurityException, IllegalStateException, NotSupportedException, SystemException, HeuristicMixedException, HeuristicRollbackException, javax.transaction.RollbackException,ParseException {
		return EmployeeFacade.getInstance().updateEmployeeById(employee);
	}

}
