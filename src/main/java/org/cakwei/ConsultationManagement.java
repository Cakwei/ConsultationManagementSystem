package org.cakwei;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.cakwei.Application.currentSession;


public class ConsultationManagement extends FileManagement { // Soon become abstract class
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
    public void start() {
        boolean isRequiredFilesExist = new File(consultationFile).exists() && new File(transactionFile).exists();
        if (!isRequiredFilesExist) {
            System.out.println("[!] One or more is not present, creating required files.");
            createInitialFile();
        }
    }

    public static void main(String[] args) {
        ConsultationManagement inv = new ConsultationManagement();
        inv.start();
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
                "CONSULT_ID::STUDENT NAME::LECTURER NAME::START TIME::END TIME::SFEEDBACK::LFEEDBACK::STATUS::NOTIFY USER OF APPROVAL",
                "1::::4::2024-11-01T01:00::2024-11-01T01:30::::::OPEN::false",
                "2::::4::2024-11-02T02:00::2024-11-02T02:30::::::SCHEDULED::false",
                "3::2::5::2024-11-03T03:00::2024-11-03T03:30::::::COMPLETED::false",
                "4::::5::2024-11-04T04:00::2024-11-04T04:30::::::RESCHEDULED::false",
                "5::2::4::2024-11-05T05:00::2024-11-05T05:30::::::COMPLETED::false",
                "6::::4::2024-11-06T06:00::2024-11-06T06:30::::Nut::OPEN::false",
                "7::::4::2024-11-07T07:00::2024-11-07T07:30::::::OPEN::false",
                "8::::4::2024-11-08T08:00::2024-11-08T08:30::::::OPEN::false",
                "9::2::4::2024-11-09T01:00::2024-11-09T09:30::::::SCHEDULED::false")
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
        updateConsultationInFile(consultId, "",newStatus, startDate, durationInMinutes, "", "", false);
    }
    public void updateConsultationInFile(String consultId, ConsultationStatus newStatus, LocalDateTime startDate, int durationInMinutes, String studentFeedbackMessage, String lecturerFeedbackMessage) {
        updateConsultationInFile(consultId,"", newStatus, startDate, durationInMinutes, studentFeedbackMessage, lecturerFeedbackMessage, false);
    }
    public void updateConsultationInFile(String consultId, ConsultationStatus newStatus) {
        updateConsultationInFile(consultId, "", newStatus, null, 0, "", "", false);
        //updateConsultationInFile(findConsultationId(lecturerId.getId(), convertToLocalDateTime), ConsultationStatus.SCHEDULED, null, 0, "", "")
    }
    public void updateConsultationInFile(String consultId, String userId, ConsultationStatus newStatus, LocalDateTime startDate, int durationInMinutes, String studentFeedbackMessage, String lecturerFeedbackMessage,  boolean notifyStudentOfApproval) { //MAIN METHOD
        Consultation updatedConsultation = null;
        String newLine = "";
        try {
            Scanner scanner = new Scanner(new File(consultationFile));
            StringBuffer buffer = new StringBuffer();
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine() + System.lineSeparator());
            }
            String fileContents = buffer.toString();
            scanner.close();
            List<Consultation> consultations = readConsultation();
            for (Consultation consultation : consultations) {
                if (consultation.getConsultationId().equals(consultId)) {
                    updatedConsultation = consultation;
                    break;
                }
            }
            String oldLine = consultations.indexOf(updatedConsultation) + 1 + "::" +
                    updatedConsultation.getStudentId() + "::" +
                    updatedConsultation.getLecturerId() + "::" +
                    updatedConsultation.getStartDate() + "::" +
                    updatedConsultation.getEndDate() + "::" +
                    updatedConsultation.getStudentFeedback() + "::" +
                    updatedConsultation.getLecturerFeedback() + "::" +
                    updatedConsultation.getStatus() + "::" +
                    updatedConsultation.getNotifyUserOfApproval();

            if (newStatus.equals(ConsultationStatus.SCHEDULED)) { //FROM RESCHEDULE & OPEN TO SCHEDULED
                newLine = (consultations.indexOf(updatedConsultation) + 1) + "::" +
                    (userId.isBlank() ?  updatedConsultation.getStudentId() : userId) + "::" +
                    updatedConsultation.getLecturerId() + "::" +
                    updatedConsultation.getStartDate() + "::" +
                    updatedConsultation.getEndDate() + "::" +
                    updatedConsultation.getLecturerFeedback() + "::" +
                    updatedConsultation.getStudentFeedback() + "::" +
                    newStatus +
                    (notifyStudentOfApproval ? "::true" : "::false");
            } else if (newStatus.equals(ConsultationStatus.RESCHEDULED)) { //SCHEDULED TO RESCHEDULED
                 newLine = (consultations.indexOf(updatedConsultation) + 1) + "::" +
                    updatedConsultation.getStudentId() + "::" +
                    updatedConsultation.getLecturerId() + "::" +
                    startDate + "::" +
                    startDate.plusMinutes(durationInMinutes) + "::::::" +
                    newStatus + "::false";
            } else if (newStatus.equals(ConsultationStatus.CANCELLED)) {
                newLine = (consultations.indexOf(updatedConsultation) + 1) + "::" +
                        (userId.isBlank() ?  updatedConsultation.getStudentId() : userId) + "::" +
                        updatedConsultation.getLecturerId() + "::" +
                        updatedConsultation.getStartDate() + "::" +
                        updatedConsultation.getEndDate() + "::::::" +
                        newStatus + "::true";
            } else {
                System.out.println("wpfjweifjwefgjwogfjw;ogj" +updatedConsultation.getStudentFeedback().isBlank() + updatedConsultation.getLecturerFeedback().isBlank());
                newLine = (consultations.indexOf(updatedConsultation) + 1) + "::" + //COMPLETED
                        updatedConsultation.getStudentId() + "::" +
                        updatedConsultation.getLecturerId() + "::" +
                        updatedConsultation.getStartDate() + "::" +
                        updatedConsultation.getEndDate() + "::" + (!updatedConsultation.getStudentFeedback().isBlank() ?  updatedConsultation.getStudentFeedback() : studentFeedbackMessage) + "::" +
                        (!updatedConsultation.getLecturerFeedback().isBlank() ?  updatedConsultation.getLecturerFeedback() : lecturerFeedbackMessage)+ "::" +
                        newStatus + "::false";
            }
            System.out.println(oldLine + "\n" + newLine);
            fileContents = fileContents.replaceAll(oldLine, newLine);
            FileWriter writer = new FileWriter(consultationFile);
            //System.out.println("New data: " + fileContents);
            writer.append(fileContents);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
