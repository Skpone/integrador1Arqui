package integrador1Arqui.factory;

public abstract class AbstractDAOFactory {
	public static final int MYSQL_JDBC = 1;
	
	public abstract ProductoDAO getProductoDAO();

	public abstract FacturaProductoDAO getFacturaProductoDAO();
	
	public abstract FacturaDAO getFacturaDAO();
	
	public abstract ClienteDAO getCustomerDAO();

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
