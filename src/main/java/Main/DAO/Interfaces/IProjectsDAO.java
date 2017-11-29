package Main.DAO.Interfaces;

import Main.Entity.Projects;
import Main.Utils;

public interface IProjectsDAO extends IDAO<Projects> {

    public void create(Projects projects);

    public void delete(Projects projects);

    public void update(Projects projects);

    public Projects read(Long id);

    public default boolean exists(Projects projects) {
        return Utils.getSession().get(Projects.class, new Long(projects.getId())) != null;
    }
}