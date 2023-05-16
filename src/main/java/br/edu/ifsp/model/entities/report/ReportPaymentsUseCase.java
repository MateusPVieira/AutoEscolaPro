package br.edu.ifsp.model.entities.report;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.instructor.InstructorDAO;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.schedule.ScheduleDAO;

import java.time.LocalDateTime;
import java.util.List;

public class ReportPaymentsUseCase {
    private InstructorDAO instructorDAO;
    private ScheduleDAO scheduleDAO;

    //private PaymentsReportValidator paymentsReportValidator;

    public ReportPaymentsUseCase(InstructorDAO instructorDAO, ScheduleDAO scheduleDAO) {
        this.instructorDAO = instructorDAO;
        this.scheduleDAO = scheduleDAO;
    }

    public StatamentReport reportInstructorPayments(long instructorId, LocalDateTime startDate, LocalDateTime endDate){
        Instructor instructor = instructorDAO.findOne(instructorId).orElseThrow(()-> new EntityNotFoundException("Instructor not found!"));
        //paymentsReportValidator.validate(startDate, endDate);

        List<Schedule> instructorSchedule = scheduleDAO.findSomeByInstructor(instructor).orElseThrow(() -> new EntityNotFoundException("Schedule List not found!"));

        StatamentReport report = new StatamentReport();
        report.addAllRows(instructorSchedule);

        return report;
    }
}
