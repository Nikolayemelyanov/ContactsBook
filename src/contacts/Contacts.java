package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Contacts extends DomainObject implements Serializable {

    private String surname;
    private LocalDate dateOfBirth;
    private String gender;

    public Contacts() {
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Name: " + this.getNameInfo() + "\n" + "Surname: " + this.getSurnameInfo() + "\n" + "Birth date: " + this.getBirthInfo() + "\n" +
                "Gender: " + this.getGenderInfo() + "\n" + "Number: " + this.getNumberInfo() + "" +
                "\n" + super.toString();
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (dateOfBirth.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
            this.dateOfBirth = LocalDate.parse(dateOfBirth);
        } else {
            System.out.println("Bad birth date!");
        }
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.toLowerCase().equals("m") || gender.toLowerCase().equals("f")) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
        }
    }
    @Override
    public String getShortInfo(){
        return name + " " + surname;
    }

    public String getSurnameInfo() {
        if (Objects.equals(surname,null)) {
            return "[no data]";
        } else {
            return surname;
        }
    }
    public String getGenderInfo() {
        if (Objects.equals(gender, null)) {
            return "[no data]";
        } else {
            return gender;
        }
    }
    public String getBirthInfo() {
        if (Objects.equals(dateOfBirth, null)) {
            return "[no data]";
        } else {
            return dateOfBirth.toString();
        }
    }
    @Override
    public String getItems() {
        return "name, surname, birth, gender, number";
    }
    @Override
    public void editDomainObject(String value, String parameter){
        switch (value) {
            case "name":
                this.setName(parameter);
                break;
            case "surname":
                this.setSurname(parameter);
                break;
            case "number":
                this.setNumber(parameter);
                break;
            case "birth":
                this.setDateOfBirth(parameter);
                break;
            case "gender":
                this.setGender(parameter);
                break;
        }
        this.setDateModified(LocalDateTime.now());
        System.out.println("Saved");
    }
}
