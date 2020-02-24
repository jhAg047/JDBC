package com.kh.model.vo;

import java.sql.Date;

public class Employee {
	/* - empNo : int 		// 사번
	   - empName : String	// 이름
	   - job : String		// 직책
	   - mgr : int			// 직속 상사(manager)
	   - hireDate : Date	// 고용일 (java.sql.Date)
	   - sal : int			// 급여
	   - comm : int			// 커미션(인센티브)
	   - deptNo :int		// 부서번호
	   
	   + 기본 생성자
	   + Employee(empNo:int, empname:String, job:String, mgr:int,hireDate:date, sal:int,comm:int,deptNo :int)
	   + Employee(empNo:int, empname:String, job:String, mgr:int,sal:int,comm:int,deptNo :int)
	   + Employee(empNo:int,job:String,sal:int,comm:int)
	   + Employee(job:String,sal:int,comm:int)
	   + getter / setter
	   + toString() : String
	   		반환 형식 : empNo / empName / job / mgr / hireDate / sal / comm / deptNo
	*/
	private int empNo;
	private String empName;
	private String job;
	private int mgr;
	private Date hireDate;
	private int sal;
	private int comm;
	private int deptNo;
	
	public Employee() {};
	public Employee(int empNo,String empName,String job,int mgr, Date hireDate,int sal,int comm,int deptNo) {
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.mgr = mgr;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
		this.deptNo = deptNo;
	}
	public Employee(int empNo,String empName,String job,int mgr,int sal,int comm,int deptNo) {
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.mgr = mgr;
		this.sal = sal;
		this.comm = comm;
		this.deptNo = deptNo;
	}
	public Employee(int empNo,String job,int sal,int comm) {
		this.empNo = empNo;
		this.job = job;
		this.sal = sal;
		this.comm = comm;
	}
	public Employee(String job,int sal,int comm) {
		this.job = job;
		this.sal = sal;
		this.comm = comm;
	}
	public int empNogetter() {
		return empNo;
	}
	public void empNosetter(int empNo) {
		this.empNo = empNo;
	}
	public String empNamegetter() {
		return empName;
	}
	public void empNamesetter(String empName) {
		this.empName = empName;
	}
	public String jobgetter() {
		return job;
	}
	public void jobsetter(String job) {
		this.job = job;
	}
	public int mgrgetter() {
		return mgr;
	}
	public void mgrsetter(int mgr) {
		this.mgr = mgr;
	}
	public Date hireDategetter() {
		return hireDate;
	}
	public void hireDatesetter(Date hireDate) {
		this.hireDate = hireDate;
	}
	public int salgetter() {
		return sal;
	}
	public void salsetter(int sal) {
		this.sal =sal;
	}
	public int commgetter() {
		return comm;
	}
	public void commsetter(int comm) {
		this.comm = comm;
	}
	public int deptNogetter() {
		return deptNo;
	}
	public void deptNosetter(int deptNo) {
		this.deptNo = deptNo;
	}
	@Override
	public String toString() {
		return empNo +" / "+ empName +" / "+ job +" / "+ mgr +" / "+ hireDate +" / "+sal +" / "+comm +" / "+deptNo;
	}
}
