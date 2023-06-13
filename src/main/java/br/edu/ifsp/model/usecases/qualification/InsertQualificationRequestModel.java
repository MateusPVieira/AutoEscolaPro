package br.edu.ifsp.model.usecases.qualification;

import br.edu.ifsp.model.entities.category.DrivingCategory;
import br.edu.ifsp.model.enums.TestStatus;

public record InsertQualificationRequestModel(
        long studentId,
        long instructorId,
        TestStatus eyeExam,
        TestStatus psychoExam,
        TestStatus theoricExam, int numberOfLessons,
        Long qualificationValueCents,
        DrivingCategory drivingCategory) {


}