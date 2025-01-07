package soft.urzi.models.parts;

import soft.urzi.models.Part;

public class GPU extends Part {
    private String memory;
    private double tflops;

    public double getTflops() {
        return tflops;
    }

    public void setTflops(double tflops) {
        this.tflops = tflops;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return super.toString() + "Memory: " + memory + "\nTflops: " + tflops + "\n";
    }
}
