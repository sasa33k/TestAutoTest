package com.s3a.poc.test.model;


import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "test_steps")
public class DbTestSteps implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 2319119888360844688L;

	public DbTestSteps(
			Integer ModId,String ModName, Integer StepNo, String StepName, String GetValue, String GetType, 
			String DoType,String DoValue, Boolean isScreenCap,Boolean isRevResult, 
			String SkipStep, String Remark) {
		// TODO Auto-generated constructor stub

		this.modId = ModId;
		this.modName = ModName;
		this.stepNo = StepNo;
		this.stepName = StepName;
		this.getValue = GetValue;
		this.getType = GetType;
		this.doType = DoType;
		this.doValue = DoValue;
		this.isScreenCap = isScreenCap;
		this.isRevResult = isRevResult;
		this.skipStep = SkipStep;
		this.remark = Remark;
	}
//
	public DbTestSteps() {
		// TODO Auto-generated constructor stub
		this.modId = null;
		this.modName = null;
		this.stepNo = null;
		this.stepName = null;
		this.getValue = null;
		this.getType = null;
		this.doType = null;
		this.doValue = null;
		this.isScreenCap = null;
		this.isRevResult = null;
		this.skipStep = null;
		this.remark = null;
//		this.OutStatus = null;
//		this.OutResult = null;
//		this.OutLapsedTime = null;
	}
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", unique = true, updatable = false, nullable = false, columnDefinition="bigint")
	    private Long id;
 
    @Column(name = "modId")
    private Integer modId;
 
    @Column(name = "modName")
    private String modName;

    @Column(name = "stepNo")
    private Integer stepNo;
    
    @Column(name = "stepName")
    private String stepName;

	@Column(name = "getValue")
    private String getValue;

    @Column(name = "getType")
    private String getType;

    @Column(name = "doType")
    private String doType;
    
    @Column(name = "doValue")
    private String doValue;

    @Column(name = "isScreenCap")
    private Boolean isScreenCap;

    @Column(name = "isRevResult")
    private Boolean isRevResult;

    @Column(name = "skipStep")
    private String skipStep;

    @Column(name = "remark")
    private String remark;
    
    @Column(name = "version")
    private Integer version;
//
//    @Column(name = "OutStatus")
//    private Boolean OutStatus;
//    
//    @Column(name = "OutResult")
//    private Boolean OutResult;
//    
//    @Column(name = "OutLapsedTime")
//    private Long OutLapsedTime;

	
    //
    
    


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getModId() {
		return modId;
	}
	public void setModId(Integer modId) {
		this.modId = modId;
	}
	public String getModName() {
		return modName;
	}
	public void setModName(String modName) {
		this.modName = modName;
	}
	public Integer getStepNo() {
		return stepNo;
	}
	public void setStepNo(Integer stepNo) {
		this.stepNo = stepNo;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getGetValue() {
		return getValue;
	}
	public void setGetValue(String getValue) {
		this.getValue = getValue;
	}
	public String getGetType() {
		return getType;
	}
	public void setGetType(String getType) {
		this.getType = getType;
	}
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	public String getDoValue() {
		return doValue;
	}
	public void setDoValue(String doValue) {
		this.doValue = doValue;
	}
	public Boolean getIsScreenCap() {
		return isScreenCap;
	}
	public void setIsScreenCap(Boolean isScreenCap) {
		this.isScreenCap = isScreenCap;
	}
	public Boolean getIsRevResult() {
		return isRevResult;
	}
	public void setIsRevResult(Boolean isRevResult) {
		this.isRevResult = isRevResult;
	}
	public String getSkipStep() {
		return skipStep;
	}
	public void setSkipStep(String skipStep) {
		this.skipStep = skipStep;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
    public String toString() {
        return String.format("DbTestSteps[id=%d, modId='%s', modName='%s', stepNo=%d, stepName='%s']", 
        		id, modId, modName, stepNo, stepName);
//    	modId, modName, stepNo, stepName,
//    	getValue, getType, doType, doValue, 
//    	isScreenCap, isRevResult, skipStep, remark 
    }
	
	
	
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
    

 

    
    
    
    
    
}