package integrador1Arqui.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integrador1Arqui.clases.FacturaProducto;
import integrador1Arqui.interfaces.DAO;

public class MySqlFacturaProductoDAO implements DAO<FacturaProducto> {
	private Connection connection;

	public MySqlFacturaProductoDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(FacturaProducto facturaProducto) {
		try {
			String query = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, facturaProducto.getIdFactura());
			ps.setInt(2, facturaProducto.getIdProducto());
			ps.setInt(3, facturaProducto.getCantidad());

			ps.executeUpdate();
			ps.close();

			this.connection.commit();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}

	@Override
	public FacturaProducto get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacturaProducto> getAll() {
		ArrayList<FacturaProducto> lista = new ArrayList<>();

		try {
			String query = "SELECT * FROM factura_producto";
			PreparedStatement ps = this.connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(new FacturaProducto(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}

		return lista;
	}

	@Override
	public void createTable() {
		try {
			String query = "CREATE TABLE IF NOT EXISTS factura_producto (idFactura INT, idProducto INT, cantidad INT)";

			this.connection.prepareStatement(query).execute();
			this.connection.commit();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(FacturaProducto objeto) {
		// TODO Auto-generated method stub
		return false;
	}

}
