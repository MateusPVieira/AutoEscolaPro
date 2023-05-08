package br.edu.ifsp.model.entities.schedule;

import br.edu.ifsp.model.entities.RemunerationStatus;
import br.edu.ifsp.model.entities.ValuesReference;

import java.time.LocalDateTime;

public class Schedule {
    private long id;
    private LocalDateTime scheduledDateTime;

    private ScheduleStatus scheduleStatus;
    private RemunerationStatus remunerationStatus;
    private ValuesReference valuesReference;

    public Schedule() {
    }

    public Schedule(long id, LocalDateTime scheduledDateTime, ScheduleStatus scheduleStatus, RemunerationStatus remunerationStatus, ValuesReference valuesReference) {
        this.id = id;
        this.scheduledDateTime = scheduledDateTime;
        this.scheduleStatus = scheduleStatus;
        this.remunerationStatus = remunerationStatus;
        this.valuesReference = valuesReference;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }

    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public RemunerationStatus getRemunerationStatus() {
        return remunerationStatus;
    }

    public void setRemunerationStatus(RemunerationStatus remunerationStatus) {
        this.remunerationStatus = remunerationStatus;
    }

    public ValuesReference getValuesReference() {
        return valuesReference;
    }

    public void setValuesReference(ValuesReference valuesReference) {
        this.valuesReference = valuesReference;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", scheduledDateTime=" + scheduledDateTime +
                ", scheduleStatus=" + scheduleStatus +
                ", remunerationStatus=" + remunerationStatus +
                ", valuesReference=" + valuesReference +
                '}';
    }
}
