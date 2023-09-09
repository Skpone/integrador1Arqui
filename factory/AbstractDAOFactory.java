package integrador1Arqui.factory;

import integrador1Arqui.interfaces.DAO;

public abstract class AbstractDAOFactory {
	public static final int MYSQL_JDBC = 1;
	
	public abstract DAO getProductoDAO();

	public abstract DAO getFacturaProductoDAO();
	
	public abstract DAO getFacturaDAO();
	
	public abstract DAO getCustomerDAO();

	public static AbstractDAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL_JDBC:
			return new MySqlJDBCDAOFactory();
		// case DERBY_JDBC: return new DerbyJDBCDAOFactory();
		// case JPA_HIBERNATE: …
		default:
			return null;
		}
	}
}
