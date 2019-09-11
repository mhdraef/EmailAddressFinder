//package com.bham.pij.assignments.a1a;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EmailAddressFinder {

    private static ArrayList<String> emailAddresses;

    public static void main(String[] args) {
        emailAddresses = new ArrayList<String>();
        EmailAddressFinder eaf = new EmailAddressFinder();
        eaf.run();
        System.out.println("Email addresses found: " + emailAddresses.size());
    }

    public void run() {

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("corrupteddb"));

            String input = "";

            PrintWriter pw = new PrintWriter("eaf");

            while ((input = reader.readLine()) != null) {

                input = input.trim();

                ArrayList<String> temp = new ArrayList<String>();

                temp = findEmailAddresses(input);

                for (String t: temp) {
                    emailAddresses.add(t);
                }
            }

            pw.close();
            reader.close();
        }

        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> findEmailAddresses(String input) {

        ArrayList<String> list = new ArrayList<String>();

        String[] validemails = input.split(" ");

        System.out.println(validemails);

        return list;
    }
}
