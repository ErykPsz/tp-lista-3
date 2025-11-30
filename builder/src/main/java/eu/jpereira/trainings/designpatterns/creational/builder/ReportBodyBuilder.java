package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;

public interface ReportBodyBuilder {
    ReportBody getReportBody(SaleEntry saleEntry);
}
