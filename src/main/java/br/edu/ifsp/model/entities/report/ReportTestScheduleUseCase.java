package br.edu.ifsp.model.entities.report;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.qualification.QualificationProcessDAO;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.schedule.ScheduleDAO;

import java.time.LocalDateTime;
import java.util.List;

public class ReportTestScheduleUseCase {
    private QualificationProcessDAO qualificationProcessDAO;
    private ScheduleDAO scheduleDAO;

    //private TestReportValidator testReportValidator;

    public ReportTestScheduleUseCase(QualificationProcessDAO qualificationProcessDAO, ScheduleDAO scheduleDAO) {
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.scheduleDAO = scheduleDAO;
    }

    public ScheduleReport reportTestsSchedules(long qualificationId, LocalDateTime startDate, LocalDateTime endDate){
        QualificationProcess qualificationProcess = qualificationProcessDAO.findOne(qualificationId).orElseThrow(()-> new EntityNotFoundException("Qualification not found!"));
        //testReportValidator.validate(startDate, endDate);

        List<Schedule> testSchedule = scheduleDAO.findSomeByQualificationProcess(qualificationProcess).orElseThrow(() -> new EntityNotFoundException("Schedule List not found!"));

        ScheduleReport report = new ScheduleReport();
        report.addAllRows(testSchedule);

        return report;
    }
}
