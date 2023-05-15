package br.edu.ifsp.model.entities.schedule;

import br.edu.ifsp.model.entities.*;
import br.edu.ifsp.model.entities.instructor.InstructorDAO;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.qualification.QualificationProcessDAO;

import java.time.LocalDateTime;

public class ScheduleLessonUseCase {
    private ScheduleDAO scheduleDAO;
    private QualificationProcessDAO qualificationProcessDAO;
    private InstructorDAO instructorDAO;

    private ValuesReference valuesReference;

    public ScheduleLessonUseCase(ScheduleDAO scheduleDAO,
                                 QualificationProcessDAO qualificationProcessDAO,
                                 InstructorDAO instructorDAO,
                                 ValuesReference valuesReference) {
        this.scheduleDAO = scheduleDAO;
        this.qualificationProcessDAO = qualificationProcessDAO;
        this.instructorDAO = instructorDAO;
        this.valuesReference = valuesReference;
    }

    public void schedule(Long qualificationId){
        QualificationProcess qualificationProcess = qualificationProcessDAO.findOne(qualificationId).orElseThrow(() -> new EntityNotFoundException("Qualification Process not found!"));

        if(qualificationProcess.getRegistrationStatus().equals(RegistrationStatus.INACTIVE)){
            throw new InactiveItemException("Qualification Process status is INACTIVE!");
        }

        if(qualificationProcess.getInstructor().getRegistrationStatus().equals(RegistrationStatus.INACTIVE)){
            throw new InactiveItemException("Instructor status is INACTIVE!");
        }

        //checar horários disponíveis do instrutor, problemas com Schedule(Lesson/Tests).
        //

        //retornar os horários disponíveis à interface, isso nao esta descrito corretamente no doc.

    }

    public void concludeSchedule(LocalDateTime date, QualificationProcess qualificationProcess){ //segunda parte após selecionar o horário
        long lessonValue = valuesReference.getLessonValueInCents();

        Schedule lessonSchedule = new Schedule(date, ScheduleStatus.ACTIVE, RemunerationStatus.NOT_REMUNERATED,valuesReference);
        /* Algo de errado, acredito que Schedule deveria ser mãe de duas classes LessonSchedule e TestSchedule, as quais possuem
        o lessonValue ali que ficou solto. Isso também previniria que uma alteração nos valueReferences modificasse os valores de uma
        aula já registrada.
        A criação das duas classes herdeiras também resultaria em dois novos DAO para elas.
        Isso está bem confuso pq aparecem ja DAOs diferentes nos diagramas e principalmente na questão da
        agenda do instrutor.
         */

        long lessonId = scheduleDAO.create(lessonSchedule);
        lessonSchedule.setId(lessonId);
        qualificationProcess.addDrivingLesson(lessonSchedule);


    }
}
