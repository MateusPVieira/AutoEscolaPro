package br.edu.ifsp.model.entities.schedule;

import br.edu.ifsp.model.entities.schedule.Schedule;

public class Cancellation {
    private long id;
    private String reason;
    private long fineValueInCents;
    private Schedule schedule;

    public Cancellation(long id, String reason, long fineValueInCents, Schedule schedule) {
        this.id = id;
        this.reason = reason;
        this.fineValueInCents = fineValueInCents;
        this.schedule = schedule;
    }

    public Cancellation(String reason, long fineValueInCents, Schedule schedule) {
        this.reason = reason;
        this.fineValueInCents = fineValueInCents;
        this.schedule = schedule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getFineValueInCents() {
        return fineValueInCents;
    }

    public void setFineValueInCents(long fineValueInCents) {
        this.fineValueInCents = fineValueInCents;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Cancellation{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", fineValueInCents=" + fineValueInCents +
                ", schedule=" + schedule +
                '}';
    }
}
