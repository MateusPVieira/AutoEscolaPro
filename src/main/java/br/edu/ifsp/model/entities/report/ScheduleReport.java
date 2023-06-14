package br.edu.ifsp.model.entities.report;

import br.edu.ifsp.model.entities.schedule.Schedule;

import java.util.List;
//need to finish
public class ScheduleReport implements Report{
    @Override
    public List<String> report() {
        return null;
    }

    public void addAllRows(List<Schedule> rows){
        //implementação temporária
        for (Schedule sc:
             rows) {

        }
    }
}
