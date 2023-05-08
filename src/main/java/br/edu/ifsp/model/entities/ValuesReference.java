package br.edu.ifsp.model.entities;

import java.time.LocalTime;

public class ValuesReference {
    private static LocalTime DRIVING_SCHOOL_OPENING_TIME;
    private static LocalTime DRIVING_SCHOOL_CLOSING_TIME;
    private long lessonValueInCents;
    private int defaultMinimunNumberOfLessons;
    private long TestValueInCents;
    private DrivingCategory drivingCategory;

    public ValuesReference(long lessonValueInCents, int defaultMinimunNumberOfLessons, long testValueInCents, DrivingCategory drivingCategory) {
        this.lessonValueInCents = lessonValueInCents;
        this.defaultMinimunNumberOfLessons = defaultMinimunNumberOfLessons;
        TestValueInCents = testValueInCents;
        this.drivingCategory = drivingCategory;
    }

    public static LocalTime getDrivingSchoolOpeningTime() {
        return DRIVING_SCHOOL_OPENING_TIME;
    }

    public static void setDrivingSchoolOpeningTime(LocalTime drivingSchoolOpeningTime) {
        DRIVING_SCHOOL_OPENING_TIME = drivingSchoolOpeningTime;
    }

    public static LocalTime getDrivingSchoolClosingTime() {
        return DRIVING_SCHOOL_CLOSING_TIME;
    }

    public static void setDrivingSchoolClosingTime(LocalTime drivingSchoolClosingTime) {
        DRIVING_SCHOOL_CLOSING_TIME = drivingSchoolClosingTime;
    }

    public long getLessonValueInCents() {
        return lessonValueInCents;
    }

    public void setLessonValueInCents(long lessonValueInCents) {
        this.lessonValueInCents = lessonValueInCents;
    }

    public int getDefaultMinimunNumberOfLessons() {
        return defaultMinimunNumberOfLessons;
    }

    public void setDefaultMinimunNumberOfLessons(int defaultMinimunNumberOfLessons) {
        this.defaultMinimunNumberOfLessons = defaultMinimunNumberOfLessons;
    }

    public long getTestValueInCents() {
        return TestValueInCents;
    }

    public void setTestValueInCents(long testValueInCents) {
        TestValueInCents = testValueInCents;
    }

    public DrivingCategory getDrivingCategory() {
        return drivingCategory;
    }

    public void setDrivingCategory(DrivingCategory drivingCategory) {
        this.drivingCategory = drivingCategory;
    }

    @Override
    public String toString() {
        return "ValuesReference{" +
                "lessonValueInCents=" + lessonValueInCents +
                ", defaultMinimunNumberOfLessons=" + defaultMinimunNumberOfLessons +
                ", TestValueInCents=" + TestValueInCents +
                ", drivingCategory=" + drivingCategory +
                '}';
    }
}
