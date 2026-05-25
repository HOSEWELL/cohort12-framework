package app.bean;

import app.dao.AuditTrailDao;
import app.model.AuditTrail;
import app.websocket.AuditTrailWs;
import jakarta.annotation.Resource;
import jakarta.ejb.Remote;
import jakarta.ejb.Singleton;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

import java.util.Date;
import java.util.List;

@Singleton
@Remote
public class AuditTrailBean {

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/jms/queue/TrainingAppQueue")
    private Queue auditQue;

    @Inject
    private AuditTrailDao auditTrailDao;

    public void save(@Observes AuditTrail auditTrail){
        auditTrail.setActivity(new Date() + ": "
                + auditTrail.getActivity());
        auditTrailDao.save(auditTrail);
        context.createProducer().send(auditQue,
                auditTrail.getActivity());
        AuditTrailWs.broadcast(auditTrail.getActivity());
    }

    public List<AuditTrail> list(AuditTrail filter){
        return auditTrailDao.findAll();
    }
}