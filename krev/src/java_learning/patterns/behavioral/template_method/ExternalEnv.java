package java_learning.patterns.behavioral.template_method;

public class ExternalEnv extends AbstractCI {
    @Override
    void preInstallSteps() {
        System.out.println("Pre install steps for external env");
    }

    @Override
    void installPatches() {
        System.out.println("Install regular patches");
    }

    @Override
    void specificPostInstallSteps() {
        System.out.println("Configure external integrations");
    }

    //override default behavior since external env needs migrated data
    @Override
    protected void migrateData() {
        System.out.println("Migrate Data to ext env");
    }

}
