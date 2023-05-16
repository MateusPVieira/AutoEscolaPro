package br.edu.ifsp.model.entities.schedule;

import br.edu.ifsp.model.entities.DAO;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;

import java.util.List;
import java.util.Optional;

public interface ScheduleDAO extends DAO<Schedule, Long> {
    Optional<List<Schedule>> findSomeByInstructor(Instructor instructor);

    Optional<List<Schedule>> findSomeByQualificationProcess (QualificationProcess qualificationProcess);


}
