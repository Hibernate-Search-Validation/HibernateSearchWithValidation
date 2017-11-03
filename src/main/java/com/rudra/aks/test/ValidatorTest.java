package com.rudra.aks.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.hibernate.validator.HibernateValidator;

import com.rudra.aks.bo.TestBO;

public class ValidatorTest {

	public static void main(String[] args) throws FileNotFoundException, NoSuchMethodException, SecurityException {
		
		//Getting Validator instance with Annotations
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		//If there are multiple JSR303 implementations in classpath
		//we can get HibernateValidator specifically too
		ValidatorFactory hibernateVF = Validation.byProvider(HibernateValidator.class)
									.configure().buildValidatorFactory();
		
		System.out.println("\nSimple field level validation example");
		//TestBO testBO = new TestBO("emailtest", 34, "ac@rudra.com", "kolkata", "buss", 89898);
		TestBO testBO = new TestBO();
		Set<ConstraintViolation<TestBO>> validationErrors = validator.validate(testBO);
		
		if(!validationErrors.isEmpty()){
			for(ConstraintViolation<TestBO> error : validationErrors){
				System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());
				
			}
		}
		
		
		/*System.out.println("\nParameter validation example");
		ParamValidationBean paramValidationBean = new ParamValidationBean("Pankaj");
		Method method = ParamValidationBean.class.getMethod("printData", String.class);
		Object[] params = {"1234"};
		ExecutableValidator executableValidator = validator.forExecutables();
		Set<ConstraintViolation<ParamValidationBean>> violations = 
				executableValidator.validateParameters(paramValidationBean, method, params);
		if(!violations.isEmpty()){
			for(ConstraintViolation<ParamValidationBean> error : violations){
				System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());
				
			}
		}
*/		
	}

}