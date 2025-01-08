package soft.urzi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartsRepositoryTest {
    private PartsRepository partsRepository;

    @BeforeEach
    void setUp() {
        partsRepository = new PartsRepository();
        partsRepository.loadFromDisk(); // Load data into the repository if applicable
    }

    @Test
    void getAllParts() {
        List<Part> parts = partsRepository.getAllParts();
        assertNotNull(parts, "The parts list should not be null.");
        assertEquals(0, parts.size(), "Initially, the list should be empty.");
    }

    @Test
    void getPartById() {
        Part part = createTestPart("Test Part", "Test Brand", 100);
        partsRepository.addPart(part);

        Part retrieved = partsRepository.getPartById(part.getId());
        assertNotNull(retrieved, "Part should be retrieved by ID.");
        assertEquals("Test Part", retrieved.getName(), "The retrieved part's name should match.");
        assertEquals(part.getId(), retrieved.getId(), "The retrieved part's ID should match.");
    }

    @Test
    void addPart() {
        Part part = createTestPart("New Part", "Brand A", 50);
        partsRepository.addPart(part);

        List<Part> parts = partsRepository.getAllParts();
        assertEquals(1, parts.size(), "The repository should contain 1 part after adding.");
        assertEquals("New Part", parts.getFirst().getName(), "The added part's name should match.");
    }

    @Test
    void deletePart() {
        Part part = createTestPart("Part to Delete", "Brand Z", 30);
        partsRepository.addPart(part);

        partsRepository.deletePart(part.getId());
        List<Part> parts = partsRepository.getAllParts();
        assertEquals(0, parts.size(), "The part should be removed from the repository.");
    }

    @Test
    void saveToDisk() {
        Part part = createTestPart("Persistent Part", "Brand P", 99);
        partsRepository.addPart(part);

        assertDoesNotThrow(() -> partsRepository.saveToDisk(), "Saving to disk should not throw exceptions.");
    }

    private Part createTestPart(String name, String brand, int price) {
        Part part = new Part() {};
        part.setId(PartsRepository.getNextId());
        part.setName(name);
        part.setBrand(brand);
        part.setPrice(price);

        return part;
    }
}