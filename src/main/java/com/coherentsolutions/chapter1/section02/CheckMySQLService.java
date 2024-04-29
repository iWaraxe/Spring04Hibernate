package com.coherentsolutions.chapter1.section02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckMySQLService {

    public static void main(String[] args) {
        String os = System.getProperty("os.name").toLowerCase();
        String command = determineCommand(os);
        if (command == null) {
            System.out.println("Unsupported operating system for this command.");
            return;
        }

        try {
            Process process = Runtime.getRuntime().exec(new String[] {"bash", "-c", command});
            printResults(process);
        } catch (IOException e) {
            System.out.println("Exception occurred while checking MySQL service: " + e.getMessage());
        }
    }

    private static String determineCommand(String os) {
        if (os.contains("win")) {
            return "sc query MySQL";
        } else if (os.contains("mac")) {
            return "mysql.server status";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return "systemctl status mysql";
        }
        return null;
    }

    private static void printResults(Process process) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
