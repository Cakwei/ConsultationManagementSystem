package org.cakwei;
import org.cakwei.Gui.Register;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AccountManagement extends FileManagement {
    public String currentUserId;
    public boolean logIn(String username, String password) {
        if (!(isUsernameExists(username))) {
                return false;
            }
            if (!isPasswordMatchUser(username, password)) {
                return false;
            }
        currentUserId = findAccountByName(username).getId();
        return true;
    }

    private boolean isUsernameExists(String username) {
        return findAccountByName(username).getName() != null;
    }

    private boolean isPasswordMatchUser(String name, String password) {
        return findAccountByName(name).getName() != null && findAccountByName(name).getName().equalsIgnoreCase(name) && findAccountByName(name).getPassword().equals(password);
    }
    public boolean signUp(String username, String password, userTypes userRole) {
            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(new Register(),
                        "Password input does not meet the requirements",
                        "Error occurred",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            List<Account> accounts = readUserFile();
            String id = String.valueOf(accounts.size());
            registerUser(id, username, password, userRole);
            System.out.println("[REGISTER] Account registration successful");
            return true;

    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).*$");
    }

    protected void registerUser(String id, String username, String password, userTypes userRole) {
        List<Account> accounts = readUserFile();
        //fileWrite.write((accounts.size() + 1) + "::" + account.getName()+ "::" + account.getPassword() + "::" + account.getUserType() + "\n");
        Account newAccount =  new Account(id, username, password, userRole);
        appendFile(newAccount);
        readUserFile();
    }

     public boolean changePassword(String oldPassword, String newPassword) {
        return updateFile(currentUserId, newPassword);
     }
    public Account findAccountById(String id) {
        List<Account> accounts = readUserFile();
        for (Account acc : accounts) {
            if (acc.getId().equalsIgnoreCase(id)) {
                return acc;
            }
        }
        return null;
    }
    public Account findAccountByName(String username) {
        List<Account> Accounts = readUserFile();
        for (Account acc : Accounts) {
            if (acc.getName().equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
    }
     protected void createInitialFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFile));
            System.out.println("[!] Attempting to create " + "users.txt " + "file...");
            System.out.println("[!] " + "users.txt " + "successfully created.");
            writer.write("ID::USERNAME::PASSWORD::USERTYPE" + "\n");
            writer.write("1::lec1::l::LECTURER" + "\n");
            writer.write("2::cakwei::c::STUDENT" + "\n");
            writer.flush();
            writer.close();
            readUserFile();
        } catch (IOException e) {
            System.out.println("[!] An error occurred.");
        }
    }
    protected void appendFile(Account account) { //Ran on user registration
        try {
            File users = new File(userFile);
            FileWriter fileWrite = new FileWriter(users, true);
            //fileWrite.write((accounts.size() + 1) + "::" + account.getName()+ "::" + account.getPassword() + "::" + account.getUserType() + "\n");
            fileWrite.write((Integer.parseInt(account.getId()) + 1) + "::" + account.getName()+ "::" + account.getPassword() + "::" + account.getUserType() + "\n");
            fileWrite.close();
            System.out.println("Successfully wrote to the file.");
            fileWrite.close();
        } catch (IOException e) {
            System.out.println("[!] An error occurred.");
        }
    }
    public boolean updateFile(String id, String newPassword) { //Ran on password modification
        Account account = null;
        try {
            Scanner scanner = new Scanner(new File(userFile));
            StringBuffer buffer = new StringBuffer();
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine() + System.lineSeparator());
            }
            String fileContents = buffer.toString();
            scanner.close();
            List<Account> accounts = readUserFile();
            for (Account acc : accounts) {
                if (acc.getId().equalsIgnoreCase(id)) {
                    account = acc;
                    break;
                }
            }
            String oldLine = accounts.indexOf(account) + 1 + "::" + account.getName() + "::" + account.getPassword() + "::" + account.getUserType();
            System.out.println("Old line: " + oldLine);
            System.out.println("Check index: " + accounts + " " + account + " " + accounts.indexOf(account));
            System.out.println("New password: " + newPassword);
            String newLine = accounts.indexOf(account) + 1 + "::" + account.getName() + "::" + newPassword + "::" + account.getUserType();
            System.out.println("New line: " + newLine);
            fileContents = fileContents.replaceAll(oldLine, newLine);
            FileWriter writer = new FileWriter(userFile);
            System.out.println("New data: " + fileContents);
            writer.append(fileContents);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("[!] Error occurred");
            return false;
        }
        return true;
    }
    public List<Account> readUserFile() {
        List<Account> accounts = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFile));
            String[] lines;
            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                lines = line.split("::");
                accounts.add(new Account(lines[0], lines[1], lines[2], userTypes.valueOf(lines[3])));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("[!] Error occurred");
        }
        return accounts;
    }
}
