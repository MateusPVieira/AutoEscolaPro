package br.edu.ifsp.model.usecases.schedule;

import br.edu.ifsp.model.entities.schedule.Cancellation;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.exceptions.InactiveItemException;
import br.edu.ifsp.model.validators.CancellationInputValidator;
import br.edu.ifsp.model.dao.CancellationDAO;
import br.edu.ifsp.model.dao.ScheduleDAO;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.enums.ScheduleStatus;

import java.util.Optional;


/**
*
 *  The ScheduleCancelLessonUseCase class represents a use case for canceling a lesson schedule.
 * It interacts with ScheduleDAO, CancellationDAO, and CancellationInputValidator to perform the cancellation process.
 *
 * @author Mateus Vieira
 */
public class ScheduleCancelLessonUseCase {
    private ScheduleDAO scheduleDAO;
    private CancellationDAO cancellationDAO;
    private CancellationInputValidator cancellationInputValidator;

    /**
     *
     * Constructs a new ScheduleCancelLessonUseCase with the specified dependencies.
     * @param scheduleDAO the data access object for schedules
     * @param cancellationDAO the data access object for cancellations
     * @param cancellationInputValidator the validator for cancellation input
     */
    public ScheduleCancelLessonUseCase(ScheduleDAO scheduleDAO, CancellationDAO cancellationDAO,
                                       CancellationInputValidator cancellationInputValidator) {
        this.scheduleDAO = scheduleDAO;
        this.cancellationDAO = cancellationDAO;
        this.cancellationInputValidator = cancellationInputValidator;
    }
    /**
     *
     * Cancels a schedule by its ID, providing a reason and fine value (optional).
     *
     * @param scheduleId the ID of the schedule to be canceled
     *
     * @param reason the reason for the cancellation
     *
     * @param fineValue the fine value associated with the cancellation (optional, default value is 0)
     *
     * @return the ID of the created cancellation
     *
     * @throws EntityNotFoundException if the schedule with the specified ID is not found
     *
     * @throws InactiveItemException if the schedule is already inactive
     *
     * @throws IllegalArgumentException if the cancellation input is invalid
     */
    public long cancelSchedule(Long scheduleId, String reason, Long fineValue) {
        fineValue = Optional.ofNullable(fineValue).orElse(0L);

        Schedule schedule = getScheduleById(scheduleId);
        checkScheduleStatus(schedule);

        Cancellation cancellation = createCancellation(reason, fineValue, schedule);
        validateCancellation(cancellation);

        long cancellationId = createCancellationInDatabase(cancellation);

        updateScheduleStatus(schedule);

        return cancellationId;
    }

    /**

     Retrieves a schedule by its ID.
     @param scheduleId the ID of the schedule to retrieve
     @return the schedule with the specified ID
     @throws EntityNotFoundException if the schedule with the specified ID is not found
     */
    private Schedule getScheduleById(Long scheduleId) {
        return scheduleDAO.findOne(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found!"));
    }
    /**

     Checks the status of a schedule and throws an exception if it is already inactive.
     @param schedule the schedule to check
     @throws InactiveItemException if the schedule is already inactive
     */
    private void checkScheduleStatus(Schedule schedule) {
        if (schedule.getScheduleStatus().equals(ScheduleStatus.INACTIVE)) {
            throw new InactiveItemException("Schedule is already inactive!");
        }
    }
    /**

     Creates a cancellation object with the specified reason, fine value, and associated schedule.
     @param reason the reason for the cancellation
     @param fineValue the fine value associated with the cancellation
     @param schedule the schedule associated with the cancellation
     @return the created cancellation object
     */
    private Cancellation createCancellation(String reason, Long fineValue, Schedule schedule) {
        return new Cancellation(reason, fineValue, schedule);
    }
    /**

     Validates the cancellation input using the cancellation input validator.
     @param cancellation the cancellation to validate
     @throws IllegalArgumentException if the cancellation input is invalid
     */
    private void validateCancellation(Cancellation cancellation) {
        Notification notification = cancellationInputValidator.validate(cancellation);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
    }
    /**

     Creates a cancellation in the database.
     @param cancellation the cancellation to create
     @return the ID of the created cancellation
     */
    private long createCancellationInDatabase(Cancellation cancellation) {
        return cancellationDAO.create(cancellation);
    }
    /**

     Updates the status of the schedule to INACTIVE in the database.
     @param schedule the schedule to update
     */
    private void updateScheduleStatus(Schedule schedule) {
        schedule.setScheduleStatus(ScheduleStatus.INACTIVE);
        scheduleDAO.update(schedule);
    }
}


