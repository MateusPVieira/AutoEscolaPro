package br.edu.ifsp.model.entities.qualification;

import br.edu.ifsp.model.entities.DrivingCategory;
import br.edu.ifsp.model.entities.RegistrationStatus;
import br.edu.ifsp.model.entities.TestStatus;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.entities.user.User;

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

    public QualificationProcess() {
    }
    public QualificationProcess(long qualificationValueCents, int minimumNumberOfLessons, User user, RegistrationStatus registrationStatus, TestStatus eyeExam, TestStatus theoricExam, TestStatus psychoExam, DrivingCategory drivingCategory, Instructor instructor, Student student) {
        this.qualificationValueCents = qualificationValueCents;
        this.minimumNumberOfLessons = minimumNumberOfLessons;
        this.user = user;
        this.registrationStatus = registrationStatus;
        this.eyeExam = eyeExam;
        this.theoricExam = theoricExam;
        this.psychoExam = psychoExam;
        this.drivingCategory = drivingCategory;
        this.instructor = instructor;
        this.student = student;
    }
    public QualificationProcess(long id, long qualificationValueCents, LocalDate openingDate, int minimumNumberOfLessons, User user, RegistrationStatus registrationStatus, TestStatus eyeExam, TestStatus theoricExam, TestStatus psychoExam, DrivingCategory drivingCategory, Instructor instructor, Student student, List<Schedule> drivingLessons, List<Schedule> drivingTests) {
        this.id = id;
        this.qualificationValueCents = qualificationValueCents;
        this.openingDate = openingDate;
        this.minimumNumberOfLessons = minimumNumberOfLessons;
        this.user = user;
        this.registrationStatus = registrationStatus;
        this.eyeExam = eyeExam;
        this.theoricExam = theoricExam;
        this.psychoExam = psychoExam;
        this.drivingCategory = drivingCategory;
        this.instructor = instructor;
        this.student = student;
        this.drivingLessons = drivingLessons;
        this.drivingTests = drivingTests;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQualificationValueCents() {
        return qualificationValueCents;
    }

    public void setQualificationValueCents(long qualificationValueCents) {
        this.qualificationValueCents = qualificationValueCents;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public int getMinimumNumberOfLessons() {
        return minimumNumberOfLessons;
    }

    public void setMinimumNumberOfLessons(int minimumNumberOfLessons) {
        this.minimumNumberOfLessons = minimumNumberOfLessons;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public TestStatus getEyeExam() {
        return eyeExam;
    }

    public void setEyeExam(TestStatus eyeExam) {
        this.eyeExam = eyeExam;
    }

    public TestStatus getTheoricExam() {
        return theoricExam;
    }

    public void setTheoricExam(TestStatus theoricExam) {
        this.theoricExam = theoricExam;
    }

    public TestStatus getPsychoExam() {
        return psychoExam;
    }

    public void setPsychoExam(TestStatus psychoExam) {
        this.psychoExam = psychoExam;
    }

    public DrivingCategory getDrivingCategory() {
        return drivingCategory;
    }

    public void setDrivingCategory(DrivingCategory drivingCategory) {
        this.drivingCategory = drivingCategory;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Schedule> getDrivingLessons() {
        return drivingLessons;
    }

    public void setDrivingLessons(List<Schedule> drivingLessons) {
        this.drivingLessons = drivingLessons;
    }

    public List<Schedule> getDrivingTests() {
        return drivingTests;
    }

    public void setDrivingTests(List<Schedule> drivingTests) {
        this.drivingTests = drivingTests;
    }
}
