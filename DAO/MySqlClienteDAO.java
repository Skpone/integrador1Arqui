package integrador1Arqui.DAO;
import java.sql.Connection;
import java.util.List;

import integrador1Arqui.clases.Cliente;
import integrador1Arqui.interfaces.DAO;

public class MySqlClienteDAO implements DAO<Cliente> {
	private Connection connection;
	
	
	public MySqlClienteDAO(Connection connection) {
		this.connection = connection;
	}
	
	
	@Override
	public void insert(int id, String nombre, String email) throws SQLException {
		String query = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
		PreparedStatement ps = this.connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.setString(2, nombre);
		ps.setString(3, email);

		ps.executeUpdate();
		ps.close();

		this.connection.commit();
	}

	@Override
	public boolean delete(int id) {
		try {
			String query = "DELETE FROM cliente WHERE idCliente = ?";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, id);

			ps.executeUpdate();
			ps.close();

			return true;
		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}

		return false;
	}

	@Override
	public boolean update(int id, String nombre, String email) {
		try {
			String query = "UPDATE cliente SET nombre = ?, email = ? WHERE idCliente = ?";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, nombre);
			ps.setString(2, email);
			ps.setInt(3, id);

			ps.executeUpdate();
			ps.close();

			return true;
		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}

		return false;
	}

	@Override
	public Cliente get(int id) {
		try {
			String query = "SELECT * FROM cliente WHERE idCliente = ?";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
			} else {
				return null;
			}
		} catch (SQLException error) {
			error.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Cliente> getAll() {
		ArrayList<Cliente> lista = new ArrayList<>();

		try {
			String query = "SELECT * FROM cliente";
			PreparedStatement ps = this.connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}

		return lista;
	}

	@Override
	public void createTable() {
		try {
			String query = "CREATE TABLE IF NOT EXISTS cliente (idCliente INT, nombre VARCHAR(255), email VARCHAR(255), PRIMARY KEY (idCliente))";

			this.connection.prepareStatement(query).execute();
			this.connection.commit();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}

}
