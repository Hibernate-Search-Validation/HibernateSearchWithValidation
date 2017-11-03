package com.rudra.aks.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Proxy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity	
@Table(name = "sprintx_test")
@Proxy(lazy = false)
@Indexed
@AnalyzerDef(name = "customanalyzer",
		tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
		filters = {
				@TokenFilterDef(factory = LowerCaseFilterFactory.class),
				@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
			    @Parameter(name = "language", value = "English")
			  })
})
public class TestBO {
	
	@Id
	@GeneratedValue
    //@Field
    private int id;
	
	@Field //(analyze = Analyze.NO)
	@Analyzer(definition= "customanalyzer")
	@Length(min=4, max=10, message = "must be between 4-10 chars.")
	private String name;

	//@Field()
	@Min( value=20, message = "Not eligible yet" )
	@Max( value=40, message = "Sorry ! You're too old." )
	private int age;
	
	//@Field()
	@Email(message="Email id is not valid!")
	private String emailid;

	//@Field
	@NotBlank( message = "Address can't be blank!")
	private String address;
	
	//@Field
	@NotEmpty( message = "Department is empty!")
	private String department;
	
	@Column
	@Range(min=1000, max=9999, message = "Salary Ranges b/w 1000 to 9999.")
	private int salary;
	
	/*@OneToOne( fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Address fullAddress;
	
	public Address getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(Address fullAddress) {
		this.fullAddress = fullAddress;
	}
	*/

	
	public TestBO(@NotNull int id, @Valid String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public TestBO(String name, int age, String emailid, String address, String department, int salary) {
		super();
		this.name = name;
		this.age = age;
		this.emailid = emailid;
		this.address = address;
		this.department = department;
		this.salary = salary;
	}
	
	public TestBO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TestBO [id=" + id 
				+ ", name=" + name 
				+ ", address=" + address 
				+ ", age=" + age 
				+ ", department=" + department
				+ ", emailid=" + emailid 
				+ ", salary=" + salary + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}
