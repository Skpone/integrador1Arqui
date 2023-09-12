package integrador1Arqui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import integrador1Arqui.DAO.MySqlClienteDAO;
import integrador1Arqui.DAO.MySqlFacturaDAO;
import integrador1Arqui.DAO.MySqlFacturaProductoDAO;
import integrador1Arqui.DAO.MySqlProductoDAO;
import integrador1Arqui.clases.Cliente;
import integrador1Arqui.clases.Factura;
import integrador1Arqui.clases.FacturaProducto;
import integrador1Arqui.clases.Producto;
import integrador1Arqui.factory.AbstractDAOFactory;
import integrador1Arqui.factory.MySqlJDBCDAOFactory;
import integrador1Arqui.interfaces.DAO;

public class main {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		// usando el abstract factory obtenermos el mySQL factory
		AbstractDAOFactory mysqlFactory = AbstractDAOFactory.getDAOFactory(1);

		// obtenemos los daos de la sql factory, se crean las tablas si no existen
		MySqlProductoDAO sqlProductoDAO = ((MySqlProductoDAO) mysqlFactory.getProductoDAO());
		MySqlClienteDAO sqlClienteDAO = ((MySqlClienteDAO) mysqlFactory.getClienteDAO());
		MySqlFacturaProductoDAO sqlFacturaProductoDAO = ((MySqlFacturaProductoDAO) mysqlFactory.getFacturaProductoDAO());
		MySqlFacturaDAO sqlFacturaDAO = ((MySqlFacturaDAO) mysqlFactory.getFacturaDAO());

		///////////////////////// INSERCIONES//////////////////////////////
		// insertamos masivamente en la tabla cliente
		// COMENTADO SI YA SE REALIZO UNA VEZ LA INSERCION MASIVA
		/*
		Iterator<Cliente> itClientes = obtenerClientes().iterator();
		while (itClientes.hasNext()) {
			Cliente cliente = itClientes.next();
			sqlClienteDAO.insert(cliente);
		}
		// insertamos masivamente en la tabla producto
		Iterator<Producto> itProductos = obtenerProductos().iterator();
		while (itProductos.hasNext()) {
			Producto producto = itProductos.next();
			sqlProductoDAO.insert(producto);
		}
		// insertamos masivamente en la tabla factura
		Iterator<Factura> itFacturas = obtenerFacturas().iterator();
		while (itFacturas.hasNext()) {
			Factura factura = itFacturas.next();
			sqlFacturaDAO.insert(factura);
		}
		// insertamos masivamente en la tabla facturaProducto
		Iterator<FacturaProducto> itFacturasProductos = obtenerFacturasProductos().iterator();
		while (itFacturasProductos.hasNext()) {
			FacturaProducto facturaProducto = itFacturasProductos.next();
			sqlFacturaProductoDAO.insert(facturaProducto);
		}*/
		///////////////////////// INSERCIONES//////////////////////////////
		
		Producto productoMasRecaudo = sqlFacturaProductoDAO.getProductoQueMasRecaudo();
		List<Cliente> clientesMasFacturaron = sqlClienteDAO.obtenerClientesMasFacturaron();

		System.out.println("CLIENTES MAS FACTURARON:");
		for (Cliente cliente : clientesMasFacturaron) {
			System.out.println(cliente);
		}
		
		System.out.println("PRODUCTO QUE MAS RECAUDO:");
		System.out.println(productoMasRecaudo);
	}

	public static List<Producto> obtenerProductos() throws FileNotFoundException, IOException {
		List<Producto> lista = new ArrayList<>();
		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader("src/integrador1Arqui/datasets/productos.csv"));
		for (CSVRecord row : parser) {
			Producto producto = new Producto(Integer.parseInt(row.get("idProducto")), row.get("nombre"),
					Float.parseFloat(row.get("valor")));
			lista.add(producto);
		}
		return lista;
	}

	public static List<Factura> obtenerFacturas() throws FileNotFoundException, IOException {
		List<Factura> lista = new ArrayList<>();
		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader("src/integrador1Arqui/datasets/facturas.csv"));
		for (CSVRecord row : parser) {
			Factura factura = new Factura(Integer.parseInt(row.get("idFactura")),
					Integer.parseInt(row.get("idCliente")));
			lista.add(factura);
		}
		return lista;
	}

	public static List<FacturaProducto> obtenerFacturasProductos() throws FileNotFoundException, IOException {
		List<FacturaProducto> lista = new ArrayList<>();
		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader("src/integrador1Arqui/datasets/facturas-productos.csv"));
		for (CSVRecord row : parser) {
			FacturaProducto facturaProducto = new FacturaProducto(Integer.parseInt(row.get("idFactura")),
					Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("cantidad")));
			lista.add(facturaProducto);
		}
		return lista;
	}

	public static List<Cliente> obtenerClientes() throws FileNotFoundException, IOException {
		List<Cliente> lista = new ArrayList<>();
		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader("src/integrador1Arqui/datasets/clientes.csv"));
		for (CSVRecord row : parser) {
			Cliente cliente = new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
			lista.add(cliente);
		}
		return lista;
	}
}
