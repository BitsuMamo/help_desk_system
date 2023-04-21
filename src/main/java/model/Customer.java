package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@EqualsAndHashCode(callSuper = true)
public class Customer extends User{

    public Customer(int id, String name, String userName, String password){
        super(id, name, userName, password, "CUSTOMER");
    }

    @Override
    String getType() {
        return "CUSTOMER";
    }


}
