package soft.urzi.interfaces;

import soft.urzi.models.Part;

import java.util.List;

public interface IPartsRepository {
    List<Part> getAllParts();

    Part getPartById(Long id);

    void addPart(Part part);

    void updatePart(Long id, Part part);

    void deletePart(Long id);

    void saveToDisk();

    void loadFromDisk();
}
