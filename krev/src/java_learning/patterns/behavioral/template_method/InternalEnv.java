package java_learning.patterns.behavioral.template_method;

public class InternalEnv extends AbstractCI {
    @Override
    void preInstallSteps() {
        System.out.println("Pre install steps for internal env");
    }

    @Override
    void installPatches() {
        System.out.println("Install regular patches + dev tools");
    }

    @Override
    void specificPostInstallSteps() {
        System.out.println("Configure internal integrations");
    }
}
