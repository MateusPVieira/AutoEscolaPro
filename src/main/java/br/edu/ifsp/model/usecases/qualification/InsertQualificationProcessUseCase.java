package br.edu.ifsp.application.view.model.usecases.qualification;

import br.edu.ifsp.application.view.model.dao.InstructorDAO;
import br.edu.ifsp.application.view.model.dao.QualificationProcessDAO;
import br.edu.ifsp.application.view.model.dao.StudentDAO;
import br.edu.ifsp.application.view.model.entities.category.DrivingCategory;
import br.edu.ifsp.application.view.model.entities.instructor.Instructor;
import br.edu.ifsp.application.view.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.application.view.model.entities.qualification.QualificationProcessInputRequestValidator;
import br.edu.ifsp.application.view.model.entities.student.Student;
import br.edu.ifsp.application.view.model.entities.user.Session;
import br.edu.ifsp.application.view.model.entities.user.User;
import br.edu.ifsp.application.view.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.application.view.model.validators.Validator;
import br.edu.ifsp.application.view.model.enums.RegistrationStatus;
import br.edu.ifsp.application.view.model.entities.notification.Notification;
import br.edu.ifsp.application.view.model.enums.TestStatus;

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
     * @param studentId                 The ID of the student associated with the qualification process.
     * @param instructorId              The ID of the instructor associated with the qualification process.
     * @param eyeExam                   The status of the eye exam for the qualification process.
     * @param psychoExam                The status of the psychological exam for the qualification process.
     * @param theoricExam               The status of the theoretical exam for the qualification process.
     * @param numberOfLessons           The number of lessons taken for the qualification process.
     * @param qualificationValueCents   The qualification value in cents for the qualification process.
     * @param drivingCategory           The driving category for the qualification process.
     * @return                          The ID of the inserted qualification process.
     * @throws EntityNotFoundException If the student or instructor is not found in the system.
     * @throws IllegalArgumentException If the qualification process validation fails.
     */
    public Long Insert(long studentId, long instructorId, TestStatus eyeExam, TestStatus psychoExam, TestStatus theoricExam, int numberOfLessons, Long qualificationValueCents, DrivingCategory drivingCategory){
        Validator<QualificationProcess> validator = new QualificationProcessInputRequestValidator();
        Student student = studentDAO.findOne(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found!"));
        Instructor instructor = instructorDAO.findOne(instructorId).orElseThrow(() -> new EntityNotFoundException("Instructor not found!"));
        User user = Session.getInstance().getUser().toUser();
        QualificationProcess qualificationProcess = new QualificationProcess(
                qualificationValueCents, numberOfLessons, user, RegistrationStatus.ACTIVE,
                eyeExam, theoricExam, psychoExam, drivingCategory, instructor, student);

        Notification notification = validator.validate(qualificationProcess);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return qualificationProcessDAO.create(qualificationProcess);
    }
}

