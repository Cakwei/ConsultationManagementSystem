package org.cakwei;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Consultation {
    private String studentId;
    private final String consultationId;
    private String lecturerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String studentFeedback;
    private String lecturerFeedback;
    private ConsultationStatus status;
    private boolean notifyUserOfApproval;
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");

    public Consultation(String consultationId, String studentId, String lecturerId, LocalDateTime startDate, LocalDateTime endDate, String studentFeedback, String  lecturerFeedback, ConsultationStatus status, boolean notifyUserOfApproval) {
        this.consultationId = consultationId;
        this.studentId = studentId;
        this.lecturerId = lecturerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.studentFeedback = studentFeedback;
        this.lecturerFeedback = lecturerFeedback;
        this.status = status;
        this.notifyUserOfApproval = notifyUserOfApproval;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime DateTime) {
        this.startDate = DateTime;
    }

    public String convert() {
        return String.join("::", consultationId, studentId, lecturerId, startDate.toString(), endDate.toString(), studentFeedback, lecturerFeedback, status.toString(), Boolean.toString(notifyUserOfApproval));
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public ConsultationStatus getStatus() {
        return status;
    }
    public void setStatus(ConsultationStatus status) {
        this.status = status;
    }

    public static Comparator<String> StuNameComparator = new Comparator<String>() {
        // Comparing attributes of students
        public int compare(String consultation1, String consultation2) {
            LocalDateTime date1
                    = LocalDateTime.parse(consultation1, dateFormat);
            LocalDateTime date2
                    = LocalDateTime.parse(consultation2, dateFormat);
            // Returning in ascending order
            return date1.compareTo(
                    date2);

            // descending order
            // return
            // StudentName2.compareTo(StudentName1);
        }
    };

    public String getStudentId() {
        return (studentId == null) ? "" : studentId;
    }
    public void setStudentId(String id) {
        this.studentId = id;
    }
    public String getConsultationId() {
        return consultationId;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime DateTime) {
        this.endDate = DateTime;
    }
    public String getStudentFeedback() {
        return studentFeedback;
    }
    public void setStudentFeedback(String message) {
        this.studentFeedback = message;
    } public String getLecturerFeedback() {
        return lecturerFeedback;
    }
    public void setLecturerFeedback(String message) {
        this.lecturerFeedback =  message;
    }
    public boolean getNotifyUserOfApproval() {
        return notifyUserOfApproval;
    }
    public void setNotifyUserOfApproval(boolean Boolean) {
        this.notifyUserOfApproval = Boolean;
    }
}


