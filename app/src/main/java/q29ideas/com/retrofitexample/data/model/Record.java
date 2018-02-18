package q29ideas.com.retrofitexample.data.model;

/**
 * Created by Bobby Jones on 2/17/2018.
 */import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sequence")
    @Expose
    private String sequence;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("calories")
    @Expose
    private String calories;
    @SerializedName("cardio_type")
    @Expose
    private String cardioType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCardioType() {
        return cardioType;
    }

    public void setCardioType(String cardioType) {
        this.cardioType = cardioType;
    }
}