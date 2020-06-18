package contacts;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactsList {
    private ArrayList<DomainObject> contactsArrayList = new ArrayList<>();

    public void addContact(Scanner scanner) {
        Contacts contact = new Contacts();
        System.out.println("Enter the name of the person:");
        contact.setName(scanner.nextLine());
        System.out.println("Enter the surname of the person:");
        contact.setSurname(scanner.nextLine());
        System.out.println("Enter the birth date: ");
        contact.setDateOfBirth(scanner.nextLine());
        System.out.println("Enter the gender (M, F): ");
        contact.setGender(scanner.nextLine());
        System.out.println("Enter the number:");
        String possibleNumber = scanner.nextLine();
        contact.setNumber(possibleNumber);
        System.out.println("The record added.");
        contact.setDateModified(LocalDateTime.now());
        contactsArrayList.add(contact);
    }

    public void addOrganization(Scanner scanner) {
        Organization organization = new Organization();
        System.out.println("Enter the organization name:");
        organization.setName(scanner.nextLine());
        System.out.println("Enter the address:");
        organization.setAddress(scanner.nextLine());
        System.out.println("Enter the number:");
        String possibleNumber = scanner.nextLine();
        organization.setNumber(possibleNumber);
        System.out.println("The record added.");
        organization.setDateModified(LocalDateTime.now());
        contactsArrayList.add(organization);
    }

    public void addDomainObject(Scanner scanner) {
        System.out.println("Enter the type (person, organization):");
        String s = scanner.nextLine();
        if (s.equals("person")) {
            this.addContact(scanner);
        } else if (s.equals("organization")) {
            this.addOrganization(scanner);
        }
    }

    public ArrayList<DomainObject> getContactsArrayList() {
        return contactsArrayList;
    }

    public void setContactsArrayList(ArrayList<DomainObject> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }

    public void countContacts() {
        System.out.println("The Phone Book has " + contactsArrayList.size() + " records");
    }

    public void getInfoList() {

        for (int i = 0; i < contactsArrayList.size(); i++) {
            System.out.println(i + 1 + ". " + contactsArrayList.get(i).getShortInfo());
        }
    }
    public void getInfo(Scanner scanner) {
        this.getInfoList();
        System.out.println("[list] Enter action ([number], back):");
        String option = scanner.nextLine();
        if (!option.equals("back")) {
            int number = Integer.parseInt(option);
            System.out.println(contactsArrayList.get(number - 1).toString());
            this.changeRecord(scanner, number);
        }
    }
    public void changeRecord(Scanner scanner, int number) {

        System.out.println("\n" + "[record] Enter action (edit, delete, menu): ");
        String s = scanner.nextLine();
        while (!s.equals("menu")) {
            if (s.equals("edit")) {
                System.out.println("Select a field (" + this.contactsArrayList.get(number - 1).getItems() +  "): ");
                String value = scanner.nextLine();
                System.out.println("Enter " + value);
                String parameter = scanner.nextLine();
                this.contactsArrayList.get(number - 1).editDomainObject(value, parameter);
                System.out.println(contactsArrayList.get(number - 1).toString());
                System.out.println("\n" + "[record] Enter action (edit, delete, menu): ");
                s = scanner.nextLine();
            } else if (s.equals("delete")) {
                contactsArrayList.remove(number - 1);
                System.out.println("The record removed!");
                s = "menu";
            }

        }
    }

    public void searchDomainObject(Scanner scanner, String filename) throws IOException, ClassNotFoundException {
        String choice = "again";
        int[] arrayNumber = new int[contactsArrayList.size()];
        while (choice.equals("again")) {
            System.out.println("Enter search query: ");
            String s = scanner.nextLine();
            Pattern p = Pattern.compile(s, Pattern.CASE_INSENSITIVE);

            int count = 1;
            for (int i = 0; i < contactsArrayList.size(); i++) {
                if (p.matcher(contactsArrayList.get(i).getSearchInfo()).find()) {
                    System.out.println(count + ". " + contactsArrayList.get(i).getShortInfo());
                    arrayNumber[count - 1] = i;
                    count++;
                }
            }
            System.out.println("[search] Enter action ([number], back, again): ");
            choice = scanner.nextLine();
        }
        if (!choice.equals("back")) {
            int number = Integer.parseInt(choice);
            System.out.println(contactsArrayList.get(arrayNumber[number - 1]).toString());
            this.changeRecord(scanner, number);
        }
    }
}
