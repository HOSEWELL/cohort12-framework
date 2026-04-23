package app.action;

import app.model.Person;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/person_lists")
public class ListPerson extends BaseListAction<Person> {
}
