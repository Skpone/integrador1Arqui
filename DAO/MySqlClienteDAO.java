package integrador1Arqui.DAO;
import java.util.List;

import integrador1Arqui.clases.Cliente;
import integrador1Arqui.interfaces.DAO;

public class MySqlClienteDAO implements DAO<Cliente> {
	
	public MySqlClienteDAO() {
		
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
	public Cliente get(int id) {
		//consulta sql agarra cliente
		//agarras los atributos de las columnas
		//creas objeto cliente
		//usas los sets para ponerselo al objeto
		return null;
	}

	@Override
	public List<Cliente> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		//USAR IF NOT EXISTS
	}

}
