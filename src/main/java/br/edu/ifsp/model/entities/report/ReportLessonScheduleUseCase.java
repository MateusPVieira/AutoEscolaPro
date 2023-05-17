package br.edu.ifsp.model.entities.report;

import br.edu.ifsp.model.entities.DateValidator;
import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.Notification;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.instructor.InstructorDAO;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.qualification.QualificationProcessDAO;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.schedule.ScheduleDAO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ReportLessonScheduleUseCase {
    private InstructorDAO instructorDAO;
    private QualificationProcessDAO qualificationProcessDAO;
    private ScheduleDAO scheduleDAO;

    private DateValidator dateValidator;

    public ReportLessonScheduleUseCase(DateValidator dateValidator, QualificationProcessDAO qualificationProcessDAO, ScheduleDAO scheduleDAO, InstructorDAO instructorDAO) {
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.scheduleDAO = scheduleDAO;
        this.instructorDAO = instructorDAO;
        this.dateValidator = dateValidator;
    }

    public ScheduleReport reportInstructorLessonSchedules(long instructorId, LocalDateTime startDate, LocalDateTime endDate) {
        dateValidator.validate(startDate, endDate);
        Notification notification = dateValidator.validate(startDate, endDate);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Instructor instructor = instructorDAO.findOne(instructorId).orElseThrow(()-> new EntityNotFoundException("Instructor not found!"));

        List<Schedule> lessonSchedule = scheduleDAO.findSomeByInstructor(instructor).orElseThrow(() -> new EntityNotFoundException("Schedule List not found!"));

        ScheduleReport report = new ScheduleReport();
        report.addAllRows(lessonSchedule);

        return report;
    }

    public ScheduleReport reportQualificationLessonSchedules(long qualificationId, LocalDateTime startDate, LocalDateTime endDate) {
        dateValidator.validate(startDate, endDate);
        Notification notification = dateValidator.validate(startDate, endDate);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        QualificationProcess qualificationProcess = qualificationProcessDAO.findOne(qualificationId).orElseThrow(()-> new EntityNotFoundException("Qualification not found!"));

        List<Schedule> lessonSchedule = scheduleDAO.findSomeByQualificationProcess(qualificationProcess).orElseThrow(() -> new EntityNotFoundException("Schedule List not found!"));

        ScheduleReport report = new ScheduleReport();
        report.addAllRows(lessonSchedule);

        return report;
    }
}