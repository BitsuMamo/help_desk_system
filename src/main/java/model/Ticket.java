package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class Ticket {

    private Integer id;
    private LocalDate createdAt;
    private String title, description;
    private Customer customer;
    private Servicer servicer;
    private boolean isFixed;

    public Ticket(Integer id, String title, String description, Customer customer) {
        this.id = id;
        this.createdAt = LocalDate.now();
        this.title = title;
        this.description = description;
        this.customer = customer;
        this.servicer = null;
        this.isFixed = false;
    }

    @Override
    public String toString(){
        return String.format(
                "|%-20d|%20s|%20s|%20s|%-20d|%-20d|",
                id, createdAt, title, description, customer.getId(), servicer == null ? -1 : servicer.getId()
        );
    }
}
