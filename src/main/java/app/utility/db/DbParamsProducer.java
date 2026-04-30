package app.utility.db;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

@ApplicationScoped
public class DbParamsProducer {

    @Named("dbParamHost")
    @Produces
    private String dbParamHost = "localhost";

    @Named("dbParamPort")
    @Produces
    private int dbParamPort = 3306;

    @Named("dbParamName")
    @Produces
    private String dbParamName = "training_app";

    @Named("dbParamUser")
    @Produces
    private String dbParamUser = "root";

    @Named("dbParamPwd")
    @Produces
    private String dbParamPwd = "";
}
