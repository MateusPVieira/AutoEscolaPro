package br.edu.ifsp.application.view.model.usecases.report;

import br.edu.ifsp.application.view.model.dao.QualificationProcessDAO;
import br.edu.ifsp.application.view.model.dao.ScheduleDAO;
import br.edu.ifsp.application.view.model.entities.notification.Notification;
import br.edu.ifsp.application.view.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.application.view.model.entities.report.ScheduleReport;
import br.edu.ifsp.application.view.model.entities.schedule.Schedule;
import br.edu.ifsp.application.view.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.application.view.model.validators.DateValidator;

import java.time.LocalDateTime;
import java.util.List;

public class ReportTestScheduleUseCase {
    private QualificationProcessDAO qualificationProcessDAO;
    private ScheduleDAO scheduleDAO;
    private DateValidator dateValidator;

    public ReportTestScheduleUseCase(DateValidator dateValidator, QualificationProcessDAO qualificationProcessDAO, ScheduleDAO scheduleDAO) {
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.scheduleDAO = scheduleDAO;
        this.dateValidator = dateValidator;
    }

    public ScheduleReport reportTestsSchedules(long qualificationId, LocalDateTime startDate, LocalDateTime endDate){
        dateValidator.validate(startDate, endDate);
        Notification notification = dateValidator.validate(startDate, endDate);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        QualificationProcess qualificationProcess = qualificationProcessDAO.findOne(qualificationId).orElseThrow(()-> new EntityNotFoundException("Qualification not found!"));

        List<Schedule> testSchedule = scheduleDAO.findSomeByQualificationProcess(qualificationProcess).orElseThrow(() -> new EntityNotFoundException("Schedule List not found!"));

        ScheduleReport report = new ScheduleReport();
        report.addAllRows(testSchedule);

        return report;
    }
}
