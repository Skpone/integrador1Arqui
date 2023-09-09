package integrador1Arqui.clases;

public class Factura {
	private int id;
	private int idCliente;
	
	public Factura(int id, int idCliente) {
		this.id = id;
		this.idCliente = idCliente;
	}

	
	public int getClienteID() {
		return idCliente;
	}

	public void setClienteID(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getId() {
		return id;
	}
	
	
	
}
