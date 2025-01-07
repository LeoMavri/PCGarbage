package soft.urzi.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import soft.urzi.models.parts.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,       // Use a name identifier for the type
        include = JsonTypeInfo.As.PROPERTY, // Include type info as a property in JSON
        property = "type"                // Specify the property name for the type
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = CPU.class, name = "CPU"),
        @JsonSubTypes.Type(value = GPU.class, name = "GPU"),
        @JsonSubTypes.Type(value = Storage.class, name = "Storage"),
        @JsonSubTypes.Type(value = Case.class, name = "Case"),
        @JsonSubTypes.Type(value = PSU.class, name = "PSU"),
        @JsonSubTypes.Type(value = RAM.class, name = "RAM"),
        @JsonSubTypes.Type(value = Motherboard.class, name = "Motherboard")
})
public abstract class Part {
    private Long id;

    private String name;
    private String brand;
    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nBrand: " + brand + "\nPrice: " + price + "\n";
    }
}