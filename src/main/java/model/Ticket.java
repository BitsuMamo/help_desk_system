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

    public Ticket(Integer id, String title, String description, Customer customer, Servicer servicer, boolean isFixed) {
        this.id = id;
        this.createdAt = LocalDate.now();
        this.title = title;
        this.description = description;
        this.customer = customer;
        this.servicer = servicer;
        this.isFixed = isFixed;
    }
    public Ticket(Integer id, LocalDate createdAt, String title, String description, Customer customer, Servicer servicer, boolean isFixed) {
        this(id, title, description, customer, servicer, isFixed);
        this.createdAt = createdAt;
    }
    public Ticket(String title, String description, Customer customer) {
        this(null, title, description, customer, null, false);
    }

    @Override
    public String toString(){
        return String.format(
                "|%-40d|%40s|%40s|%40s|%40s|%40s|",
                id, createdAt, title, description, customer.getName(), servicer == null ? "" : servicer.getName()
        );
    }
}
