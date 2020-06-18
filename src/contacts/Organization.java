package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Organization extends DomainObject implements Serializable {

    private String address = "";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "Organization name: " + this.getNameInfo() + "\n" + "Address: " + this.getAddressInfo() + "\n" + "Number: " + this.getNumberInfo() + "" +
                "\n" + super.toString();
    }
    @Override
    public String getShortInfo(){
        return name;
    }
    public String getAddressInfo() {
        if (address.equals("")) {
            return "[no data]";
        } else {
            return address;
        }
    }
    @Override
    public String getItems() {
        return "name, address, number";

    }
    @Override
    public void editDomainObject(String value, String parameter){
        switch (value) {
            case "name":
                this.setName(parameter);
                break;
            case "address":
                this.setAddress(parameter);
                break;
            case "number":
                this.setNumber(parameter);//
                break;
        }
        this.setDateModified(LocalDateTime.now());
        System.out.println("Saved");
    }
}

