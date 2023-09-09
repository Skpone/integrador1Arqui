package integrador1Arqui.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import integrador1Arqui.DAO.MySqlClienteDAO;
import integrador1Arqui.DAO.MySqlFacturaDAO;
import integrador1Arqui.DAO.MySqlFacturaProductoDAO;
import integrador1Arqui.DAO.MySqlProductoDAO;
import integrador1Arqui.clases.Cliente;
import integrador1Arqui.clases.Factura;
import integrador1Arqui.clases.FacturaProducto;
import integrador1Arqui.clases.Producto;
import integrador1Arqui.interfaces.DAO;

public class MySqlJDBCDAOFactory extends AbstractDAOFactory {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURI = "jdbc:mysql://localhost:3306/integrador1";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static MySqlJDBCDAOFactory INSTANCE;
	
	private Connection connection;

	private MySqlJDBCDAOFactory() throws SQLException {
		try {
			// sintaxis para hacer el import de acuerdo al DRIVER
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			// salir de la execución con un "1" = estado error
			System.exit(1);
		}
		openConnection();
	}
	
	public void openConnection() throws SQLException {
		connection = DriverManager.getConnection(DBURI, USERNAME, PASSWORD);
		connection.setAutoCommit(false);
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}

	public static MySqlJDBCDAOFactory getInstance() throws SQLException {
		if (INSTANCE == null) {
			return new MySqlJDBCDAOFactory();
		}
		return INSTANCE;
	}

	@Override
	public DAO<Producto> getProductoDAO() {
		return new MySqlProductoDAO(connection);
	}

	@Override
	public DAO<FacturaProducto> getFacturaProductoDAO() {
		return new MySqlFacturaProductoDAO(connection);
	}

	@Override
	public DAO<Factura> getFacturaDAO() {
		return new MySqlFacturaDAO(connection);
	}

	@Override
	public DAO<Cliente> getClienteDAO() {
		return new MySqlClienteDAO(connection);
	}
}
