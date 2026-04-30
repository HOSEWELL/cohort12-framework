package app.dao;

import app.model.School;
import app.utility.bootstrap.InitBootstrap;
import app.utility.db.DataSourceHelper;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class SchoolDao extends GenericDao<School, Integer> {

    @Inject
    public SchoolDao(@InitBootstrap DataSourceHelper ds) {
        super(School.class);
        setDs(ds);
    }
}
