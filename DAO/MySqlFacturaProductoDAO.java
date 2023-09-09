package integrador1Arqui.DAO;

import java.util.List;

import integrador1Arqui.clases.FacturaProducto;
import integrador1Arqui.interfaces.DAO;

public class MySqlFacturaProductoDAO implements DAO<FacturaProducto> {
	
	public MySqlFacturaProductoDAO() {
		
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
	public FacturaProducto get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacturaProducto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		//USAR IF NOT EXISTS
	}

}
