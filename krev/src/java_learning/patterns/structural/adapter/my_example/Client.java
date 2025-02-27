package java_learning.patterns.structural.adapter.my_example;

public class Client {
    public static void main(String[] args) {
        String xmlFile = "This is xml file";

        //we can't use Service for parsing JSON directly, we need to adapt XML to JSON before that
        Service service = new Service();
        Parser parser = new XMLAdapter(service);

        parser.parse(xmlFile);
    }
}
