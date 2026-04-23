package app.action;

import app.model.Person;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Register Person",
    urlPatterns = { "/register_person" },
    initParams = {
        @WebInitParam(name = "pageName", value = "Register - Training Academy"),
        @WebInitParam(name = "pageHeader", value = "Training Registration - IT")
    })
public class RegisterPerson extends BaseAction<Person> {

}
