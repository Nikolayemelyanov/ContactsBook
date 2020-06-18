package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        ContactsList contactsList = new ContactsList();
        String fileName = "text.txt";
        System.out.println("x");
        try {
            contactsList.setContactsArrayList((ArrayList<DomainObject>) SerializationUtils.deserialize(fileName));
        } catch (Exception e) {
            System.out.println("The contacts are empty");
        }
        System.out.println("\n" + "[menu] Enter action (add, list, search, count, exit):");
        String message = sc.nextLine();
        while(!"exit".equals(message)) {
            switch (message) {
                case "add":
                    contactsList.addDomainObject(sc);
                    break;
                case "count":
                    contactsList.countContacts();
                    break;
                case "list":
                    contactsList.getInfo(sc);
                    break;
                case "search":
                    contactsList.searchDomainObject(sc, fileName);
                    break;
            }

            System.out.println("\n" + "[menu] Enter action (add, list, search, count, exit):");
            message = sc.nextLine();
        }
        SerializationUtils.serialize(contactsList.getContactsArrayList(), fileName);
        sc.close();
    }
}
