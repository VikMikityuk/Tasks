package Model;

import java.util.List;


public class JournalModel {

    private List<TaskModel> journalModel;

    public JournalModel() {
        setJournal(journalModel);

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

    public void add(TaskModel task) {
        journalModel.add(task);
    }


}
