package eu.cloudscale.showcase.generate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eu.cloudscale.showcase.db.IService;
import eu.cloudscale.showcase.db.common.ContextHelper;
import eu.cloudscale.showcase.db.dao.IAddressDao;
import eu.cloudscale.showcase.db.dao.IAuthorDao;
import eu.cloudscale.showcase.db.dao.ICcXactsDao;
import eu.cloudscale.showcase.db.dao.ICountryDao;
import eu.cloudscale.showcase.db.dao.ICustomerDao;
import eu.cloudscale.showcase.db.dao.IItemDao;
import eu.cloudscale.showcase.db.dao.IOrderLineDao;
import eu.cloudscale.showcase.db.dao.IOrdersDao;
import eu.cloudscale.showcase.db.dao.hibernate.impl.AuthorDaoImpl;
import eu.cloudscale.showcase.db.model.ICcXacts;
import eu.cloudscale.showcase.db.model.IOrderLine;
import eu.cloudscale.showcase.db.model.hibernate.CcXacts;
import eu.cloudscale.showcase.db.model.hibernate.OrderLine;
import eu.cloudscale.showcase.db.model.hibernate.Orders;

@Component
@Transactional
public class GenerateHibernate extends AGenerate
{
	
	public GenerateHibernate()
	{
	}
	
	@Override
    public void populateOrdersAndCC_XACTSTable()
    {
		GregorianCalendar cal;
		String[] credit_cards = {"VISA", "MASTERCARD", "DISCOVER", "AMEX",
		        "DINERS" };
		int num_card_types = 5;
		String[] ship_types = {"AIR", "UPS", "FEDEX", "SHIP", "COURIER", "MAIL" };
		int num_ship_types = 6;

		String[] status_types = {"PROCESSING", "SHIPPED", "PENDING", "DENIED" };
		int num_status_types = 4;

		// Order variables
		int O_C_ID;
		java.sql.Timestamp O_DATE;
		double O_SUB_TOTAL;
		double O_TAX;
		double O_TOTAL;
		String O_SHIP_TYPE;
		java.sql.Timestamp O_SHIP_DATE;
		int O_BILL_ADDR_ID, O_SHIP_ADDR_ID;
		String O_STATUS;

		String CX_TYPE;
		int CX_NUM;
		String CX_NAME;
		java.sql.Date CX_EXPIRY;
		String CX_AUTH_ID;
		int CX_CO_ID;

		System.out.println( "Populating ORDERS, ORDER_LINES, CC_XACTS with "
		        + NUM_ORDERS + " orders" );

		System.out.print( "Complete (in 10,000's): " );

		ICustomerDao customerDao = super.service.getCustomerDaoImpl();
		IAddressDao addressDao = super.service.getAddressDaoImpl();
		IItemDao itemDao = super.service.getItemDaoImpl();
		ICountryDao countryDao = super.service.getCountryDaoImpl();
		IOrdersDao ordersDao = super.service.getOrdersDaoImpl();
		IOrderLineDao orderLineDao = super.service.getOrderLineDaoImpl();
		ICcXactsDao ccXactsDao = super.service.getCcXactsDaoImpl();
		IAuthorDao authorDao = super.service.getAuthorDaoImpl();

		for ( int i = 1; i <= NUM_ORDERS; i++ )
		{
			if ( i % 10000 == 0 )
				System.out.print( i / 10000 + " " );

			int num_items = getRandomInt( 1, 5 );
			O_C_ID = getRandomInt( 1, NUM_CUSTOMERS );
			cal = new GregorianCalendar();
			cal.add( Calendar.DAY_OF_YEAR, -1 * getRandomInt( 1, 60 ) );
			O_DATE = new java.sql.Timestamp( cal.getTime().getTime() );
			O_SUB_TOTAL = (double) getRandomInt( 1000, 999999 ) / 100;
			O_TAX = O_SUB_TOTAL * 0.0825;
			O_TOTAL = O_SUB_TOTAL + O_TAX + 3.00 + num_items;
			O_SHIP_TYPE = ship_types[getRandomInt( 0, num_ship_types - 1 )];
			cal.add( Calendar.DAY_OF_YEAR, getRandomInt( 0, 7 ) );
			O_SHIP_DATE = new java.sql.Timestamp( cal.getTime().getTime() );

			O_BILL_ADDR_ID = getRandomInt( 1, 2 * NUM_CUSTOMERS );
			O_SHIP_ADDR_ID = getRandomInt( 1, 2 * NUM_CUSTOMERS );
			O_STATUS = status_types[getRandomInt( 0, num_status_types - 1 )];

			Orders order = new Orders();

			// Set parameter
			order.setCustomer( customerDao.findById( O_C_ID ) );
			order.setODate( new Date( O_DATE.getTime() ) );
			order.setOSubTotal( O_SUB_TOTAL );
			order.setOTax( O_TAX );
			order.setOTotal( O_TOTAL );
			order.setOShipType( O_SHIP_TYPE );
			order.setOShipDate( O_SHIP_DATE );
			order.setAddressByOBillAddrId( addressDao.findById( O_BILL_ADDR_ID ) );
			order.setAddressByOShipAddrId( addressDao.findById( O_SHIP_ADDR_ID ) );
			order.setOStatus( O_STATUS );
			order.setCcXactses( new HashSet<ICcXacts>() );
			order.setOrderLines( new HashSet<IOrderLine>() );
			ordersDao.shrani( order );

			for ( int j = 1; j <= num_items; j++ )
			{
				int OL_ID = j;
				int OL_O_ID = i;
				int OL_I_ID = getRandomInt( 1, NUM_ITEMS );
				int OL_QTY = getRandomInt( 1, 300 );
				double OL_DISCOUNT = (double) getRandomInt( 0, 30 ) / 100;
				String OL_COMMENTS = getRandomAString( 20, 100 );
				
				OrderLine orderLine = new OrderLine();
				orderLine.setItem( itemDao.findById( OL_I_ID ) );
				orderLine.setOlQty( OL_QTY );
				orderLine.setOlDiscount( OL_DISCOUNT );
				orderLine.setOlComment( OL_COMMENTS );
				orderLine.setOrders( order );

				orderLineDao.shrani( orderLine );
				order.getOrderLines().add( orderLine );

			}

			CX_TYPE = credit_cards[getRandomInt( 0, num_card_types - 1 )];
			CX_NUM = getRandomNString( 16 );
			CX_NAME = getRandomAString( 14, 30 );
			cal = new GregorianCalendar();
			cal.add( Calendar.DAY_OF_YEAR, getRandomInt( 10, 730 ) );
			CX_EXPIRY = new java.sql.Date( cal.getTime().getTime() );
			CX_AUTH_ID = getRandomAString( 15 );
			CX_CO_ID = getRandomInt( 1, 92 );

			CcXacts ccXacts = new CcXacts();

			ccXacts.setCountry( countryDao.findById( CX_CO_ID ) );
			ccXacts.setOrders( order );
			ccXacts.setCxType( CX_TYPE );
			ccXacts.setCxNum( CX_NUM );
			ccXacts.setCxName( CX_NAME );
			ccXacts.setCxExpiry( CX_EXPIRY );
			ccXacts.setCxAuthId( String.valueOf(CX_AUTH_ID) );
			ccXacts.setCxXactAmt( O_TOTAL );
			ccXacts.setCxXactDate( O_SHIP_DATE );

			ccXacts.setOrders( order );

			ccXacts.setCountry( countryDao.findById( CX_CO_ID ) );

			order.getCcXactses().add( ccXacts );

			ccXactsDao.shrani( ccXacts );

		}

		System.out.println( "" );
    }

	@Override
	public void dropTables(String[] tables) 
	{
		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		
		Session session = sf.openSession();
		Query q = session.createSQLQuery("SET FOREIGN_KEY_CHECKS=0");
		q.executeUpdate();
		
		for(String table : tables)
		{
			// Ugly hack so that TRUNCATE TABLE works
			q = session.createSQLQuery("CREATE TABLE  IF NOT EXISTS " + table + " (id int)");
			q.executeUpdate();
			
			q = session.createSQLQuery("TRUNCATE TABLE " + table);
			q.executeUpdate();
			
//			q = session.createSQLQuery("DROP TABLE IF EXISTS " + table);
//			q.executeUpdate();
//			System.out.println("DROPPED TABLE " + table);
		}
		
		q = session.createSQLQuery("SET FOREIGN_KEY_CHECKS=1");
		q.executeUpdate();
		
		session.close();
//		sf.close();
//		sf = null;
	}
}
