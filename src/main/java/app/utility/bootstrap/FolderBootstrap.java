package app.utility.bootstrap;

import jakarta.enterprise.context.Dependent;

@InitBootstrap
@Dependent
public class FolderBootstrap implements Bootstrap {

    @Override
    public void process() {
        System.out.println("==========================");
        System.out.println("Creating course document folders..");
        System.out.println("Creating picture folders.. for storing profiles");
        System.out.println("=========================");

    }
}
