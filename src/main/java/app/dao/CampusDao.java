package app.dao;

import app.model.Campus;
import app.model.Course;
import app.utility.bootstrap.InitBootstrap;
import app.utility.db.DataSourceHelper;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class CampusDao extends GenericDao<Campus, Integer> {

    @Inject
    public CampusDao(@InitBootstrap DataSourceHelper ds) {
        super(Campus.class);
        setDs(ds);
    }
}
