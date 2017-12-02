package Main.DAO.Interfaces;

import Main.Entity.Projects;

public interface IProjectsDAO extends IDAO<Projects> {

    public void create(Projects projects);

    public void delete(Projects projects);

    public void update(Projects projects);

    public Projects read(Projects projects);

    public long exists(Projects projects);

}