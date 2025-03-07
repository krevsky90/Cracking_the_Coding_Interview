package java_learning.patterns.behavioral.chain_of_responsibility.notifications;

public class Client {
    public static void main(String[] args) {
        ReportNotifier reportNotifier = new ReportNotifier();
        EmailNotifier emailNotifier = new EmailNotifier();
        SmsNotifier smsNotifier = new SmsNotifier();

        reportNotifier.setNext(emailNotifier);
        emailNotifier.setNext(smsNotifier);

        reportNotifier.notify("Regular report", 1);
        reportNotifier.notify("Incident report", 2);
        reportNotifier.notify("ALARM!", 5);

    }
}
