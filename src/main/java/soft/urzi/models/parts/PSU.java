package soft.urzi.models.parts;

import com.fasterxml.jackson.annotation.JsonTypeName;
import soft.urzi.models.Part;
import soft.urzi.models.parts.enums.Rating;

@JsonTypeName("PSU")
public class PSU extends Part {
    private Rating rating;
    private int wattage;

    public String type = "PSU";

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setWattage(int wattage) {
        this.wattage = wattage;
    }

    public int getWattage() {
        return wattage;
    }

    @Override
    public String toString() {
        return super.toString() + "Rating: " + rating + "\nWattage: " + wattage + "\n";
    }
}
