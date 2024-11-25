package org.cakwei;

import com.formdev.flatlaf.FlatDarkLaf;
import org.cakwei.Gui.*;

import java.io.File;
public class Application extends AccountManagement{
    public static Application currentSession = new Application();
    public static void main(String[] args) {
            boolean isUserFileExist = new File(userFile).exists();
            boolean isConsultationFileExist = new File(consultationFile).exists();
            if (!isUserFileExist) {
                new AccountManagement().createInitialFile();
            }
            if (!isConsultationFileExist) {
                new ConsultationManagement().createInitialFile();
            }
            FlatDarkLaf.setup();
            Login.main(args);
    }
}