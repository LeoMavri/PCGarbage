package soft.urzi.models.parts;

import com.fasterxml.jackson.annotation.JsonTypeName;
import soft.urzi.models.Part;

@JsonTypeName("RAM")
public class RAM extends Part {
    private double size;
    private double frequency;
    private int ddrType;

    public String type = "RAM";

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public int getDdrType() {
        return ddrType;
    }

    public void setDdrType(int ddrType) {
        this.ddrType = ddrType;
    }

    @Override
    public String toString() {
        return super.toString() + "Size: " + size + "\nFrequency: " + frequency + "GHz\nDDR Type: " + ddrType + "\n";
    }
}
