package model;

import lombok.Data;

public class Servicer extends User {
    public Servicer(Integer id, String name, String userName, String password){
        super(id, name, userName, password, "SERVICER");
    }
    @Override
    String getType() {
        return "SERVICER";
    }
}
