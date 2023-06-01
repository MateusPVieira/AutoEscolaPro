package br.edu.ifsp.model.usecases.schedule;

import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.reference.ValuesReference;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.exceptions.InactiveItemException;
import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.dao.ScheduleDAO;
import br.edu.ifsp.model.enums.ScheduleStatus;
import br.edu.ifsp.model.enums.ScheduleType;
import br.edu.ifsp.model.enums.RegistrationStatus;
import br.edu.ifsp.model.enums.RemunerationStatus;

import java.time.LocalDateTime;

/**

 The ScheduleLessonUseCase class represents a use case for scheduling a driving lesson.

 It interacts with ScheduleDAO, QualificationProcessDAO, InstructorDAO, and ValuesReference to perform the scheduling process.

 @author Mateus Vieira
 */
public class ScheduleLessonUseCase {
    private ScheduleDAO scheduleDAO;
    private QualificationProcessDAO qualificationProcessDAO;
    private InstructorDAO instructorDAO;
    private ValuesReference valuesReference;

    /**

     Constructs a new ScheduleLessonUseCase with the specified dependencies.
     @param scheduleDAO the data access object for schedules
     @param qualificationProcessDAO the data access object for qualification processes
     @param instructorDAO the data access object for instructors
     @param valuesReference the reference for values and settings
     */
    public ScheduleLessonUseCase(ScheduleDAO scheduleDAO, QualificationProcessDAO qualificationProcessDAO,
                                 InstructorDAO instructorDAO, ValuesReference valuesReference) {
        this.scheduleDAO = scheduleDAO;
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.instructorDAO = instructorDAO;
        this.valuesReference = valuesReference;
    }
    /**

     Schedules a driving lesson for the specified qualification process.

     @param qualificationId the ID of the qualification process

     @throws EntityNotFoundException if the qualification process with the specified ID is not found

     @throws InactiveItemException if the qualification process or the instructor is inactive
     */
    public void schedule(Long qualificationId) {
        QualificationProcess qualificationProcess = qualificationProcessDAO.findOne(qualificationId)
                .orElseThrow(() -> new EntityNotFoundException("Qualification Process not found!"));

        if (qualificationProcess.getRegistrationStatus().equals(RegistrationStatus.INACTIVE)) {
            throw new InactiveItemException("Qualification Process status is INACTIVE!");
        }

        if (qualificationProcess.getInstructor().getRegistrationStatus().equals(RegistrationStatus.INACTIVE)) {
            throw new InactiveItemException("Instructor status is INACTIVE!");
        }

// Check instructor's available schedules and handle Schedule (Lesson/Tests) conflicts

// Return the available schedules to the interface (not described in detail in the code)
    }

    /**

     Concludes the scheduling process by creating a new driving lesson schedule.

     @param date the date and time of the lesson

     @param qualificationProcess the qualification process associated with the lesson

     @return the ID of the created lesson schedule
     */
    public long concludeSchedule(LocalDateTime date, QualificationProcess qualificationProcess) {
        long lessonValue = valuesReference.getLessonValueInCents();

        Schedule lessonSchedule = new Schedule(date, ScheduleStatus.ACTIVE, RemunerationStatus.NOT_REMUNERATED,
                valuesReference, ScheduleType.LESSON);
        /* lesson value? */

        long lessonId = scheduleDAO.create(lessonSchedule);
        lessonSchedule.setId(lessonId);

        qualificationProcess.addDrivingLesson(lessonSchedule);
        qualificationProcessDAO.update(qualificationProcess);

        return lessonId;
    }
}




