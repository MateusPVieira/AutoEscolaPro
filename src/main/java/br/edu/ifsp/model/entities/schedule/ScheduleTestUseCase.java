package br.edu.ifsp.model.entities.schedule;

import br.edu.ifsp.model.entities.EntityNotFoundException;
import br.edu.ifsp.model.entities.RegistrationStatus;
import br.edu.ifsp.model.entities.ValuesReference;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.qualification.QualificationProcessDAO;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.schedule.ScheduleStatus;

import java.time.LocalDateTime;

public class ScheduleTestUseCase {
//    private QualificationProcessDAO qualificationProcessDAO;
//    private ScheduleDAO ScheduleDAO;
//    private ValuesReference valuesReference;
//
//    public ScheduleTestUseCase(QualificationProcessDAO qualificationProcessDAO, ScheduleDAO ScheduleDAO, ValuesReference valuesReference) {
//        this.qualificationProcessDAO = qualificationProcessDAO;
//        this.ScheduleDAO = ScheduleDAO;
//        this.valuesReference = valuesReference;
//    }
//
//    public int schedule(Long qualificationId, LocalDateTime date) throws Exception {
//        QualificationProcess qualificationProcess = qualificationProcessDAO.findOne(qualificationId).orElseThrow(() -> new EntityNotFoundException("Qualification Process not found!"));
//        if(qualificationProcess.getRegistrationStatus().equals(RegistrationStatus.INACTIVE)){
//            throw new Exception(); // criar exceção pra isso
//        }
//        long testValue = valuesReference.getTestValueInCents();
//
//        //Schedule testSchedule = new Schedule(date, testValue);
//        ScheduleDAO.insert(testSchedule);
//        // tendi foi nada, terminar
//    }
}
