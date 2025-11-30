package eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportFactory;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportFooter;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportHeader;

public class XMLReportFactory implements ReportFactory{
    @Override
    public ReportHeader createReportHeader(){
        return new XMLReportHeader();
    }

    @Override
    public ReportBody createReportBody(){
        return new XMLReportBody();
    }

    @Override
    public ReportFooter createReportFooter(){
        return new XMLReportFooter();
    }
}
