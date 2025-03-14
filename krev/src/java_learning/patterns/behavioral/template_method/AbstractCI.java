package java_learning.patterns.behavioral.template_method;

public abstract class AbstractCI {
    public void process() {
        prerequisites();
        preInstallSteps();
        installPatches();
        postInstallSteps();
        migrateData();
        notifyQA();
    }

    private void prerequisites() {
        System.out.println("Do prerequisites");
    }

    abstract void preInstallSteps();

    abstract void installPatches();

    private void postInstallSteps() {
        System.out.println("Do common post-install steps ");
        specificPostInstallSteps();
    }

    abstract void specificPostInstallSteps();

    private void notifyQA() {
        System.out.println("notify QA");
    }

    protected void migrateData() {
        //default behavior - do nothing!
    }




}
