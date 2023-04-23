package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NonNull
public abstract class User {

    private Integer id;
    private String name;
    private String userName;
    private String password;
    private String userType;

    abstract String getType();

    @Override
    public String toString(){
        return String.format(
                "|%20s|%-20s|%-20s|",
                getId(), getName(), getType()
        );
    }
}
