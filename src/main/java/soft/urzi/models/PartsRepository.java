package soft.urzi.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import soft.urzi.models.parts.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PartsRepository implements soft.urzi.interfaces.IPartsRepository {
    private static final String PARTS_FILE = "/home/mavri/Documents/PcGarbage/src/main/parts.json";
    private static final String ID_FILE = "/home/mavri/Documents/PcGarbage/src/main/parts_id.json";

    private static Long idCounter = 0L;
    private List<Part> parts;

    public PartsRepository() {
        parts = new ArrayList<>();
        loadId(); // Load the ID counter from disk during initialization
    }

    @Override
    public List<Part> getAllParts() {
        return parts;
    }

    @Override
    public Part getPartById(Long id) {
        return parts.stream().filter(part -> Objects.equals(part.getId(), id)).findFirst().orElse(null);
    }

    @Override
    public void addPart(Part part) {
        part.setId(getNextId()); // Assign a unique ID to the part
        parts.add(part);
        saveId(); // Persist the updated ID counter
    }

    @Override
    public void updatePart(Long id, Part part) {
        for (int i = 0; i < parts.size(); i++) {
            if (Objects.equals(parts.get(i).getId(), id)) {
                parts.set(i, part);
                return;
            }
        }
    }

    @Override
    public void deletePart(Long id) {
        parts.removeIf(part -> Objects.equals(part.getId(), id));
    }

    @Override
    public void saveToDisk() {
        for (Part part : parts) {
            if (part instanceof CPU) {
                ((CPU) part).type = "CPU";
            } else if (part instanceof GPU) {
                ((GPU) part).type = "GPU";
            } else if (part instanceof Storage) {
                ((Storage) part).type = "Storage";
            } else if (part instanceof Case) {
                ((Case) part).type = "Case";
            } else if (part instanceof PSU) {
                ((PSU) part).type = "PSU";
            } else if (part instanceof RAM) {
                ((RAM) part).type = "RAM";
            } else if (part instanceof Motherboard) {
                ((Motherboard) part).type = "Motherboard";
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(PARTS_FILE), parts);
        } catch (IOException e) {
            System.err.println("Error saving parts to disk: " + e.getMessage());
        }
    }

    @Override
    public void loadFromDisk() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(PARTS_FILE);
            if (file.exists()) {
                parts = objectMapper.readValue(file, new TypeReference<>() {
                });
            } else {
                parts = new ArrayList<>(); // Initialize an empty list if the file doesn't exist
            }
        } catch (IOException e) {
            System.err.println("Error loading parts from disk: " + e.getMessage());
            parts = new ArrayList<>(); // Ensure the list is initialized even if loading fails
        }
    }

    /**
     * Retrieves the next available unique ID.
     *
     * @return the next unique ID as a Long
     */
    public static synchronized Long getNextId() {
        return ++idCounter;
    }

    /**
     * Loads the current `idCounter` from disk.
     * This ensures the ID sequence continues correctly after restarting the application.
     */
    private static void loadId() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(ID_FILE);
            if (file.exists()) {
                idCounter = objectMapper.readValue(file, Long.class);
            }
        } catch (IOException e) {
            System.err.println("Error loading ID from disk: " + e.getMessage());
        }
    }

    /**
     * Saves the current `idCounter` to disk persistently.
     */
    private static void saveId() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(ID_FILE), idCounter);
        } catch (IOException e) {
            System.err.println("Error saving ID to disk: " + e.getMessage());
        }
    }
}