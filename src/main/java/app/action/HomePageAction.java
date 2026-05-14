package app.action;

import app.framework.Action;
import app.framework.ActionGetMethod;
import app.framework.ActionResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Action(value = "/home", showLink = false)
public class HomePageAction {

    @ActionGetMethod("index")
    public ActionResponse index() {
        StringBuilder html = new StringBuilder();

        /* Hero */
        html.append("<div class='hero'>");
        html.append("<h1>Empowering Learning Through Practical Training</h1>");
        html.append("<p>Manage, track, and deliver impactful training programs with ease.</p>");
        html.append("</div>");

        /* Features */
        html.append("<div class='section'>");
        html.append("<h2>Key Features</h2>");
        html.append("<div class='features'>");

        html.append("<div class='card'>");
        html.append("<h3>Course Management</h3>");
        html.append("<p>Create and organize structured training programs easily.</p>");
        html.append("</div>");

        html.append("<div class='card'>");
        html.append("<h3>Trainer Management</h3>");
        html.append("<p>Assign trainers and monitor their performance.</p>");
        html.append("</div>");

        html.append("<div class='card'>");
        html.append("<h3>Trainee Management</h3>");
        html.append("<p>Track enrollments and trainee progress in real time.</p>");
        html.append("</div>");

        html.append("<div class='card'>");
        html.append("<h3>Performance Tracking</h3>");
        html.append("<p>Analyze outcomes with reports and insights.</p>");
        html.append("</div>");

        html.append("</div>");
        html.append("</div>");

        return new ActionResponse(html.toString());
    }
}
