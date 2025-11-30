package eu.jpereira.trainings.designpatterns.creational.abstractfactory.json;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportFactory;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportFooter;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportHeader;

public class JSONReportFactory implements ReportFactory {
    @Override
    public ReportHeader createReportHeader(){
        return new JSONReportHeader();
    }

    @Override
    public ReportBody createReportBody(){
        return new JSONReportBody();
    }

    @Override
    public ReportFooter createReportFooter(){
        return new JSONReportFooter();
    }
}
