package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class JournalModel implements Serializable {

    private List<TaskModel> journalModel;


    public JournalModel() {
        journalModel=new ArrayList<>();
    }

    public void setJournal(List<TaskModel> journal) {
        this.journalModel = journal;
    }

    public List<TaskModel> getJournalList() {
        return journalModel;
    }

    public TaskModel get(int i) {
        return journalModel.get(i);
    }

}
