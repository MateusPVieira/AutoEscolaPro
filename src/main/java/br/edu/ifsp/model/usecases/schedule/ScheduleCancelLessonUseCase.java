package br.edu.ifsp.model.usecases.schedule;

import br.edu.ifsp.model.dao.CancellationDAO;
import br.edu.ifsp.model.dao.ScheduleDAO;
import br.edu.ifsp.model.entities.schedule.Cancellation;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.exceptions.InactiveItemException;

import static br.edu.ifsp.model.enums.ScheduleStatus.INACTIVE;

public class ScheduleCancelLessonUseCase {
    private String reason;

    private long id;

    private long fineValueInCents;

    private Schedule schedule;

    private Cancellation cancellation;

    private ScheduleDAO scheduleDAO;

    private CancellationDAO cancellationDAO;

    public ScheduleCancelLessonUseCase(String reason, long id, long fineValueInCents) {
        this.reason = reason;
        this.id = id;
        this.fineValueInCents = fineValueInCents;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFineValueInCents() {
        return fineValueInCents;
    }

    public void setFineValueInCents(long fineValueInCents) {
        this.fineValueInCents = fineValueInCents;
    }

    public void checkCancelLesson(String reason, Schedule schedule, long id, ScheduleDAO scheduleDAO ){
        Schedule lesson = scheduleDAO.findOne(id).orElseThrow(() -> new EntityNotFoundException("Lesson not found!"));
        lesson.setScheduleStatus(INACTIVE) ;
        if(schedule.getScheduledDateTime() == null){
            throw new InactiveItemException("Unscheduled lesson!");
        }

        if(reason == null){
            throw new InactiveItemException("Reason is empty!");
        }

        if(schedule.getScheduleStatus().equals(INACTIVE)){
            throw new InactiveItemException("Lesson already canceled!");
        }

    }

    public Long insert(Cancellation cancellation){
        return cancellationDAO.create(cancellation);
    }

    public boolean update(Schedule schedule){
        Schedule scheduleLesson = scheduleDAO.findOne(id).orElseThrow(() -> new EntityNotFoundException("Lesson not found!"));
        scheduleLesson.setScheduleStatus(INACTIVE) ;
        Long idSchedule = id;
        if(scheduleDAO.findOne(idSchedule).isEmpty())
            throw new EntityNotFoundException("Lesson not found!");

        return scheduleDAO.update(scheduleLesson);

    }

}
