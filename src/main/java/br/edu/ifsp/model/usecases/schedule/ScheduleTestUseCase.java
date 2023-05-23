package br.edu.ifsp.model.usecases.schedule;

import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.entities.reference.ValuesReference;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.dao.ScheduleDAO;
import br.edu.ifsp.model.enums.ScheduleStatus;
import br.edu.ifsp.model.enums.ScheduleType;
import br.edu.ifsp.model.enums.RegistrationStatus;
import br.edu.ifsp.model.enums.RemunerationStatus;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.exceptions.InactiveItemException;

import java.time.LocalDateTime;

/**

 The ScheduleTestUseCase class represents a use case for scheduling a driving test.

 It interacts with QualificationProcessDAO, ScheduleDAO, and ValuesReference to perform the scheduling process.

 @author Mateus Vieira
 */
public class ScheduleTestUseCase {
    private QualificationProcessDAO qualificationProcessDAO;
    private ScheduleDAO scheduleDAO;
    private ValuesReference valuesReference;

    /**

     Constructs a new ScheduleTestUseCase with the specified dependencies.
     @param qualificationProcessDAO the data access object for qualification processes
     @param scheduleDAO the data access object for schedules
     @param valuesReference the reference for values and settings
     */
    public ScheduleTestUseCase(QualificationProcessDAO qualificationProcessDAO, ScheduleDAO scheduleDAO,
                               ValuesReference valuesReference) {
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.scheduleDAO = scheduleDAO;
        this.valuesReference = valuesReference;
    }
    /**

     Schedules a driving test for the specified qualification process and date.

     @param qualificationId the ID of the qualification process

     @param date the date and time of the test

     @return the ID of the created test schedule

     @throws EntityNotFoundException if the qualification process with the specified ID is not found

     @throws InactiveItemException if the qualification process is inactive
     */
    public long schedule(Long qualificationId, LocalDateTime date) throws Exception {
        QualificationProcess qualificationProcess = qualificationProcessDAO.findOne(qualificationId)
                .orElseThrow(() -> new EntityNotFoundException("Qualification Process not found!"));

        if (qualificationProcess.getRegistrationStatus().equals(RegistrationStatus.INACTIVE)) {
            throw new InactiveItemException("Qualification Process id: " + qualificationId + " is Inactive!");
        }

        long testValue = valuesReference.getTestValueInCents(); //?

        Schedule testSchedule = new Schedule(date, ScheduleStatus.ACTIVE, RemunerationStatus.REMUNERATED,
                valuesReference, ScheduleType.TEST);
        long scheduleId = scheduleDAO.create(testSchedule);
        testSchedule.setId(scheduleId);

        qualificationProcess.addDrivingTest(testSchedule);
        qualificationProcessDAO.update(qualificationProcess);

        return scheduleId;
    }
}






