package soft.urzi.models.parts;

import com.fasterxml.jackson.annotation.JsonTypeName;
import soft.urzi.models.Part;
import soft.urzi.models.parts.enums.StorageType;

@JsonTypeName("Storage")
public class Storage extends Part {
    private double capacity; // capacity (in GB)
    public String type = "Storage";
    private StorageType storageType;

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    @Override
    public String toString() {
        return super.toString() + "Capacity: " + capacity + "\nType: " + storageType + "\n";
    }
}
