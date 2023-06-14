package br.edu.ifsp.model.usecases.qualification;

import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.entities.category.DrivingCategory;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.qualification.QualificationProcessInputRequestValidator;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.entities.user.Session;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.validators.Validator;
import br.edu.ifsp.model.enums.RegistrationStatus;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.enums.TestStatus;

/**
 * Class responsible for inserting a qualification process.
 * This use case inserts a new qualification process with the provided information.
 * It retrieves the student, instructor, and user information from the respective DAOs.
 * The qualification process is validated using a QualificationProcessInputRequestValidator.
 * If the validation fails, an IllegalArgumentException is thrown with the error message.
 * Otherwise, the qualification process is created and its ID is returned.
 * Note: This use case assumes that the student and instructor with the given IDs exist in the system.
 * If either the student or instructor is not found, an EntityNotFoundException will be thrown.
 * Also, the user session must be active, and a User object must be available through the Session class.
 * If the user session is not active or no User object is found, the behavior is undefined.
 *
 * @author Mateus Vieira
 */
public class InsertQualificationProcessUseCase {

    private StudentDAO studentDAO;
    private InstructorDAO instructorDAO;
    private QualificationProcessDAO qualificationProcessDAO;

    /**
     * Constructor for the InsertQualificationProcessUseCase class.
     *
     * @param studentDAO                The StudentDAO object used to access student data.
     * @param instructorDAO             The InstructorDAO object used to access instructor data.
     * @param qualificationProcessDAO   The QualificationProcessDAO object used to access qualification process data.
     */
    public InsertQualificationProcessUseCase(StudentDAO studentDAO, InstructorDAO instructorDAO, QualificationProcessDAO qualificationProcessDAO){
        this.studentDAO = studentDAO;
        this.instructorDAO = instructorDAO;
        this.qualificationProcessDAO = qualificationProcessDAO;
    }

    /**
     * Inserts a qualification process with the provided information.
     *
     * @param dto           The driving category for the qualification process.
     * @return                          The ID of the inserted qualification process.
     * @throws EntityNotFoundException If the student or instructor is not found in the system.
     * @throws IllegalArgumentException If the qualification process validation fails.
     */
    public Long insert(InsertQualificationRequestModel dto){
        var validator = new QualificationProcessInputRequestValidator();

        var student = studentDAO
                .findOne(dto.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found!"));
        var instructor = instructorDAO
                .findOne(dto.instructorId())
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found!"));
        var user = Session
                .getInstance()
                .getUser().toUser();

        var qualificationProcess = buildQualificationProcess(dto, student, instructor, user);

        var notification = validator.validate(qualificationProcess);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return qualificationProcessDAO.create(qualificationProcess);
    }

    private QualificationProcess buildQualificationProcess(InsertQualificationRequestModel dto, Student student, Instructor instructor, User user) {
        return new QualificationProcess(
                dto.qualificationValueCents(),
                dto.numberOfLessons(),
                user,
                RegistrationStatus.ACTIVE,
                dto.eyeExam(),
                dto.theoricExam(),
                dto.psychoExam(),
                dto.drivingCategory(),
                instructor,
                student);
    }

}


