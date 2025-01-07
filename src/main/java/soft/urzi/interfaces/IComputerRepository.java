package soft.urzi.interfaces;

import soft.urzi.models.Computer;

import java.util.List;

public interface IComputerRepository {
    List<Computer> getAllComputers();

    void deleteComputer(Long id);

    void addComputer(Computer computer);

    Computer getComputerById(Long id);

    void updateComputer(Long id, Computer computer);

    void saveToDisk();

    void loadFromDisk();
}
