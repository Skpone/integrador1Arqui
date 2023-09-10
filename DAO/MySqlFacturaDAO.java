package integrador1Arqui.DAO;

import java.sql.Connection;
import java.util.List;

import integrador1Arqui.clases.Factura;
import integrador1Arqui.interfaces.DAO;

public class MySqlFacturaDAO implements DAO<Factura> {
	private Connection connection;
	
	public MySqlFacturaDAO(Connection connection) {
		this.connection = connection;
	}

	
	@Override
	public void insert(int idFactura, int idCliente) throws SQLException {
		String query = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";
		PreparedStatement ps = this.connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.setInt(2, idCliente);

		ps.executeUpdate();
		ps.close();

		this.connection.commit();
	}

	@Override
	public boolean delete(int id) {
		try {
			String query = "DELETE FROM factura WHERE idFactura = ?";
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
	public boolean update(int idFactura, int idCliente) {
		try {
			String query = "UPDATE factura SET idCliente = ? WHERE idFactura = ?";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, idCliente);
			ps.setInt(2, idFactura);

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
	public Factura get(int id) {
		try {
			String query = "SELECT * FROM factura WHERE idFactura = ?";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Factura(rs.getInt(1), rs.getInt(2));
			} else {
				return null;
			}
		} catch (SQLException error) {
			error.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Factura> getAll() {
		ArrayList<Factura> lista = new ArrayList<>();

		try {
			String query = "SELECT * FROM factura";
			PreparedStatement ps = this.connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(new Factura(rs.getInt(1), rs.getInt(2)));
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}

		return lista;
	}

	@Override
	public void createTable() {
		try {
			String query = "CREATE TABLE IF NOT EXISTS factura (idFactura INT, idCliente INT, PRIMARY KEY (idFactura), FOREIGN KEY (idCliente) REFERENCES cliente (idCliente))";

			this.connection.prepareStatement(query).execute();
			this.connection.commit();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}

}
