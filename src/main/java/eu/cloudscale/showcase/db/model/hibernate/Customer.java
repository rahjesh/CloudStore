/*******************************************************************************
*  Copyright (c) 2015 XLAB d.o.o.
*  All rights reserved. This program and the accompanying materials
*  are made available under the terms of the Eclipse Public License v1.0
*  which accompanies this distribution, and is available at
*  http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package eu.cloudscale.showcase.db.model.hibernate;

// Generated May 16, 2013 3:07:18 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import eu.cloudscale.showcase.db.model.IAddress;
import eu.cloudscale.showcase.db.model.ICustomer;
import eu.cloudscale.showcase.db.model.IOrders;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table( name = "customer", catalog = "tpcw" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Customer implements ICustomer
{

	private Integer      CId;

	private IAddress    address;

	private String      CUname;

	private String      CPasswd;

	private String      CFname;

	private String      CLname;

	private String      CPhone;

	private String      CEmail;

	private Date        CSince;

	private Date        CLastVisit;

	private Date        CLogin;

	private Date        CExpiration;

	private Double      CDiscount;

	private Double      CBalance;

	private Double      CYtdPmt;

	private Date        CBirthdate;

	private String      CData;

	private Set<IOrders> orderses = new HashSet<IOrders>( 0 );

	public Customer()
	{
	}

	@Id
	@GeneratedValue( strategy = IDENTITY )
	@Column( name = "C_ID", unique = true, nullable = false )
	public Integer getCId()
	{
		return this.CId;
	}

	public void setCId(Integer CId)
	{
		this.CId = CId;
	}

	@ManyToOne( targetEntity=Address.class, fetch = FetchType.EAGER)
	@JoinColumn( name = "C_ADDR_ID", nullable = false )
	public IAddress getAddress()
	{
		return this.address;
	}

	public void setAddress(IAddress address)
	{
		this.address = address;
	}

	@Column( name = "C_UNAME", length = 20 )
	@Index(name = "C_UNAME")
	public String getCUname()
	{
		return this.CUname;
	}

	public void setCUname(String CUname)
	{
		this.CUname = CUname;
	}

	@Column( name = "C_PASSWD", length = 20 )
	@Index(name="C_PASSWD")
	public String getCPasswd()
	{
		return this.CPasswd;
	}

	public void setCPasswd(String CPasswd)
	{
		this.CPasswd = CPasswd;
	}

	@Column( name = "C_FNAME", length = 15 )
	public String getCFname()
	{
		return this.CFname;
	}

	public void setCFname(String CFname)
	{
		this.CFname = CFname;
	}

	@Column( name = "C_LNAME", length = 15 )
	public String getCLname()
	{
		return this.CLname;
	}

	public void setCLname(String CLname)
	{
		this.CLname = CLname;
	}

	@Column( name = "C_PHONE", length = 16 )
	public String getCPhone()
	{
		return this.CPhone;
	}

	public void setCPhone(String CPhone)
	{
		this.CPhone = CPhone;
	}

	@Column( name = "C_EMAIL", length = 50 )
	public String getCEmail()
	{
		return this.CEmail;
	}

	public void setCEmail(String CEmail)
	{
		this.CEmail = CEmail;
	}

	@Temporal( TemporalType.DATE )
	@Column( name = "C_SINCE", length = 10 )
	public Date getCSince()
	{
		return this.CSince;
	}

	public void setCSince(Date CSince)
	{
		this.CSince = CSince;
	}

	@Temporal( TemporalType.DATE )
	@Column( name = "C_LAST_VISIT", length = 10 )
	public Date getCLastVisit()
	{
		return this.CLastVisit;
	}

	public void setCLastVisit(Date CLastVisit)
	{
		this.CLastVisit = CLastVisit;
	}

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "C_LOGIN", length = 19 )
	public Date getCLogin()
	{
		return this.CLogin;
	}

	public void setCLogin(Date CLogin)
	{
		this.CLogin = CLogin;
	}

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "C_EXPIRATION", length = 19 )
	public Date getCExpiration()
	{
		return this.CExpiration;
	}

	public void setCExpiration(Date CExpiration)
	{
		this.CExpiration = CExpiration;
	}

	@Column( name = "C_DISCOUNT" )
	public Double getCDiscount()
	{
		return this.CDiscount;
	}

	public void setCDiscount(double c_DISCOUNT)
	{
		this.CDiscount = c_DISCOUNT;
	}

	@Column( name = "C_BALANCE" )
	public Double getCBalance()
	{
		return this.CBalance;
	}

	public void setCBalance(Double c_BALANCE)
	{
		this.CBalance = c_BALANCE;
	}

	@Column( name = "C_YTD_PMT" )
	public Double getCYtdPmt()
	{
		return this.CYtdPmt;
	}

	public void setCYtdPmt(Double c_YTD_PMT)
	{
		this.CYtdPmt = c_YTD_PMT;
	}

	@Temporal( TemporalType.DATE )
	@Column( name = "C_BIRTHDATE", length = 10 )
	public Date getCBirthdate()
	{
		return this.CBirthdate;
	}

	public void setCBirthdate(Date CBirthdate)
	{
		this.CBirthdate = CBirthdate;
	}

	@Column( name = "C_DATA", length = 65535 )
	public String getCData()
	{
		return this.CData;
	}

	public void setCData(String CData)
	{
		this.CData = CData;
	}

	@OneToMany( targetEntity=Orders.class, fetch = FetchType.LAZY, mappedBy = "customer" )
	public Set<IOrders> getOrderses()
	{
		return this.orderses;
	}

	public void setOrderses(Set<IOrders> orderses)
	{
		this.orderses = orderses;
	}

}
