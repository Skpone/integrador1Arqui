package integrador1Arqui.clases;

public class Producto {
	private int id;
	private String nombre;
	private float valor;
	
	public Producto(int id, String nombre, float valor) {
		this.id = id;
		this.nombre = nombre;
		this.valor = valor;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		String string = this.getId()+ ", " + this.getNombre() + ", " + this.getValor();
		return string;
	}
	
}
