package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportFactory;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportFactory;

public class ReportFactoryManager {

    public ReportFactory createReportFactory(String type) {
        return switch (type) {
            case "JSON" -> new JSONReportFactory();
            case "XML" -> new XMLReportFactory();
            default -> throw new IllegalArgumentException("Unknown report type: " + type);
        };
    }
}
