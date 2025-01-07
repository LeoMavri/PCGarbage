package soft.urzi.models.parts;

import com.fasterxml.jackson.annotation.JsonTypeName;
import soft.urzi.models.Part;
import soft.urzi.models.parts.enums.Size;


@JsonTypeName("Case")
public class Case extends Part {
    private Size formFactor;

    public String type = "Case";

    public Size getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(Size formFactor) {
        this.formFactor = formFactor;
    }

    @Override
    public String toString() {
        return super.toString() + "Form Factor: " + formFactor + "\n";
    }
}
