package soft.urzi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import soft.urzi.models.parts.*;

import java.io.File;
import java.io.IOException;

public class Computer implements soft.urzi.interfaces.IComputer {
    private static final String ID_FILE = "/home/mavri/Documents/PcGarbage/src/main/computer_id.json";
    private String name;

    private Case computerCase;
    private Motherboard motherboard;
    private CPU cpu;
    private GPU gpu;
    private RAM ram;
    private Storage storage;
    private PSU psu;
    private static Long currentId = 0L; // Static shared variable for generating unique IDs
    // Remove `static` from ID
    private Long computerId;

    // Constructor
    public Computer() {
        this.computerId = getNextId(); // Assign a unique ID to each instance
    }

    // Generate the next unique ID
    private static synchronized Long getNextId() {
        loadId(); // Ensure the current ID is loaded from file
        currentId++; // Increment the ID
        saveId(); // Persist the new ID to the file
        return currentId; // Return the incremented ID
    }

    // Load the ID from the persistent `ID_FILE`
    private static void loadId() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(ID_FILE);
            if (file.exists()) {
                currentId = objectMapper.readValue(file, Long.class);
            }
        } catch (IOException e) {
            System.err.println("Failed to load ID from file: " + e.getMessage());
        }
    }

    @Override
    public Long getId() {
        return computerId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Case getComputerCase() {
        return computerCase;
    }

    @Override
    public void setComputerCase(Case computerCase) {
        this.computerCase = computerCase;
    }

    @Override
    public Motherboard getMotherboard() {
        return motherboard;
    }

    @Override
    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    @Override
    public CPU getCpu() {
        return cpu;
    }

    @Override
    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    @Override
    public GPU getGpu() {
        return gpu;
    }

    @Override
    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    @Override
    public RAM getRam() {
        return ram;
    }

    @Override
    public void setRam(RAM ram) {
        this.ram = ram;
    }

    @Override
    public Storage getStorage() {
        return storage;
    }

    @Override
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public PSU getPsu() {
        return psu;
    }

    @Override
    public void setPsu(PSU psu) {
        this.psu = psu;
    }

    // Save the current ID to the persistent `ID_FILE`
    private static void saveId() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(ID_FILE), currentId);
        } catch (IOException e) {
            System.err.println("Failed to save ID to file: " + e.getMessage());
        }
    }

    @JsonGetter("id") // Ensure this maps properly during serialization
    public Long getComputerId() {
        return computerId;
    }

    public void setComputerId(Long computerId) {
        this.computerId = computerId; // Allow setting ID manually during deserialization if needed
    }

    @Override
    public String toString() {
        return "ID: " + computerId + "\nName: " + name +
                "\nCase: " + computerCase +
                "\nMotherboard: " + motherboard +
                "\nCPU: " + cpu +
                "\nGPU: " + gpu +
                "\nRAM: " + ram +
                "\nStorage: " + storage +
                "\nPSU: " + psu + "\n";
    }
}