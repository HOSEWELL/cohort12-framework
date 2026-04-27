package app.utility.general;

import jakarta.enterprise.context.Dependent;

@Dependent
public class TrainingApplication {

    public boolean exists(String nationalId) {
        System.out.println("check application exist for " +
            "national ID: " +nationalId);

        return true;
    }
}
