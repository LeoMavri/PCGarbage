package soft.urzi;

import soft.urzi.models.Computer;
import soft.urzi.models.ComputerRepository;
import soft.urzi.models.Part;
import soft.urzi.models.PartsRepository;
import soft.urzi.models.parts.*;
import soft.urzi.models.parts.enums.Rating;
import soft.urzi.models.parts.enums.Size;
import soft.urzi.models.parts.enums.Socket;
import soft.urzi.models.parts.enums.StorageType;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final PartsRepository partsRepository = new PartsRepository();
    private static final ComputerRepository computerRepository = new ComputerRepository();

    public static void main(String[] args) {
        partsRepository.loadFromDisk();
        computerRepository.loadFromDisk();

        System.out.println("Loaded from disk " + partsRepository.getAllParts().size() + " parts and " + computerRepository.getAllComputers().size() + " computers.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. View all parts");
                System.out.println("2. View all computers");
                System.out.println("3. Add a part");
                System.out.println("4. Build a new computer");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> viewAllParts();
                    case 2 -> viewAllComputers();
                    case 3 -> addPart(scanner);
                    case 4 -> buildComputer(scanner);
                    case 5 -> {
                        scanner.close();
                        partsRepository.saveToDisk();
                        computerRepository.saveToDisk();
                        System.out.println("Saved to disk.");
                        System.out.println("Exiting... Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static void viewAllParts() {
        List<Part> parts = partsRepository.getAllParts();
        if (parts.isEmpty()) {
            System.out.println("No parts available.");
            return;
        }
        System.out.println("\n--- All Parts ---");
        for (Part part : parts) {
            System.out.println(part);
        }
    }

    private static void viewAllComputers() {
        List<Computer> computers = computerRepository.getAllComputers();
        if (computers.isEmpty()) {
            System.out.println("No computers available.");
            return;
        }
        System.out.println("\n--- All Computers ---");
        for (Computer computer : computers) {
//            System.out.println("ID: " + computer.getId() + ", Name: " + computer.getName());
            System.out.println(computer);
        }
    }

    private static void addPart(Scanner scanner) {
        System.out.println("\n--- Add a New Part ---");
        System.out.println("Available part types: 1. CPU, 2. GPU, 3. Storage, 4. Case, 5. PSU, 6. RAM, 7. Motherboard");
        System.out.print("Choose a part type: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        Part part;
        switch (typeChoice) {
            case 1 -> part = new CPU();
            case 2 -> part = new GPU();
            case 3 -> part = new Storage();
            case 4 -> part = new Case();
            case 5 -> part = new PSU();
            case 6 -> part = new RAM();
            case 7 -> part = new Motherboard();
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        System.out.print("Enter part name: ");
        part.setName(scanner.nextLine());
        System.out.print("Enter brand: ");
        part.setBrand(scanner.nextLine());
        System.out.print("Enter price: ");
        part.setPrice(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        // Add additional configuration based on part type
        configurePart(scanner, part);

        partsRepository.addPart(part);
        System.out.println("Part added successfully!");
    }

    private static void configurePart(Scanner scanner, Part part) {
        switch (part) {
            case CPU cpu -> {
                System.out.println("Available socket types: ");
                Arrays.stream(Socket.values()).forEach(socket -> System.out.println("- " + socket.name()));

                System.out.print("Enter socket type: ");

                cpu.setSocket(Socket.valueOf(scanner.nextLine()));
                System.out.print("Enter frequency (GHz): ");

                cpu.setFrequency(scanner.nextDouble());
                System.out.print("Enter number of cores: ");

                cpu.setCores(scanner.nextInt());
                System.out.print("Enter number of threads: ");

                cpu.setThreads(scanner.nextInt());
                scanner.nextLine();
            }
            case GPU gpu -> {
                System.out.print("Enter memory size: ");
                gpu.setMemory(scanner.nextLine());

                System.out.print("Enter TFLOPS rating: ");
                gpu.setTflops(scanner.nextDouble());

                scanner.nextLine();
            }
            case Storage storage -> {
                System.out.print("Enter capacity (in GB): ");
                storage.setCapacity(scanner.nextDouble());

                scanner.nextLine();

                System.out.print("Enter storage type (e.g., SSD, HDD): ");
                storage.setStorageType(StorageType.valueOf(scanner.nextLine()));
            }
            case Case computerCase -> {
                System.out.print("Enter form factor (e.g., ATX, MicroATX): ");
                computerCase.setFormFactor(Size.valueOf(scanner.nextLine()));
            }
            case PSU psu -> {
                System.out.print("Enter wattage: ");
                psu.setWattage(scanner.nextInt());
                scanner.nextLine();

                System.out.print("Enter efficiency rating (e.g., Bronze, Gold): ");
                psu.setRating(Rating.valueOf(scanner.nextLine()));
            }
            case RAM ram -> {
                System.out.print("Enter size (in GB): ");
                ram.setSize(scanner.nextDouble());

                System.out.print("Enter frequency (MHz): ");
                ram.setFrequency(scanner.nextDouble());

                System.out.print("Enter DDR type: ");
                ram.setDdrType(scanner.nextInt());

                scanner.nextLine();
            }
            case Motherboard motherboard -> {
                System.out.print("Enter chipset: ");
                motherboard.setChipset(scanner.nextLine());

                System.out.print("Enter CPU socket type: ");
                motherboard.setCpuSocket(Socket.valueOf(scanner.nextLine()));

                System.out.print("Enter form factor: ");
                motherboard.setFormFactor(Size.valueOf(scanner.nextLine()));
            }
            case null, default -> {
                System.out.println("Invalid part type.");
            }
        }
    }

    private static void buildComputer(Scanner scanner) {
        System.out.println("\n--- Build a New Computer ---");
        Computer computer = new Computer();

        // Set computer name
        System.out.print("Enter computer name: ");
        computer.setName(scanner.nextLine());

        // Choose CPU
        System.out.println("\nChoose a CPU:");
        List<Part> cpus = partsRepository.getAllParts().stream()
                .filter(part -> part instanceof CPU)
                .toList();

        if (cpus.isEmpty()) {
            System.out.println("No CPUs available. Add a CPU first.");
            return;
        }

        cpus.forEach(cpu -> System.out.println(cpu.getId() + ": " + cpu.getName() + " (" + cpu.getBrand() + ")"));
        System.out.print("Enter CPU ID: ");
        long cpuId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        CPU selectedCPU = (CPU) partsRepository.getPartById(cpuId);
        if (selectedCPU == null) {
            System.out.println("Invalid CPU ID! Operation aborted.");
            return;
        }
        computer.setCpu(selectedCPU);

        // Choose Motherboard
        System.out.println("\nChoose a Motherboard:");
        List<Part> motherboards = partsRepository.getAllParts().stream()
                .filter(part -> part instanceof Motherboard)
                .toList();

        if (motherboards.isEmpty()) {
            System.out.println("No motherboards available. Add a motherboard first.");
            return;
        }

        motherboards.forEach(mb -> System.out.println(mb.getId() + ": " + mb.getName() + " (" + mb.getBrand() + ")"));
        System.out.print("Enter Motherboard ID: ");
        long motherboardId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Motherboard selectedMotherboard = (Motherboard) partsRepository.getPartById(motherboardId);
        if (selectedMotherboard == null) {
            System.out.println("Invalid Motherboard ID! Operation aborted.");
            return;
        }

        // Compatibility check: CPU and Motherboard sockets should match
        if (!selectedCPU.getSocket().equals(selectedMotherboard.getCpuSocket())) {
            System.out.println("Compatibility error: Selected CPU socket (" + selectedCPU.getSocket() +
                    ") and Motherboard socket (" + selectedMotherboard.getCpuSocket() + ") do not match.");
            return;
        }
        computer.setMotherboard(selectedMotherboard);

        // Choose GPU
        System.out.println("\nChoose a GPU:");
        List<Part> gpus = partsRepository.getAllParts().stream()
                .filter(part -> part instanceof GPU)
                .toList();

        if (gpus.isEmpty()) {
            System.out.println("No GPUs available. Add a GPU first.");
        } else {
            gpus.forEach(gpu -> System.out.println(gpu.getId() + ": " + gpu.getName() + " (" + gpu.getBrand() + ")"));
            System.out.print("Enter GPU ID (or 0 to skip): ");
            long gpuId = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            if (gpuId != 0) {
                GPU selectedGPU = (GPU) partsRepository.getPartById(gpuId);
                if (selectedGPU == null) {
                    System.out.println("Invalid GPU ID! Operation aborted.");
                    return;
                }
                computer.setGpu(selectedGPU);
            }
        }

        // Choose RAM
        System.out.println("\nChoose RAM:");
        List<Part> rams = partsRepository.getAllParts().stream()
                .filter(part -> part instanceof RAM)
                .toList();

        if (rams.isEmpty()) {
            System.out.println("No RAMs available. Add RAM first.");
            return;
        }

        rams.forEach(ram -> System.out.println(ram.getId() + ": " + ram.getName() + " (" + ram.getBrand() + ")"));
        System.out.print("Enter RAM ID: ");
        long ramId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        RAM selectedRAM = (RAM) partsRepository.getPartById(ramId);
        if (selectedRAM == null) {
            System.out.println("Invalid RAM ID! Operation aborted.");
            return;
        }
        computer.setRam(selectedRAM);

        // Choose Storage
        System.out.println("\nChoose a Storage Device:");
        List<Part> storages = partsRepository.getAllParts().stream()
                .filter(part -> part instanceof Storage)
                .toList();

        if (storages.isEmpty()) {
            System.out.println("No Storage devices available. Add one first.");
            return;
        }

        storages.forEach(storage -> System.out.println(storage.getId() + ": " + storage.getName() + " (" + storage.getBrand() + ")"));
        System.out.print("Enter Storage ID: ");
        long storageId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Storage selectedStorage = (Storage) partsRepository.getPartById(storageId);
        if (selectedStorage == null) {
            System.out.println("Invalid Storage ID! Operation aborted.");
            return;
        }
        computer.setStorage(selectedStorage);

        // Choose PSU
        System.out.println("\nChoose a PSU:");
        List<Part> psus = partsRepository.getAllParts().stream()
                .filter(part -> part instanceof PSU)
                .toList();

        if (psus.isEmpty()) {
            System.out.println("No PSUs available. Add a PSU first.");
            return;
        }

        psus.forEach(psu -> System.out.println(psu.getId() + ": " + psu.getName() + " (" + psu.getBrand() + ")"));
        System.out.print("Enter PSU ID: ");
        long psuId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        PSU selectedPSU = (PSU) partsRepository.getPartById(psuId);
        if (selectedPSU == null) {
            System.out.println("Invalid PSU ID! Operation aborted.");
            return;
        }
        computer.setPsu(selectedPSU);

        // Choose Case
        System.out.println("\nChoose a Case:");
        List<Part> cases = partsRepository.getAllParts().stream()
                .filter(part -> part instanceof Case)
                .toList();

        if (cases.isEmpty()) {
            System.out.println("No Cases available. Add a Case first.");
            return;
        }

        cases.forEach(c -> System.out.println(c.getId() + ": " + c.getName() + " (" + c.getBrand() + ")"));
        System.out.print("Enter Case ID: ");
        long caseId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Case selectedCase = (Case) partsRepository.getPartById(caseId);
        if (selectedCase == null) {
            System.out.println("Invalid Case ID! Operation aborted.");
            return;
        }
        computer.setComputerCase(selectedCase);

        // Save the computer
        computerRepository.addComputer(computer);
        System.out.println("\nComputer built successfully!");
    }
}