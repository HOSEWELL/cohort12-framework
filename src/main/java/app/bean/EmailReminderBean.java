package app.bean;

import app.model.AuditTrail;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;

import java.util.Date;

@Singleton
public class EmailReminderBean {

    @EJB
    private AuditTrailBean auditTrailBean;

    //@Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void sendEmailToTrainer(){
        System.out.println("sending reminder email to trainer");
        auditTrailBean.save(new AuditTrail("Email sent to trainer: " +
            new Date()));
    }

}
