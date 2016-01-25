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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import eu.cloudscale.showcase.db.model.IAuthor;
import eu.cloudscale.showcase.db.model.ICcXacts;
import eu.cloudscale.showcase.db.model.ICountry;
import eu.cloudscale.showcase.db.model.IOrders;

/**
 * CcXacts generated by hbm2java
 */
@Entity
@Table( name = "cc_xacts", catalog = "tpcw" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CcXacts implements ICcXacts
{

	private ICountry country;

	private IOrders  orders;

	private Integer  id;

	private String   cxType;

	private Integer  cxNum;

	private String   cxName;

	private Date     cxExpiry;

	private String  cxAuthId;

	private Double   cxXactAmt;

	private Date     cxXactDate;

	@Id
	@GeneratedValue( strategy = IDENTITY )
	@Column( name = "CX_ID", unique = true, nullable = false )
	@Override
	public Integer getId()
	{
		return this.id;
	}

	@Override
	public void setId(Integer string)
	{
		this.id = string;
	}

	@ManyToOne( targetEntity=Orders.class, fetch = FetchType.LAZY )
	@JoinColumn( name = "CX_O_ID", nullable = false )
	@Override
	public IOrders getOrders()
	{
		return this.orders;
	}

	@Override
	public void setOrders(IOrders orders)
	{
		this.orders = orders;
	}

	@Column( name = "CX_TYPE", length = 10 )
	@Override
	public String getCxType()
	{
		return this.cxType;
	}

	@Override
	public void setCxType(String cxType)
	{
		this.cxType = cxType;
	}

	@Column( name = "CX_NUM" )
	@Override
	public Integer getCxNum()
	{
		return this.cxNum;
	}

	@Override
	public void setCxNum(Integer cxNum)
	{
		this.cxNum = cxNum;
	}

	@Column( name = "CX_NAME", length = 31 )
	@Override
	public String getCxName()
	{
		return this.cxName;
	}

	@Override
	public void setCxName(String cxName)
	{
		this.cxName = cxName;
	}

	@Column( name = "CX_EXPIRY", length = 10 )
	@Override
	public Date getCxExpiry()
	{
		return this.cxExpiry;
	}

	@Override
	public void setCxExpiry(Date cxExpiry)
	{
		this.cxExpiry = cxExpiry;
	}

	@Column( name = "CX_AUTH_ID", length = 15 )
//	@OneToOne( targetEntity=Author.class, fetch=FetchType.EAGER)
//	@JoinColumn( name="CX_AUTH_ID" )
	@Override
	public String getCxAuthId()
	{
		return this.cxAuthId;
	}

	@Override
	public void setCxAuthId(String cxAuthId)
	{
		this.cxAuthId = cxAuthId;
	}

	@Column( name = "CX_XACT_AMT", precision = 15 )
	@Override
	public Double getCxXactAmt()
	{
		return this.cxXactAmt;
	}

	@Override
	public void setCxXactAmt(Double o_TOTAL)
	{
		this.cxXactAmt = o_TOTAL;
	}

	@Column( name = "CX_XACT_DATE", length = 19 )
	@Override
	public Date getCxXactDate()
	{
		return this.cxXactDate;
	}

	@Override
	public void setCxXactDate(Date cxXactDate)
	{
		this.cxXactDate = cxXactDate;
	}

	@ManyToOne( targetEntity=Country.class, fetch = FetchType.LAZY )
	@JoinColumn( name = "CX_CO_ID", nullable = false)
	@Override
	public ICountry getCountry()
	{
		return this.country;
	}

	@Override
	public void setCountry(ICountry country)
	{
		this.country = country;
	}
}
