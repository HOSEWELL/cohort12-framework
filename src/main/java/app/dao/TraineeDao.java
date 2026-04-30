package app.dao;

import app.model.Trainee;
import app.utility.bootstrap.InitBootstrap;
import app.utility.db.DataSourceHelper;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class TraineeDao extends GenericDao<Trainee, Integer> {

    @Inject
    public TraineeDao(@InitBootstrap DataSourceHelper ds) {
        super(Trainee.class);
        setDs(ds);
    }
}
