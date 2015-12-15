package Controller;


import Model.JournalModel;
import com.google.gson.*;

public class TaskToJson {

    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    public static String toJson(JournalModel jm) {
        String json = gson.toJson(jm);
        return json;
    }


    public static JournalModel fromJson(String json) {
        JournalModel dr2 = gson.fromJson(json, JournalModel.class);
        return dr2;
    }

}
