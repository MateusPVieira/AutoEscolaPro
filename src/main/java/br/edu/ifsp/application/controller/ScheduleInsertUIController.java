package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.dao.ScheduleDAO;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.daosqlite.QualificationProcessDAOSQLite;
import br.edu.ifsp.model.daosqlite.ScheduleDAOSQLite;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.reference.ValuesReference;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.enums.RemunerationStatus;
import br.edu.ifsp.model.enums.ScheduleStatus;
import br.edu.ifsp.model.enums.ScheduleType;
import br.edu.ifsp.model.usecases.qualification.UpdateQualificationProcessUseCase;
import br.edu.ifsp.model.usecases.student.UpdateStudentUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ScheduleInsertUIController {
    @FXML
    DatePicker dtData;

    @FXML
    TextField txtHour;

    @FXML
    TextField txtMin;

    @FXML
    ComboBox<ScheduleStatus> cbSchStatus;
    @FXML
    ComboBox<RemunerationStatus> cbSchPayment;
    @FXML
    ComboBox<ScheduleType> cbSchType;
    @FXML
    ComboBox<ValuesReference> cbValue;

    ScheduleDAO scheduleDAO = new ScheduleDAOSQLite();
    QualificationProcessDAO qualificationProcessDAO = new QualificationProcessDAOSQLite();
    UpdateQualificationProcessUseCase updateQualificationProcessUseCase = new UpdateQualificationProcessUseCase(qualificationProcessDAO);


    private QualificationProcess qualificationProcessUI;


    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        Schedule schedule = getSchedule();
        long scheduleId = scheduleDAO.create(schedule);
        if (schedule.getScheduleType().equals(ScheduleType.LESSON)){
            qualificationProcessUI.addDrivingLesson(schedule);
        } else {
            qualificationProcessUI.addDrivingTest(schedule);
        }
        qualificationProcessDAO.update(qualificationProcessUI);
        scheduleDAO.insertScheduleQualification(qualificationProcessUI.getId(), scheduleId);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Agendamento inserido com sucesso!");
        returnClicked();
    }

    public void returnClicked() throws IOException {
        WindowLoader.setRoot("StudentEditUI");
    }

    public void setQualificationProcessToUI(QualificationProcess qualificationProcess) {
        this.qualificationProcessUI = qualificationProcess;
    }

    public Schedule getSchedule(){
        LocalDate date = dtData.getValue();
        int minutes = 0;
        int hour = 0;
        try {
             hour = Integer.parseInt(txtHour.getText().trim());
             minutes = Integer.parseInt(txtMin.getText().trim());
            if (hour > 24 || hour < 0 || minutes > 59 || minutes < 0) {
                throw new IllegalArgumentException("Data/Hora invalidos!");
            }
        } catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
        LocalTime time = LocalTime.of(hour, minutes, 0);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        Schedule schedule = new Schedule();
        schedule.setScheduleStatus(cbSchStatus.getValue());
        schedule.setScheduledDateTime(dateTime);
        schedule.setRemunerationStatus(cbSchPayment.getValue());
        schedule.setValuesReference(cbValue.getValue());
        schedule.setScheduleType(cbSchType.getValue());
        return schedule;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
