package integrador1Arqui.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integrador1Arqui.clases.Cliente;
import integrador1Arqui.clases.Producto;
import integrador1Arqui.interfaces.DAO;

public class MySqlClienteDAO implements DAO<Cliente> {
	private Connection connection;

	public MySqlClienteDAO(Connection connection) {
		this.connection = connection;
		this.createTable();
	}

	@Override
	public void insert(Cliente cliente) {
		try {
			String query = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, cliente.getId());
			ps.setString(2, cliente.getNombre());
			ps.setString(3, cliente.getEmail());

			ps.executeUpdate();
			ps.close();

			this.connection.commit();
		} catch (SQLException error) {
			error.printStackTrace();
		}
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
	}

	@Override
	public boolean update(Cliente cliente) {
		try {
			String query = "UPDATE cliente SET nombre = ?, email = ? WHERE idCliente = ?";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getEmail());
			ps.setInt(3, cliente.getId());

			ps.executeUpdate();
			ps.close();

			return true;
		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}
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

	public List<Cliente> obtenerClientesMasFacturaron() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			String query = "SELECT c.idCliente, c.nombre, c.email, ( " + "SELECT SUM(fp.cantidad * pr.valor) "
					+ "FROM factura_producto AS fp " + "INNER JOIN producto AS pr ON fp.idProducto = pr.idProducto "
					+ "WHERE fp.idFactura IN (" + "SELECT idFactura " + "FROM factura AS f "
					+ "WHERE f.idCliente = c.idCliente )" + ") AS total_gastado " + "FROM cliente AS c "
					+ "ORDER BY total_gastado DESC;";

			PreparedStatement ps = this.connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
				clientes.add(cliente);
			}
		} catch (SQLException error) {
			error.printStackTrace();
			return null;
		}
		return clientes;
	}
}
