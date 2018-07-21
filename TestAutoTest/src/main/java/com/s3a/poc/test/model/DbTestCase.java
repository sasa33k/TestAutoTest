package com.s3a.poc.test.model;


import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "test_case")
public class DbTestCase implements Serializable {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 8752003187984397179L;

	public DbTestCase(String TSID, String TSDesc,
			Integer MID,String Module, String Function,String TCID, String SubID, 
			String TcType,String TcDesc, String TcResultDesc,Boolean isAcceptCritera, Integer Priority, 
			String PreReq,String TSteps, String TBeforeRun,String TAfterRun, String TSkip) {
		// TODO Auto-generated constructor stub
		
		this.tsId = TSID;
		this.tsDesc = TSDesc;
		this.mId = MID;
		this.module = Module;
		this.function = Function;
		this.tcId = TCID;
		this.subId = SubID;
		this.tcType = TcType;
		this.tcDesc = TcDesc;
		this.tcResultDesc = TcResultDesc;
		this.isAcceptCritera = isAcceptCritera;
		this.priority = Priority;
		this.preReq = PreReq;
		this.tSteps = TSteps;
		this.tBeforeRun = TBeforeRun;
		this.tAfterRun = TAfterRun;
		this.tSkip = TSkip;
	}
//
	public DbTestCase() {
		// TODO Auto-generated constructor stub
		this.tsId = null;
		this.tsDesc = null;
		this.mId = null;
		this.module = null;
		this.function = null;
		this.tcId = null;
		this.subId = null;
		this.tcType = null;
		this.tcDesc = null;
		this.tcResultDesc = null;
		this.isAcceptCritera = null;
		this.priority = null;
		this.preReq = null;
		this.tSteps = null;
		this.tBeforeRun = null;
		this.tAfterRun = null;
		this.tSkip = null;
	}

	@Override
    public String toString() {
        return String.format("DbSummaryDesc[id=%d, tsId='%s', tsDesc='%s, subId='%s', mId=%d, module='%s', tSteps='%s']",
        		                          id, tsId, tsDesc, subId, mId, module, tSteps);
    }
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false, columnDefinition="bigint")
    private Long id;
    //int NOT NULL AUTO_INCREMENT,
    
 
    @Column(name = "tsId")
    private String tsId;
    
    @Column(name = "category")
    private String category;

	@Column(name = "tsDesc")
    private String tsDesc;

    @Column(name = "mId")
    private Integer mId;

    @Column(name = "module")
    private String module;

    @Column(name = "function")
    private String function;

    @Column(name = "tcId")
    private String tcId;

    @Column(name = "subId")
    private String subId;

    
    @Column(name = "tcType")
    private String tcType;

    @Column(name = "tcDesc")
    private String tcDesc;

    @Column(name = "tcResultDesc")
    private String tcResultDesc;
    
    
    @Column(name = "isAcceptCritera")
    private Boolean isAcceptCritera;

    @Column(name = "priority")
    private Integer priority;
    
    @Column(name = "PreReq")
    private String preReq;
    
    @Column(name = "tSteps")
    private String tSteps;
    
    @Column(name = "tBeforeRun")
    private String tBeforeRun;

    @Column(name = "tAfterRun")
    private String tAfterRun;

    @Column(name = "tSkip")
    private String tSkip;
    
    @Column(name = "version")
    private Integer version;

    
    //
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTsId() {
		return tsId;
	}
	public void setTsId(String tsId) {
		this.tsId = tsId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTsDesc() {
		return tsDesc;
	}
	public void setTsDesc(String tsDesc) {
		this.tsDesc = tsDesc;
	}
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getTcId() {
		return tcId;
	}
	public void setTcId(String tcId) {
		this.tcId = tcId;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getTcType() {
		return tcType;
	}
	public void setTcType(String tcType) {
		this.tcType = tcType;
	}
	public String getTcDesc() {
		return tcDesc;
	}
	public void setTcDesc(String tcDesc) {
		this.tcDesc = tcDesc;
	}
	public String getTcResultDesc() {
		return tcResultDesc;
	}
	public void setTcResultDesc(String tcResultDesc) {
		this.tcResultDesc = tcResultDesc;
	}
	public Boolean getIsAcceptCritera() {
		return isAcceptCritera;
	}
	public void setIsAcceptCritera(Boolean isAcceptCritera) {
		this.isAcceptCritera = isAcceptCritera;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getPreReq() {
		return preReq;
	}
	public void setPreReq(String preReq) {
		this.preReq = preReq;
	}
	public String gettSteps() {
		return tSteps;
	}
	public void settSteps(String tSteps) {
		this.tSteps = tSteps;
	}
	public String gettBeforeRun() {
		return tBeforeRun;
	}
	public void settBeforeRun(String tBeforeRun) {
		this.tBeforeRun = tBeforeRun;
	}
	public String gettAfterRun() {
		return tAfterRun;
	}
	public void settAfterRun(String tAfterRun) {
		this.tAfterRun = tAfterRun;
	}
	public String gettSkip() {
		return tSkip;
	}
	public void settSkip(String tSkip) {
		this.tSkip = tSkip;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
    
    
    //

    
    
    
    
    
}