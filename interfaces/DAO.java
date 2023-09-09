package integrador1Arqui.interfaces;

import java.util.List;

public interface DAO<T> {
	 public void insert();
	 public boolean delete(int id);
	 public boolean update(int id);
	 public T get(int id);
	 public List<T> getAll();
	 

}
