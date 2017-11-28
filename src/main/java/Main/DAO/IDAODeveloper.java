package Main.DAO;

import Main.Entity.Developers;
import Main.Utils;

public interface IDAODeveloper extends IDAO <Developers,Long>{

    public void create(Developers developer);

    public void delete(Developers developer);

    public void update(Developers developer);

    public Developers read(Long id);

    public default boolean exists(Developers developers){
        return Utils.getSession().get(Developers.class,new Long (developers.getId())) != null;
    }
}
