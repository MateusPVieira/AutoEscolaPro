package br.edu.ifsp.model.usecases.report;

import br.edu.ifsp.model.entities.report.StatamentReport;
import br.edu.ifsp.model.validators.DateValidator;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.dao.ScheduleDAO;

import java.time.LocalDateTime;
import java.util.List;

public class ReportPaymentsUseCase {
    private InstructorDAO instructorDAO;
    private ScheduleDAO scheduleDAO;
    private DateValidator dateValidator;

    public ReportPaymentsUseCase(DateValidator dateValidator, InstructorDAO instructorDAO, ScheduleDAO scheduleDAO) {
        this.instructorDAO = instructorDAO;
        this.scheduleDAO = scheduleDAO;
        this.dateValidator = dateValidator;
    }

    public StatamentReport reportInstructorPayments(long instructorId, LocalDateTime startDate, LocalDateTime endDate){
        dateValidator.validate(startDate, endDate);
        Notification notification = dateValidator.validate(startDate, endDate);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Instructor instructor = instructorDAO.findOne(instructorId).orElseThrow(()-> new EntityNotFoundException("Instructor not found!"));

        List<Schedule> instructorSchedule = scheduleDAO.findSomeByInstructor(instructor).orElseThrow(() -> new EntityNotFoundException("Schedule List not found!"));

        StatamentReport report = new StatamentReport();
        report.addAllRows(instructorSchedule);

        return report;
    }
}
