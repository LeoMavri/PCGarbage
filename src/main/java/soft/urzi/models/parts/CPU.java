package soft.urzi.models.parts;

import com.fasterxml.jackson.annotation.JsonTypeName;
import soft.urzi.models.Part;
import soft.urzi.models.parts.enums.Socket;

@JsonTypeName("CPU")
public class CPU extends Part {
    private Socket socket;
    private double frequency;
    private int cores;
    private int threads;

    public String type = "CPU";

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String toString() {
        return super.toString() + "Socket: " + socket + "\nFrequency: " + frequency + " GHz\nCores: " + cores + "\nThreads: " + threads + "\n";
    }
}
