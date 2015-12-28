package TaskFile;


import Model.JournalModel;
import com.google.gson.*;


/**Класс для преобразования объекта в Json
 *
 */

public class TaskToJson {

    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * Преобразование журнала задач в Json
     *
     * @param jm -журнал задач
     * @return json
     */
    public static String toJson(JournalModel jm) {
        String json = gson.toJson(jm);
        return json;
    }



    /**
     * Преобразование из Json в журнала задач.
     *
     * @param json -журнал задач
     * @return журнал задач
     */
    public static JournalModel fromJson(String json) {
        JournalModel dr2 = gson.fromJson(json, JournalModel.class);
        return dr2;
    }

}
