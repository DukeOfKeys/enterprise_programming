import java.util.Scanner;
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GeneralRegistry registry = new GeneralRegistry();

    public static Student inputStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter father name: ");
        String fatherName = scanner.nextLine();
        System.out.print("Enter course number: ");
        int courseNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter group number: ");
        int groupNumber = Integer.parseInt(scanner.nextLine());

        LocalRegistry localRegistry = new LocalRegistry();
        System.out.print("Enter number of sessions: ");
        int sessionCount = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= sessionCount; i++) {
            LocalRegistry.Session session = new LocalRegistry.Session(i);
            System.out.print("  Enter number of marks for session " + i + ": ");
            int markCount = Integer.parseInt(scanner.nextLine());
            for (int j = 0; j < markCount; j++) {
                System.out.print("    Subject: ");
                String subject = scanner.nextLine();
                System.out.print("    Mark (0-100): ");
                int eval = Integer.parseInt(scanner.nextLine());
                session.marks.add(new LocalRegistry.Session.Mark(subject, eval));
            }
            localRegistry.allSessions.add(session);
        }

        return new Student(name, surname, fatherName, courseNumber, groupNumber, localRegistry);
    }

  private static void showMenu() {
    System.out.println("\n=== MENU ===");
    System.out.println("1. Add new student");
    System.out.println("2. Load student from file");
    System.out.println("3. Save student to file");
    System.out.println("4. Show all students");
    System.out.println("5. Show students with average mark above threshold");
    System.out.println("6. Sort students");
    System.out.println("7. Exit");
    System.out.println("8. Save ALL students");
    System.out.println("9. Load ALL students");
    System.out.print("Choose: ");
}

    public static void main(String[] args) {
        boolean running = true;
        showMenu();
        while (running) {
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Student s = inputStudent();
                    registry.addStudent(s);
                    break;

                case "2":
                    System.out.print("Enter file name to load (e.g. student.txt): ");
                    registry.loadStudent(scanner.nextLine());
                    break;

                case "3":
                    System.out.print("Enter index of student to save: ");
                    int idx = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter filename: ");
                    registry.saveStudentByIndex(idx, scanner.nextLine());
                    break;

                case "4":
                    registry.showAll();
                    break;

                case "5":
                    System.out.print("Enter threshold: ");
                    double t = Double.parseDouble(scanner.nextLine());
                    registry.showAboveMark(t);
                    break;

                case "6":
                    System.out.println("Sort by:");
                    System.out.println(" 1. Surname (A-Z)");
                    System.out.println(" 2. Average mark (desc)");
                    System.out.println(" 3. Course number");
                    System.out.print("Choose: ");
                    String sortChoice = scanner.nextLine();
                    switch (sortChoice) {
                        case "1":
                            registry.sortBy(GeneralRegistry.BY_SURNAME);
                            break;
                        case "2":
                            registry.sortBy(GeneralRegistry.BY_AVERAGE_DESC);
                            break;
                        case "3":
                            registry.sortBy(GeneralRegistry.BY_COURSE);
                            break;
                        default:
                            System.out.println("❌ Invalid sort type");
                    }
                    break;

                case "7":
                    running = false;
                    System.out.println("👋 Exiting program...");
                    break;
                    case "8":
    System.out.print("Enter filename to save all (e.g. students.txt): ");
    registry.saveAll(scanner.nextLine());
    break;

case "9":
    System.out.print("Enter filename to load all (e.g. students.txt): ");
    registry.loadAll(scanner.nextLine());
    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}