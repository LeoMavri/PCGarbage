package soft.urzi.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import soft.urzi.interfaces.IComputerRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComputerRepository implements IComputerRepository {
    private List<Computer> computers;
    private static final String COMPUTERS_FILE = "/home/mavri/Documents/PcGarbage/src/main/computers.json";

    public ComputerRepository() {
        this.computers = new ArrayList<>();
    }

    @Override
    public List<Computer> getAllComputers() {
        return computers;
    }

    @Override
    public void deleteComputer(Long id) {
        computers.removeIf(computer -> Objects.equals(computer.getId(), id));
    }

    @Override
    public void addComputer(Computer computer) {
        computers.add(computer);
    }

    @Override
    public Computer getComputerById(Long id) {
        return computers.stream()
                .filter(computer -> computer.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateComputer(Long id, Computer computer) {
        for (int i = 0; i < computers.size(); i++) {
            if (computers.get(i).getId().equals(id)) {
                computers.set(i, computer);
                return;
            }
        }
    }

    @Override
    public void saveToDisk() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(COMPUTERS_FILE), computers);
        } catch (IOException e) {
            System.err.println("Error saving computers to disk: " + e.getMessage());
        }
    }

    @Override
    public void loadFromDisk() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(COMPUTERS_FILE);
            if (file.exists()) {
                computers = objectMapper.readValue(file, new TypeReference<List<Computer>>() {
                });
            } else {
                computers = new ArrayList<>(); // Initialize as empty if file doesn't exist
            }
        } catch (IOException e) {
            System.err.println("Error loading computers from disk: " + e.getMessage());
            computers = new ArrayList<>(); // Initialize as empty in case of error
        }
    }
}