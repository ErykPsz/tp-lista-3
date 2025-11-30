package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

public interface ReportFactory {
    ReportHeader createReportHeader();
    ReportBody createReportBody();
    ReportFooter createReportFooter();
}
