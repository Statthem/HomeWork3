package Main.DAO.Interfaces;

import Main.Entity.Developers;

public interface IDevelopersDAO extends IDAO <Developers>{

    public void create(Developers developer);

    public void delete(Developers developer);

    public void update(Developers developer);

    public Developers read(Developers developer);

    public long exists(Developers developers);
}
