package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

public abstract class DomainObject implements Serializable {
    private static final long serialVersionUID = 1L;
    String number;
    LocalDateTime dateCreated = LocalDateTime.now();
    LocalDateTime dateModified;
    String name;
    String regexNumber = "\\+?\\(\\w+\\)([- ]\\w{2,})*|\\+?\\w+([ -]\\(\\w{2,}\\))*([- ]\\w{2,})*";
    Pattern p = Pattern.compile(regexNumber);
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        if(p.matcher(number).matches()) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
        }
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Time created: " + dateCreated + "\n" + "Time last edit: " + dateModified;
    }

    public abstract String getShortInfo();

    public String getNameInfo(){
        if (Objects.equals(name,null)){
            return "[no data]";
        } else {
            return name;
        }
    }
    public String getNumberInfo() {
        if (Objects.equals(number,null)) {
            return "[no data]";
        } else {
            return number;
        }
    }
    public abstract String getItems();
    public abstract void editDomainObject(String value, String parameter);
    public  String getSearchInfo(){
        return this.getShortInfo() + " " + this.getNumber();
    };
}

