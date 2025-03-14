package java_learning.patterns.behavioral.template_method;

public class Main {
    public static void main(String[] args) {
        ExternalEnv externalEnv = new ExternalEnv();
        InternalEnv internalEnv = new InternalEnv();

        externalEnv.process();
        System.out.println("----");
        internalEnv.process();
    }
}
