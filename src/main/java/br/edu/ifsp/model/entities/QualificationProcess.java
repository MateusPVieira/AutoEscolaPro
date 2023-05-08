package br.edu.ifsp.model.entities;

import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.entities.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//need to finish
public class QualificationProcess {
    private long id;
    private long qualificationValueCents;
    private LocalDate openingDate;
    private int minimumNumberOfLessons;

    private User user;
    private RegistrationStatus registrationStatus;
    private TestStatus eyeExam;
    private TestStatus theoricExam;
    private TestStatus psychoExam;
    private DrivingCategory drivingCategory;
    private Instructor instructor;
    private Student student;
    private List<Schedule> drivingLessons = new ArrayList<>();
    private List<Schedule> drivingTests = new ArrayList<>();

}
