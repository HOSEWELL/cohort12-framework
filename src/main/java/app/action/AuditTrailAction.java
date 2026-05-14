package app.action;

import app.bean.AuditTrailBean;
import app.framework.Action;
import app.framework.ActionGetMethod;
import app.framework.ActionResponse;
import app.model.AuditTrail;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Action(value = "audit_trail", label = "Audit Trail", linkPosition = 100)
public class AuditTrailAction {

    @EJB
    private AuditTrailBean auditTrailBean;

    @ActionGetMethod("list")
    public ActionResponse list() {
        return new ActionResponse(AuditTrail.class, auditTrailBean.list(new AuditTrail()));
    }
}
