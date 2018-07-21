package com.s3a.poc.test.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "summary_desc")
public class DbSummaryDesc implements Serializable {
 
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -8622300041275052044L;

	public DbSummaryDesc(String Application, String Description, String Modules, String Datasource, String Users, String Impact) {
		// TODO Auto-generated constructor stub
		this.application = Application;
		this.description = Description;
		this.modules = Modules;
		this.datasource = Datasource;
		this.users = Users;
		this.impact = Impact;
		this.createDateTime = new Date();
		
	}
//
	public DbSummaryDesc() {
		// TODO Auto-generated constructor stub
		this.application = null;
		this.description = null;
		this.modules = null;
		this.datasource = null;
		this.users = null;
		this.impact = null;
		this.createDateTime = new Date();
	}


	@Override
    public String toString() {
        return String.format("DbSummaryDesc[id=%d, application='%s']", id, application);
    }
	
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false, columnDefinition="bigint")
    private Long id;
   
   @Column(name = "version")
   private Integer version;
 
    @Column(name = "application")
    private String application;
 
    @Column(name = "description")
    private String description;

    @Column(name = "modules")
    private String modules;

    @Column(name = "datasource")
    private String datasource;

    @Column(name = "users")
    private String users;

    @Column(name = "impact")
    private String impact;


    @Column(name = "CreateDateTime")
    private Date createDateTime;
    
    //

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModules() {
		return modules;
	}
	public void setModules(String modules) {
		this.modules = modules;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
 



    
    
//
    
    
    
    
    
    
    
}