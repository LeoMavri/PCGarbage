package soft.urzi.models.parts;

import soft.urzi.models.Part;
import soft.urzi.models.parts.enums.Size;
import soft.urzi.models.parts.enums.Socket;

public class Motherboard extends Part {
    private Socket cpuSocket;
    private String chipset;
    private Size formFactor;

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
    }

    @Override
    public String toString() {
        return super.toString() + "Chipset: " + chipset + "\nSocket: " + cpuSocket + "\nForm Factor: " + formFactor + "\n";
    }
}
