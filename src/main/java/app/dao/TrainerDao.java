package app.dao;

import app.model.Trainer;
import app.utility.bootstrap.InitBootstrap;
import app.utility.db.DataSourceHelper;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class TrainerDao extends GenericDao<Trainer, Integer> {

    @Inject
    public TrainerDao(@InitBootstrap DataSourceHelper ds) {
        super(Trainer.class);
        setDs(ds);
    }
}
