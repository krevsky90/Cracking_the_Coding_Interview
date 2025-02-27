package java_learning.patterns.structural.adapter.my_example;

/**
 * ATTENTION!
 * in this example adapter does not extend Service class, but potentially can
 */
public class XMLAdapter implements Parser {
    private Service service;

    public XMLAdapter(Service service) {
        this.service = service;
    }

    private String convertXmlToJSON(String xmlFile) {
        System.out.println("here is we converting xmlFile to JSON");
        return "JSON file";
    }

    @Override
    public void parse(String xmlFile) {
        service.parseJSON(convertXmlToJSON(xmlFile));
    }
}
