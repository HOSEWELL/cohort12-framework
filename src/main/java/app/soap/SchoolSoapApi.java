package app.soap;

import app.bean.SchoolBean;
import app.model.School;
import app.rest.ResponseStatus;
import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public class SchoolSoapApi {

    @EJB
    private SchoolBean schoolBean;

    @WebMethod
    public ResponseStatus save(@WebParam(name = "school") School school){
        schoolBean.save(school);

        return new ResponseStatus();
    }

    @WebMethod
    @WebResult(name = "schools")
    public SchoolWrapper list(){
        return new SchoolWrapper(schoolBean.list(new School()));
    }

    @WebMethod
    public String displayMessage(
        @WebParam(name = "message") String message){
        return "The message is " + message;
    }

}
