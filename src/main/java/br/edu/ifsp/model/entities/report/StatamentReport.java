package br.edu.ifsp.application.view.model.entities.report;

import br.edu.ifsp.application.view.model.entities.schedule.Schedule;

import java.util.List;
//need to finish
public class StatamentReport implements Report{
    @Override
    public List<String> report() {
        return null;
    }

    public void addAllRows(List<Schedule> rows){
        //implementação temporária
        for (Schedule sc:
                rows) {
            System.out.println(sc.toString());
        }
    }
}
