package soft.urzi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComputerRepositoryTest {
    private ComputerRepository computerRepository;

    @BeforeEach
    void setUp() {
        computerRepository = new ComputerRepository();
        computerRepository.loadFromDisk(); // Pre-load repository for meaningful tests.
    }

    @Test
    void getAllComputers() {
        List<Computer> computers = computerRepository.getAllComputers();
        assertNotNull(computers, "The computer list should not be null.");
        assertEquals(0, computers.size(), "Initially, the list should be empty.");
    }

    @Test
    void addComputer() {
        Computer computer = new Computer();
        computer.setComputerId(1L);
        computerRepository.addComputer(computer);

        List<Computer> computers = computerRepository.getAllComputers();
        assertEquals(1, computers.size(), "Should have exactly 1 computer after adding.");
        assertEquals(1L, computers.getFirst().getId(), "The added computer's ID should match.");
    }

    @Test
    void deleteComputer() {
        Computer computer = new Computer();
        computer.setComputerId(1L);
        computerRepository.addComputer(computer);
        assertEquals(1, computerRepository.getAllComputers().size(), "Should have 1 computer before deletion.");

        computerRepository.deleteComputer(1L);

        List<Computer> computers = computerRepository.getAllComputers();
        assertTrue(computers.isEmpty(), "The list should be empty after deleting the computer.");
    }

    @Test
    void getComputerById() {
        Computer computer = new Computer();
        computer.setComputerId(1L);
        computer.setName("Gaming PC");
        computerRepository.addComputer(computer);

        Computer retrieved = computerRepository.getComputerById(1L);
        assertNotNull(retrieved, "The computer should be found by its ID.");
        assertEquals("Gaming PC", retrieved.getName(), "The computer's name should match.");
    }

    @Test
    void saveToDisk() {
        Computer computer = new Computer();
        computer.setComputerId(1L);
        computerRepository.addComputer(computer);

        assertDoesNotThrow(() -> computerRepository.saveToDisk(), "Saving to disk should not throw exceptions.");
    }

    @Test
    void loadFromDisk() {
        List<Computer> computersBefore = computerRepository.getAllComputers();
        assertEquals(0, computersBefore.size(), "Repository should initially be empty.");

        computerRepository.loadFromDisk();
        List<Computer> computersAfter = computerRepository.getAllComputers();
        assertNotNull(computersAfter, "Computers list should not be null after loading from disk.");
    }
}