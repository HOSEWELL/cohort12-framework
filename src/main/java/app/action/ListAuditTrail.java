package app.action;

import app.bean.AuditTrailBean;
import app.bean.CourseBean;
import app.framework.PageContent;
import app.model.AuditTrail;
import app.model.Course;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/audit_trail_lists")
public class ListAuditTrail extends BaseAction<AuditTrail> {

    @EJB
    private AuditTrailBean auditTrailBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(PageContent.CONTENT.name(),
                super.framework.htmlTable(getType(),
                    auditTrailBean.list(new AuditTrail())));
        RequestDispatcher rd = request.getRequestDispatcher("./app_page");
        rd.include(request, response);
    }

}
