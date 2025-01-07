package soft.urzi.interfaces;

import soft.urzi.models.parts.*;

public interface IComputer {
    Long getId();

    String getName();

    void setName(String name);

    Case getComputerCase();

    void setComputerCase(Case computerCase);

    Motherboard getMotherboard();

    void setMotherboard(Motherboard motherboard);

    CPU getCpu();

    void setCpu(CPU cpu);

    GPU getGpu();

    void setGpu(GPU gpu);

    RAM getRam();

    void setRam(RAM ram);

    Storage getStorage();

    void setStorage(Storage storage);

    PSU getPsu();

    void setPsu(PSU psu);
}
