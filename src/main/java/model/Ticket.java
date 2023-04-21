package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class Ticket {

    private int id;
    private LocalDate createdAt;
    private String title, description;
    private Customer customer;
    private Servicer servicer;
    private boolean isFixed;

    public Ticket(int id, String title, String description, Customer customer) {
        this.id = id;
        this.createdAt = LocalDate.now();
        this.title = title;
        this.description = description;
        this.customer = customer;
        this.servicer = null;
        this.isFixed = false;
    }
}
