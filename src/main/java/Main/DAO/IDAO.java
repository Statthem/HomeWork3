package Main.DAO;

import java.io.Serializable;

public interface IDAO<T,ID extends Serializable>{

    public void create(T entity);

    public void delete(T entity);

    public void update(T entity);

    public T read(ID id);

}
