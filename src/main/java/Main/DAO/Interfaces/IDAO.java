package Main.DAO.Interfaces;

import java.io.Serializable;

public interface IDAO<T>{

    public void create(T entity);

    public void delete(T entity);

    public void update(T entity);

    public T read(T entity);

}
