package br.edu.ifsp.model.dao;

import br.edu.ifsp.model.dao.DAO;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.schedule.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleDAO extends DAO<Schedule, Long> {
    Optional<List<Schedule>> findSomeByInstructor(Instructor instructor);

    Optional<List<Schedule>> findSomeByQualificationProcess (QualificationProcess qualificationProcess);


}
