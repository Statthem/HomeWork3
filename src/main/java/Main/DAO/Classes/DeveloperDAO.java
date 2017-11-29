package Main.DAO.Classes;

import Main.DAO.Interfaces.IDevelopersDAO;
import Main.Entity.Developers;

public class DeveloperDAO<Developers> implements IDevelopersDAO {
    @Override
    public void create(Main.Entity.Developers developer) {

    }

    @Override
    public void delete(Main.Entity.Developers developer) {

    }

    @Override
    public void update(Main.Entity.Developers developer) {

    }

    @Override
    public Main.Entity.Developers read(Main.Entity.Developers entity) {
        return null;
    }

    @Override
    public Main.Entity.Developers read(java.lang.Long id) {
        return null;
    }

    @Override
    public boolean exists(Main.Entity.Developers developers) {
        return false;
    }
}
