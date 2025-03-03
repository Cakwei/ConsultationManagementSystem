package org.cakwei;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ConsultationManagement extends FileManagement {
    private BufferedWriter writer;

    public Consultation findConsultationById(String id) {
        List<Consultation> consultations = readConsultation();
        for (Consultation consultation : consultations) {
            System.out.println("CHECK: " + consultation.getConsultationId() + " " + id);
            if (consultation.getConsultationId().equals(id))
                return consultation;
        }
        return null;
    }

    public void checkConsultationHasPassed() {
        List<Consultation> consultations = readConsultation();
        for (Consultation consultation : consultations) {
            boolean isScheduledPassedTime = consultation.getStatus().equals(ConsultationStatus.SCHEDULED) && consultation.getEndDate().isBefore(LocalDateTime.now());
            //System.out.println("First " + isScheduledPassedTime + consultation.getEndDate() + " " + LocalDateTime.now());
            if (isScheduledPassedTime) {
                updateConsultationInFile(consultation.getConsultationId(), ConsultationStatus.COMPLETED);
            }

            boolean isOpenOrRescheduledPastTime = (consultation.getStatus().equals(ConsultationStatus.OPEN) || consultation.getStatus().equals(ConsultationStatus.RESCHEDULED)) && consultation.getEndDate().isBefore(LocalDateTime.now());
            //System.out.println("Second " + isOpenOrRescheduledPastTime + consultation.getEndDate() + " " + LocalDateTime.now());
            if (isOpenOrRescheduledPastTime) {
                updateConsultationInFile(consultation.getConsultationId(), ConsultationStatus.CANCELLED);

            }
        }
    }

    public List<Consultation> readConsultation() {
        List<Consultation> consultations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(consultationFile))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                //System.out.println("RIP");
                String[] parts = line.split("::");
                //boolean convertBoolean = Boolean.parseBoolean(parts[5]);
                ConsultationStatus convertStatus = ConsultationStatus.valueOf(parts[7].toUpperCase());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime convertedStartDate = LocalDateTime.parse(parts[3], formatter);
                LocalDateTime convertedEndDate = LocalDateTime.parse(parts[4], formatter);
                consultations.add(new Consultation(parts[0], parts[1], parts[2], convertedStartDate, convertedEndDate, parts[5], parts[6], convertStatus, Boolean.parseBoolean(parts[8])));
            }
        } catch (IOException _) {
        }
        return consultations;
    }

    protected void createInitialFile() {
        writeFile(consultationFile, Arrays.asList(
                "CONSULT_ID::STUDENT ID::LECTURER ID::START TIME::END TIME::SFEEDBACK::LFEEDBACK::STATUS::NOTIFY USER OF APPROVAL",
                "1::::1::2024-11-01T01:00::2024-11-01T01:30::::::OPEN::false",
                "2::2::1::2024-11-02T02:00::2024-11-02T02:30::Great lecturer::Great student::COMPLETED::false",
                "3::2::1::2024-11-03T03:00::2024-11-03T03:30::::::COMPLETED::false",
                "4::2::1::2024-11-04T04:00::2024-11-04T04:30::::::RESCHEDULED::false",
                "5::2::1::2024-11-05T05:00::2024-11-05T05:30::::::COMPLETED::false",
                "6::::1::2025-11-06T06:30::2025-11-06T08:30::::::OPEN::false",
                "7::::1::2025-11-07T07:00::2025-11-07T07:30::::::OPEN::false",
                "8::::1::2025-11-08T08:00::2025-11-08T08:30::::::OPEN::false",
                "9::2::1::2024-11-09T09:00::2024-11-09T09:30::::::SCHEDULED::false")
        );
    }

    private void writeFile(String fileName, List<String> list) { //CAN MOVE TO FILE MANAGEMENT
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            for (String i : list) {
                writer.write(i + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("[!] Error occurred while creating the file:");
        }
    }

    public void updateConsultationInFile(String consultId, ConsultationStatus newStatus, LocalDateTime startDate, int durationInMinutes) {
        updateConsultationInFile(consultId, "", newStatus, startDate, durationInMinutes, "", "", false);
    }

    public void updateConsultationInFile(String consultId, ConsultationStatus newStatus, LocalDateTime startDate, int durationInMinutes, String studentFeedbackMessage, String lecturerFeedbackMessage) {
        updateConsultationInFile(consultId, "", newStatus, startDate, durationInMinutes, studentFeedbackMessage, lecturerFeedbackMessage, false);
    }

    public void updateConsultationInFile(String consultId, ConsultationStatus newStatus) {
        updateConsultationInFile(consultId, "", newStatus, null, 0, "", "", false);
        //updateConsultationInFile(findConsultationId(lecturerId.getId(), convertToLocalDateTime), ConsultationStatus.SCHEDULED, null, 0, "", "")
    }

    public void updateConsultationInFile(String consultId, String userId, ConsultationStatus newStatus, LocalDateTime startDate, int durationInMinutes, String studentFeedbackMessage, String lecturerFeedbackMessage, boolean notifyStudentOfApproval) {
        try {
            Consultation consultationToUpdate = findConsultationById(consultId);
            String oldLine = consultationToUpdate.convert();

            Consultation updatedConsultation = createUpdatedConsultation(
                    consultationToUpdate, userId, newStatus, startDate, durationInMinutes, studentFeedbackMessage, lecturerFeedbackMessage, notifyStudentOfApproval
            );
            String newLine = updatedConsultation.convert();
            updateFileContents(oldLine, newLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Consultation createUpdatedConsultation(Consultation consultation, String userId, ConsultationStatus newStatus, LocalDateTime startDate, int durationInMinutes, String studentFeedbackMessage, String lecturerFeedbackMessage, boolean notifyStudentOfApproval) {
        switch (newStatus) {
            case SCHEDULED:
                consultation.setStudentId(userId.isBlank() ?  consultation.getStudentId() : userId);
                //System.out.println("MAET" + consultation.convert() + "WHAT: " + userId);
                consultation.setNotifyUserOfApproval(notifyStudentOfApproval);
                break;

            case RESCHEDULED:
                consultation.setStartDate(startDate);
                consultation.setEndDate(startDate.plusMinutes(durationInMinutes));
                consultation.setNotifyUserOfApproval(false);
                break;

            case CANCELLED:
                consultation.setStudentId(userId.isBlank() ? consultation.getStudentId() : userId);
                consultation.setNotifyUserOfApproval(true);
                break;

            case COMPLETED:
                consultation.setStudentFeedback(
                        !consultation.getStudentFeedback().isBlank() ? consultation.getStudentFeedback() : studentFeedbackMessage
                );
                consultation.setLecturerFeedback(
                        !consultation.getLecturerFeedback().isBlank() ? consultation.getLecturerFeedback() : lecturerFeedbackMessage
                );
                consultation.setNotifyUserOfApproval(false);
                break;

            default:
                throw new IllegalArgumentException("Unsupported status: " + newStatus);
        }

        consultation.setStatus(newStatus);
        System.out.println("OUT: " + consultation.convert());
        return consultation;
    }

    private void updateFileContents(String oldLine, String newLine) throws IOException {
        File file = new File(consultationFile);
        String fileContents = Files.readString(file.toPath());
        fileContents = fileContents.replace(oldLine, newLine);
        Files.writeString(file.toPath(), fileContents);
    }
    public void addConsultationToFile(Consultation consultation) {
        try {
            writer = new BufferedWriter(new FileWriter(consultationFile, true));
            writer.write(consultation.convert() + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error occurred while writing to inventory file.");
        }
    }
}
