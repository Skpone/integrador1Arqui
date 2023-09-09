package integrador1Arqui.DAO;

import java.sql.Connection;
import java.util.List;

import integrador1Arqui.clases.Producto;
import integrador1Arqui.interfaces.DAO;

public class MySqlProductoDAO implements DAO<Producto> {
	private Connection connection;
	
	public MySqlProductoDAO(Connection connection) {
		this.connection = connection;
	}

	
	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Producto get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		//USAR IF NOT EXISTS
	}

}
