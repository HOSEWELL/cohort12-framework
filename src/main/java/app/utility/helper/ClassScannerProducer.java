package app.utility.helper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import java.util.Date;

@ApplicationScoped
public class ClassScannerProducer {

    @Produces
    @ApplicationScoped
    public ClassScanner createScanner(){
        System.out.println("*************** creating contextual instance " +
                "for  specify time..." + new Date());
        return new ClassScanner();
    }
}
