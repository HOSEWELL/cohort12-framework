package app.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Courses {

    public String list(){
        List<String> courseList = new ArrayList<>();
        courseList.add("Java Development");
        courseList.add("Quality Assurance");
        courseList.add("Infrastructure");
        courseList.add("Networking");
        courseList.add("Database Admin");
        courseList.add("System Admin");
        courseList.add("Accounting and Finance");
        courseList.add("Pension");
        courseList.add("System Security");

        return courseList.stream()
            .map(course -> "<li>" + course + "</li>")
            .collect(Collectors.joining());
    }
}
