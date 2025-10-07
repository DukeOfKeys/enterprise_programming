import java.io.*;

class Student {
    String name;
    String surname;
    String fatherName;
    int courseNumber;
    int groupNumber;
    LocalRegistry gradesInfo;

    public Student() {
        this.gradesInfo = new LocalRegistry();
    }

    public Student(String name, String surname, String fatherName,
                   int courseNumber, int groupNumber, LocalRegistry gradesInfo) {
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
        this.courseNumber = courseNumber;
        this.groupNumber = groupNumber;
        this.gradesInfo = gradesInfo;
    }

    @Override
    public String toString() {
        return name + " " + surname + " (" + fatherName + ")\n" +
               "Course: " + courseNumber + ", Group: " + groupNumber + "\n" +
               gradesInfo.toString() +
               String.format("Average mark: %.2f", getAverageMark());
    }

    public double getAverageMark() {
        return gradesInfo.getAverageMark();
    }

    public void saveToTextFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(name);
            writer.println(surname);
            writer.println(fatherName);
            writer.println(courseNumber);
            writer.println(groupNumber);
            writer.println(gradesInfo.allSessions.size());
            for (LocalRegistry.Session s : gradesInfo.allSessions) {
                writer.println(s.sessionNumber);
                writer.println(s.marks.size());
                for (LocalRegistry.Session.Mark m : s.marks) {
                    writer.println(m.subject + "," + m.evaluation);
                }
            }
            System.out.println("✅ Saved to text file: " + filename);
        } catch (IOException e) {
            System.err.println("❌ Error saving to file: " + e.getMessage());
        }
    }

    public static Student loadFromTextFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String name = reader.readLine();
            String surname = reader.readLine();
            String fatherName = reader.readLine();
            int courseNumber = Integer.parseInt(reader.readLine());
            int groupNumber = Integer.parseInt(reader.readLine());

            LocalRegistry registry = new LocalRegistry();
            int sessionCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < sessionCount; i++) {
                int sessionNumber = Integer.parseInt(reader.readLine());
                LocalRegistry.Session session = new LocalRegistry.Session(sessionNumber);
                int markCount = Integer.parseInt(reader.readLine());
                for (int j = 0; j < markCount; j++) {
                    String[] parts = reader.readLine().split(",");
                    session.marks.add(new LocalRegistry.Session.Mark(parts[0], Integer.parseInt(parts[1])));
                }
                registry.allSessions.add(session);
            }

            System.out.println("✅ Loaded from text file: " + filename);
            return new Student(name, surname, fatherName, courseNumber, groupNumber, registry);
        } catch (IOException e) {
            System.err.println("❌ Error reading file: " + e.getMessage());
            return null;
        }
    }
}