package model;

public class Manager extends User{

    public Manager(Integer id, String name, String userName, String password){
        super(id, name, userName, password, "MANAGER");
    }
    @Override
    String getType() {
        return "MANAGER";
    }
}
