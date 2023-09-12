package integrador1Arqui.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integrador1Arqui.clases.Producto;
import integrador1Arqui.interfaces.DAO;

public class MySqlProductoDAO implements DAO<Producto> {
	private Connection connection;

	public MySqlProductoDAO(Connection connection) {
		this.connection = connection;
		this.createTable();
	}

	@Override
	public void insert(Producto producto) {
		try {
			String query = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, producto.getId());
			ps.setString(2, producto.getNombre());
			ps.setFloat(3, producto.getValor());

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
			String query = "DELETE FROM producto WHERE idProducto = ?";
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
	public boolean update(Producto producto) {
		try {
			String query = "UPDATE producto SET nombre = ?, valor = ? WHERE idProducto = ?";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, producto.getNombre());
			ps.setFloat(2, producto.getValor());
			ps.setInt(3, producto.getId());

			ps.executeUpdate();
			ps.close();

			return true;
		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}
	}

	@Override
	public Producto get(int id) {
		try {
			String query = "SELECT * FROM producto WHERE idProducto = ?";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
			} else {
				return null;
			}
		} catch (SQLException error) {
			error.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Producto> getAll() {
		ArrayList<Producto> lista = new ArrayList<>();

		try {
			String query = "SELECT * FROM producto";
			PreparedStatement ps = this.connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}

		return lista;
	}

	@Override
	public void createTable() {
		try {
			String query = "CREATE TABLE IF NOT EXISTS producto (idProducto INT, nombre VARCHAR(45), valor FLOAT, PRIMARY KEY (idProducto))";

			this.connection.prepareStatement(query).execute();
			this.connection.commit();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}

}
