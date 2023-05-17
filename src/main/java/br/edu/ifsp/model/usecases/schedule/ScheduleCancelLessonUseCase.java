package br.edu.ifsp.model.usecases.schedule;

import br.edu.ifsp.model.dao.CancellationDAO;
import br.edu.ifsp.model.dao.ScheduleDAO;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.entities.schedule.Cancellation;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.enums.ScheduleStatus;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.exceptions.InactiveItemException;
import br.edu.ifsp.model.validators.CancellationInputValidator;

import java.util.Optional;

import static br.edu.ifsp.model.enums.ScheduleStatus.INACTIVE;

public class ScheduleCancelLessonUseCase {
    private ScheduleDAO scheduleDAO;
    private CancellationDAO cancellationDAO;
    private CancellationInputValidator cancellationInputValidator;

    public ScheduleCancelLessonUseCase(ScheduleDAO scheduleDAO, CancellationDAO cancellationDAO, CancellationInputValidator cancellationInputValidator) {
        this.scheduleDAO = scheduleDAO;
        this.cancellationDAO = cancellationDAO;
        this.cancellationInputValidator = cancellationInputValidator;
    }

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

    private Schedule getScheduleById(Long scheduleId) {
        return scheduleDAO.findOne(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found!"));
    }

    private void checkScheduleStatus(Schedule schedule) {
        if (schedule.getScheduleStatus().equals(ScheduleStatus.INACTIVE)) {
            throw new InactiveItemException("Schedule is already inactive!");
        }
    }

    private Cancellation createCancellation(String reason, Long fineValue, Schedule schedule) {
        return new Cancellation(reason, fineValue, schedule);
    }

    private void validateCancellation(Cancellation cancellation) {
        Notification notification = cancellationInputValidator.validate(cancellation);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
    }

    private long createCancellationInDatabase(Cancellation cancellation) {
        return cancellationDAO.create(cancellation);
    }

    private void updateScheduleStatus(Schedule schedule) {
        schedule.setScheduleStatus(ScheduleStatus.INACTIVE);
        scheduleDAO.update(schedule);
    }
}


