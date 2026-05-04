package app.bean;

import app.model.AuditTrail;
import jakarta.ejb.Singleton;
import jakarta.enterprise.event.Observes;

@Singleton
public class AdminNotificationBean {

    public void notification(@Observes AuditTrail auditTrail){
        System.out.println("Admin was notified through email " +
                "about " + auditTrail.getActivity());
    }

    public void notifyRuto(@Observes AuditTrail auditTrail){
        System.out.println("Ruto Notifified " +
                "about " + auditTrail.getActivity());
    }
}
