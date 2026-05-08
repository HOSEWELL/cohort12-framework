package app.bean;


import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/TrainingAppQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
    }
)
public class ExternalAuditServerBean implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMsg = (TextMessage) message;
            System.out.println("********** JMS Message: "
                + " back up message to external "
                + "server IP 192.168.0.71 "
                + textMsg.getText());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
