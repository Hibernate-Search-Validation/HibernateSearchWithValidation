package com.rudra.aks.test;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.rudra.aks.bo.TestBO;

public class ValidationTest {
	private static Validator validator;

	/**
	 * TestBO - id, 
	 * 			name - must be between 4-10 chars. 
	 * 			age - if < min, "Not eligible yet", >max, "Sorry ! You're too old." 
	 * 			emailid - Email id is not valid!
	 * 			address - Address can't be blank! 
	 * 			department - Department is empty! 
	 * 			salary - Salary Ranges b/w 1000 to 9999.
	 */
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void nameLengthBetween4to10() {
		//created testbo with violating length of name property as not 4-10 chars
		TestBO testBO = new TestBO("aks", 26, "aks@gmail.com", "delhi", "sales", 2345);
		  
		Set<ConstraintViolation<TestBO>> constraintViolations = validator.validate( testBO );
		
		assertEquals( 1, constraintViolations.size() );
		assertEquals( "must be between 4-10 chars.", constraintViolations.iterator().next().getMessage());
	}
    
	@Test
	public void	ageLimitBelow20() {
		//Create TesBO to check age limit between 20 to 40, voilated
		TestBO testBOMinAge = new TestBO("agetest", 15, "ac@rudra.com", "mumbai", "markt", 8989);
   
		Set<ConstraintViolation<TestBO>>	 constraintViolation = validator.validate(testBOMinAge);
		//assertEquals( 1, constraintViolation.size());
		assertEquals( "Not eligible yet", constraintViolation.iterator().next().getMessage());
	}
  
	@Test
	public void	ageLimitAbove40() {
		//Create TesBO to check age limit between 20 to 40, voilated
		TestBO testBOMaxAge = new TestBO("agetest", 50, "ac@rudra.com", "mumbai", "markt", 8989);
   
		Set<ConstraintViolation<TestBO>>	 constraintViolation = validator.validate(testBOMaxAge);
		assertEquals( 1, constraintViolation.size());
		assertEquals( "Sorry ! You're too old.", constraintViolation.iterator().next().getMessage());
	} 
   
   
	@Test
	public void validateEmailId() {
		//Create TestBO to validate email id wellformedness 
		TestBO testBO = new TestBO("emailtest", 34, "accom", "patna", "sales", 9073);
		
		Set<ConstraintViolation<TestBO>>	 constraintViolation = validator.validate(testBO);
		assertEquals( 1, constraintViolation.size());
		assertEquals( "Email id is not valid!", constraintViolation.iterator().next().getMessage());
	}
	
	@Test
	public void adderssValidation() {
		//Create TestBO to validate address as not blank( not empty also, i.e, only whitespaces are also not allowed.)
		TestBO testBO = new TestBO("emailtest", 34, "ac@rudra.com", " ", "sales", 9073);
		
		Set<ConstraintViolation<TestBO>>	 constraintViolation = validator.validate(testBO);
		assertEquals( 1, constraintViolation.size());
		assertEquals( "Address can't be blank!", constraintViolation.iterator().next().getMessage());
	}
	
	@Test
	public void departmentNameValidation() {
		//Create TestBO to check department name is not empty, lenght can't be zero. whitespaces are allowed.
		TestBO testBO = new TestBO("emailtest", 34, "ac@rudra.com", "goa", "", 9073);
		
		Set<ConstraintViolation<TestBO>>	 constraintViolation = validator.validate(testBO);
		assertEquals( 1, constraintViolation.size());
		assertEquals( "Department is empty!", constraintViolation.iterator().next().getMessage());
	}
	
	@Test
	public void salaryRangeTest() {
		//Create TestBO with salary not in range of 1000-9999.
		TestBO testBO = new TestBO("emailtest", 34, "ac@rudra.com", "kolkata", "buss", 89898);
		
		Set<ConstraintViolation<TestBO>>	 constraintViolation = validator.validate(testBO);
		assertEquals( 1, constraintViolation.size());
		assertEquals( "Salary Ranges b/w 1000 to 9999.", constraintViolation.iterator().next().getMessage());
	}
	
	
}
