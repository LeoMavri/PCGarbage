package soft.urzi.models.parts;

import com.fasterxml.jackson.annotation.JsonTypeName;
import soft.urzi.models.Part;
import soft.urzi.models.parts.enums.Size;
import soft.urzi.models.parts.enums.Socket;

@JsonTypeName("Motherboard")
public class Motherboard extends Part {
    private Socket cpuSocket;
    private String chipset;
    private Size formFactor;

    public String type = "Motherboard";

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Size getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(Size formFactor) {
        this.formFactor = formFactor;
    }

    public Socket getCpuSocket() {
        return cpuSocket;
    }

    public void setCpuSocket(Socket cpuSocket) {
        this.cpuSocket = cpuSocket;
    }

    @Override
    public String toString() {
        return super.toString() + "Chipset: " + chipset + "\nSocket: " + cpuSocket + "\nForm Factor: " + formFactor + "\n";
    }
}
