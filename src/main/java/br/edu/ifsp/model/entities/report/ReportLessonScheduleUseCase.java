package br.edu.ifsp.model.entities.report;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.instructor.InstructorDAO;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.qualification.QualificationProcessDAO;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.schedule.ScheduleDAO;

import java.time.LocalDateTime;
import java.util.List;

public class ReportLessonScheduleUseCase {
    private InstructorDAO instructorDAO;
    private QualificationProcessDAO qualificationProcessDAO;
    private ScheduleDAO scheduleDAO;

    //private TestReportValidator testReportValidator;

    public ReportLessonScheduleUseCase(QualificationProcessDAO qualificationProcessDAO, ScheduleDAO scheduleDAO, InstructorDAO instructorDAO) {
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.scheduleDAO = scheduleDAO;
        this.instructorDAO = instructorDAO;
    }

    public ScheduleReport reportInstructorLessonSchedules(long instructorId, LocalDateTime startDate, LocalDateTime endDate) {
        Instructor instructor = instructorDAO.findOne(instructorId).orElseThrow(()-> new EntityNotFoundException("Instructor not found!"));
        //testReportValidator.validate(startDate, endDate);

        List<Schedule> lessonSchedule = scheduleDAO.findSomeByInstructor(instructor).orElseThrow(() -> new EntityNotFoundException("Schedule List not found!"));

        ScheduleReport report = new ScheduleReport();
        report.addAllRows(lessonSchedule);

        return report;
    }

    public ScheduleReport reportQualificationLessonSchedules(long qualificationId, LocalDateTime startDate, LocalDateTime endDate) {
        QualificationProcess qualificationProcess = qualificationProcessDAO.findOne(qualificationId).orElseThrow(()-> new EntityNotFoundException("Qualification not found!"));
        //testReportValidator.validate(startDate, endDate);

        List<Schedule> lessonSchedule = scheduleDAO.findSomeByQualificationProcess(qualificationProcess).orElseThrow(() -> new EntityNotFoundException("Schedule List not found!"));

        ScheduleReport report = new ScheduleReport();
        report.addAllRows(lessonSchedule);

        return report;
    }
}