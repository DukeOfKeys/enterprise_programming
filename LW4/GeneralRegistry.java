import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
class GeneralRegistry {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("✅ Student added!");
    }

    public void showAll() {
        if (students.isEmpty()) {
            System.out.println("⚠ No students in registry.");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println("[" + i + "] ---------------------");
            System.out.println(students.get(i));
        }
    }

    public void saveStudentByIndex(int index, String filename) {
        if (index < 0 || index >= students.size()) {
            System.out.println("❌ Invalid index!");
            return;
        }
        students.get(index).saveToTextFile(filename);
    }

    public void loadStudent(String filename) {
        Student s = Student.loadFromTextFile(filename);
        if (s != null) {
            students.add(s);
        }
    }

    public void showAboveMark(double threshold) {
        boolean found = false;
        for (Student s : students) {
            if (s.getAverageMark() > threshold) {
                System.out.println(s.name + " " + s.surname + " -> avg: " + s.getAverageMark());
                found = true;
            }
        }
        if (!found) {
            System.out.println("⚠ No students above that mark.");
        }
    }
    public void saveAll(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(students.size());
            for (Student s : students) {
                writer.println(s.name);
                writer.println(s.surname);
                writer.println(s.fatherName);
                writer.println(s.courseNumber);
                writer.println(s.groupNumber);

                writer.println(s.gradesInfo.allSessions.size());
                for (LocalRegistry.Session session : s.gradesInfo.allSessions) {
                    writer.println(session.sessionNumber);
                    writer.println(session.marks.size());
                    for (LocalRegistry.Session.Mark m : session.marks) {
                        writer.println(m.subject + "," + m.evaluation);
                    }
                }
            }
            System.out.println("✅ All students saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("❌ Error saving file: " + e.getMessage());
        }
    }

    public void loadAll(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            students.clear();
            int studentCount = Integer.parseInt(reader.readLine());

            for (int i = 0; i < studentCount; i++) {
                String name = reader.readLine();
                String surname = reader.readLine();
                String fatherName = reader.readLine();
                int courseNumber = Integer.parseInt(reader.readLine());
                int groupNumber = Integer.parseInt(reader.readLine());

                LocalRegistry localRegistry = new LocalRegistry();
                int sessionCount = Integer.parseInt(reader.readLine());
                for (int j = 0; j < sessionCount; j++) {
                    int sessionNumber = Integer.parseInt(reader.readLine());
                    LocalRegistry.Session session = new LocalRegistry.Session(sessionNumber);
                    int markCount = Integer.parseInt(reader.readLine());
                    for (int k = 0; k < markCount; k++) {
                        String[] parts = reader.readLine().split(",");
                        session.marks.add(new LocalRegistry.Session.Mark(parts[0], Integer.parseInt(parts[1])));
                    }
                    localRegistry.allSessions.add(session);
                }

                students.add(new Student(name, surname, fatherName, courseNumber, groupNumber, localRegistry));
            }
            System.out.println("✅ Loaded " + studentCount + " students from file: " + filename);
        } catch (IOException e) {
            System.err.println("❌ Error loading file: " + e.getMessage());
        }
    }

    public void sortBy(Comparator<Student> comparator) {
        students.sort(comparator);
        System.out.println("✅ Students sorted!");
    }

    public static Comparator<Student> BY_SURNAME = Comparator.comparing(s -> s.surname);
    public static Comparator<Student> BY_AVERAGE_DESC = (a, b) -> Double.compare(b.getAverageMark(), a.getAverageMark());
    public static Comparator<Student> BY_COURSE = Comparator.comparingInt(s -> s.courseNumber);
}