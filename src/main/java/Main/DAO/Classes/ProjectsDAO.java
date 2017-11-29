package Main.DAO.Classes;

import Main.DAO.Interfaces.IProjectsDAO;
import Main.Entity.Projects;

public class ProjectsDAO<Projects> implements IProjectsDAO {
    @Override
    public void create(Main.Entity.Projects projects) {

    }

    @Override
    public void delete(Main.Entity.Projects projects) {

    }

    @Override
    public void update(Main.Entity.Projects projects) {

    }

    @Override
    public Main.Entity.Projects read(Main.Entity.Projects entity) {
        return null;
    }

    @Override
    public Main.Entity.Projects read(java.lang.Long id) {
        return null;
    }

    @Override
    public boolean exists(Main.Entity.Projects projects) {
        return false;
    }
}
