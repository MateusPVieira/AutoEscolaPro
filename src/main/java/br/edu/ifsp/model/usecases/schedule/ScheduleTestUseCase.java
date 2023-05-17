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

public class ScheduleTestUseCase {
    private QualificationProcessDAO qualificationProcessDAO;
    private br.edu.ifsp.model.dao.ScheduleDAO ScheduleDAO;
    private ValuesReference valuesReference;

    public ScheduleTestUseCase(QualificationProcessDAO qualificationProcessDAO, ScheduleDAO ScheduleDAO, ValuesReference valuesReference) {
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.ScheduleDAO = ScheduleDAO;
        this.valuesReference = valuesReference;
    }

    public long schedule(Long qualificationId, LocalDateTime date) throws Exception {
        QualificationProcess qualificationProcess = qualificationProcessDAO.findOne(qualificationId).orElseThrow(() -> new EntityNotFoundException("Qualification Process not found!"));
        if(qualificationProcess.getRegistrationStatus().equals(RegistrationStatus.INACTIVE)) {
            throw new InactiveItemException("Qualification Process id:" + qualificationId + " is Inactive!");
        }
        long testValue = valuesReference.getTestValueInCents();//?

        Schedule testSchedule = new Schedule(date, ScheduleStatus.ACTIVE, RemunerationStatus.REMUNERATED, valuesReference, ScheduleType.TEST);
        long scheduleId = ScheduleDAO.create(testSchedule);
        testSchedule.setId(scheduleId);


        qualificationProcess.addDrivingTest(testSchedule);
        qualificationProcessDAO.update(qualificationProcess);
        return scheduleId;
    }
}
