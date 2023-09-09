package integrador1Arqui.factory;

import integrador1Arqui.clases.Cliente;
import integrador1Arqui.clases.Factura;
import integrador1Arqui.clases.FacturaProducto;
import integrador1Arqui.clases.Producto;
import integrador1Arqui.interfaces.DAO;

public abstract class AbstractDAOFactory {
	public static final int MYSQL_JDBC = 1;
	
	public abstract DAO<Producto> getProductoDAO();

	public abstract DAO<FacturaProducto> getFacturaProductoDAO();
	
	public abstract DAO<Factura> getFacturaDAO();
	
	public abstract DAO<Cliente> getClienteDAO();

	public static AbstractDAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL_JDBC:
			return MySqlJDBCDAOFactory.getInstance();
		// case DERBY_JDBC: return new DerbyJDBCDAOFactory();
		// case JPA_HIBERNATE: …
		default:
			return null;
		}
	}
}
