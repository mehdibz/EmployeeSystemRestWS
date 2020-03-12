package ca.web.employee.jpa.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import ca.web.employee.jpa.data.entity.Employee;



public class EmployeeFacade {

	private EntityManagerFactory entityManagerFactory = null;
	private static EmployeeFacade employeeFacade = null;
	private static Employee employee;
	
	@PersistenceContext
	private static EntityManager entityManager = null;
	
	
	public static synchronized EmployeeFacade getInstance()
	{
		if ( employeeFacade!=null)
		{
			return employeeFacade;
		}
		else return new EmployeeFacade();
	}
		
	private EmployeeFacade() {
		entityManagerFactory = Persistence.createEntityManagerFactory("PersonPU");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public EmployeeFacade(EntityManager em) {
		EmployeeFacade.entityManager = em;
	}

	public Employee findEmployeeById( String id )
	{
		try {
			employee = entityManager.createNamedQuery( "Employee.findById", Employee.class ).setParameter("id", id ).getSingleResult();
			return employee;
		}catch(Exception e){
			return null;
		}
	}
	
	public List<Employee> getEmployeeList() {
		return entityManager.createNamedQuery( "Employee.getEmployeeList", Employee.class ).getResultList();
	}

	public int removeEmployee(String id) {  

		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery( "Employee.removeById").setParameter("id", id );
			int result = query.executeUpdate();
			entityManager.getTransaction().commit();
			
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}		
	}
	
	public int addEmployee(Employee employee) throws 
								NotSupportedException, 
								SystemException, 
								SecurityException, 
								IllegalStateException, 
								RollbackException, 
								HeuristicMixedException, 
								HeuristicRollbackException, 
								javax.transaction.RollbackException,
								java.text.ParseException
	{ 
		String Id = employee.getId();
		if(findEmployeeById(Id)==null) {
			entityManager.getTransaction().begin();
			entityManager.persist(employee);
			entityManager.getTransaction().commit();
			return 1;
		}
		if(findEmployeeById(Id).getId().equals(Id)) {
			return 2;
		}else {
			return 0;
		}
		
	}
	
	public int updateEmployeeById (Employee employee ) throws
					NotSupportedException, 
					SystemException, 
					SecurityException, 
					IllegalStateException, 
					RollbackException, 
					HeuristicMixedException, 
					HeuristicRollbackException, 
					javax.transaction.RollbackException
	{
		if(findEmployeeById(employee.getId())!=null) {
			entityManager.getTransaction().begin();
			entityManager.merge(employee);
			entityManager.getTransaction().commit();
			return 1;
		}
		return 2;
	}
	
	public void closeEntityManager()
	{
		if ( entityManager.isOpen() )
		{
			entityManager.close();
		}
	}

}
