package soft.urzi.models.parts;

import soft.urzi.models.Part;
import soft.urzi.models.parts.enums.Size;


public class Case extends Part {
    private Size formFactor;

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
