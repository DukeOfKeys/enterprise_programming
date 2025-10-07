import java.util.ArrayList;

class LocalRegistry {
    public static class Session {
        public static class Mark {
            String subject;
            int evaluation;

            public Mark(String subject, int evaluation) {
                this.subject = subject;
                this.evaluation = evaluation;
            }

            @Override
            public String toString() {
                return subject + ":" + evaluation;
            }
        }

        int sessionNumber;
        ArrayList<Mark> marks = new ArrayList<>();

        public Session(int sessionNumber) {
            this.sessionNumber = sessionNumber;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Session ").append(sessionNumber).append("\n");
            for (Mark m : marks) {
                sb.append("  ").append(m.toString()).append("\n");
            }
            return sb.toString();
        }
    }

    ArrayList<Session> allSessions = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Session s : allSessions) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    public double getAverageMark() {
        int total = 0;
        int count = 0;
        for (Session s : allSessions) {
            for (Session.Mark m : s.marks) {
                total += m.evaluation;
                count++;
            }
        }
        return count == 0 ? 0 : (double) total / count;
    }
}