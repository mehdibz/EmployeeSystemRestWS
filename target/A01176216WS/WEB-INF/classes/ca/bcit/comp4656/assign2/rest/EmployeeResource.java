package ca.bcit.comp4656.assign2.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.bcit.comp4656.assign2.jpa.entity.Employee;
import ca.bcit.comp4656.assign2.services.EmployeeServices;
import ca.bcit.comp4656.assign2.services.EmployeeServicesImpl;
import ca.bcit.comp4656.assign2.util.*;

@Path("/services/")
public class EmployeeResource
{
	private Employee employee;

	
	@GET
	@Path("/findEmployeeById/{ID}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findEmployeeByID(@PathParam("ID") String id) {
		EmployeeServicesImpl services = new EmployeeServicesImpl();
		employee = services.findEmployeeById(id);
        ResponseCode responseCode = new ResponseCode();
      
        if (employee != null) {

            responseCode.setCode("000");
            responseCode.setDescription(employee.getFirstName() + " " + employee.getLastName());
        }else {

        	responseCode.setCode("801");
        	responseCode.setDescription("Unsucessful: No match found");
        }
	 
        return Response.status(Response.Status.OK).entity(responseCode).build();	
	}
	
	@POST
	@Path("/addEmployee")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	public Response addPerson(@FormParam("ID") String id,@FormParam("firstName") String firstName,@FormParam("lastName") String lastName,@FormParam("dob") String dob) {
		
		
		EmployeeServicesImpl services = new EmployeeServicesImpl();
		ResponseCode responseCode = new ResponseCode();
		int addStatus = -1;
		
		int validInfo = ValidateInfo.fieldsCheck(id, firstName, lastName, dob);

		if(validInfo==0) {
			
			employee = createEmployee(id, firstName, lastName, dob);
			
	        try {
	        	addStatus = services.addEmployee(employee);
	            responseCode.setCode("000");
	            responseCode.setDescription("The employee added Sucessfully");
	        }
	        catch (java.text.ParseException e) {
	        	addStatus = 3;
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
	        if (addStatus == 2) {
	        	responseCode.setCode("902");
	        	responseCode.setDescription("Unsucessful: The employee already exist!");
	        }
	        if (addStatus == 3) 
	        {
	        	responseCode.setCode("503");
	        	responseCode.setDescription("Unsucessful: Invalid date format");
	        }
	        if(addStatus == 0) {
	        	responseCode.setCode("504");
	        	responseCode.setDescription("Unsucessful: The request processed with error");
	        }
			
		}else {
        	responseCode.setCode(String.valueOf(validInfo));
        	responseCode.setDescription(ValidateInfo.desc);
		}

		ValidateInfo.result=0;
		ValidateInfo.desc="";
        return Response.status(Response.Status.OK).entity(responseCode).build();
	}
	
	@PUT
	@Path("/updateEmployee")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_XML)
	public Response updateEmployeeById(@FormParam("ID") String id,@FormParam("firstName") String firstName,@FormParam("lastName") String lastName,@FormParam("dob") String dob) {
		
		EmployeeServicesImpl services = new EmployeeServicesImpl();
		employee = createEmployee(id, firstName, lastName, dob);
        ResponseCode responseCode = new ResponseCode();
		int updateStatus = -1;
		int validInfo = ValidateInfo.fieldsCheck(id, firstName, lastName, dob);

      
		if(validInfo==0) {
			
			employee = createEmployee(id, firstName, lastName, dob);
			try {
	        	updateStatus = services.updateEmployeeById(employee);
	            responseCode.setCode("001");
	            responseCode.setDescription("Updated Sucessfully");
	            
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        if (updateStatus== 2) {
	        	responseCode.setCode("902");
	        	responseCode.setDescription("Updated Unsucessful");
	        }
		}else {
        	responseCode.setCode(String.valueOf(validInfo));
        	responseCode.setDescription(ValidateInfo.desc);
		}
 
		ValidateInfo.result=0;
		ValidateInfo.desc="";
        return Response.status(Response.Status.OK).entity(responseCode).build();
	}
	
	@DELETE
	@Path("/removeEmployee/{ID}")
	@Produces(MediaType.APPLICATION_XML)
	public Response removePersonByID(@PathParam("ID") String id) {
		EmployeeServices services = new EmployeeServicesImpl();
        int numberDeleted = 0;
        ResponseCode responseCode = new ResponseCode();
        
        try {
        	numberDeleted = services.removeEmployee(id);
            responseCode.setCode("001");
            responseCode.setDescription("Deleted Sucessfully");
        } catch (Exception e) {
        	e.printStackTrace();
        	numberDeleted = 0;
        }
        if (numberDeleted == 0) {
        	responseCode.setCode("902");
        	responseCode.setDescription("Delete Unsucessful");
        }
        
        return Response.status(Response.Status.OK).entity(responseCode).build();
	}
	
	@GET
	@Path("/employeeList")
	@Produces(MediaType.APPLICATION_XML)
	public List<Employee> getPersonList() {

		EmployeeServicesImpl response = new EmployeeServicesImpl();
		List<Employee> list = response.getEmployeeList();
		return list;
	}
	
	public Employee createEmployee(String id, String firstName, String lastName, String dob) {
		
		Employee employee = new Employee();
		employee.setId(id);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setDob(dob);
		
		return employee;
	}
}
