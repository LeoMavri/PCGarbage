package soft.urzi.models.parts;

import soft.urzi.models.Part;
import soft.urzi.models.parts.enums.StorageType;

public class Storage extends Part {
    private double capacity; // capacity (in GB)
    private StorageType type;

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public StorageType getType() {
        return type;
    }

    public void setType(StorageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + "Capacity: " + capacity + "\nType: " + type + "\n";
    }
}
