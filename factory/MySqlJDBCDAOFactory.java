package integrador1Arqui.factory;

import integrador1Arqui.clases.Cliente;
import integrador1Arqui.clases.Factura;
import integrador1Arqui.clases.FacturaProducto;
import integrador1Arqui.clases.Producto;
import integrador1Arqui.interfaces.DAO;

public class MySqlJDBCDAOFactory extends AbstractDAOFactory {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURI = "jdbc:mysql://localhost:3306/mydb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	private MySqlJDBCDAOFactory() {
		//hacer
	}

	public static MySqlJDBCDAOFactory getInstance() {
		//if(this.) //HACER
		return new MySqlJDBCDAOFactory();
	}

	@Override
	public DAO<Producto> getProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAO<FacturaProducto> getFacturaProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAO<Factura> getFacturaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAO<Cliente> getClienteDAO() {
		// TODO Auto-generated method stub
		return null;
	}
}
