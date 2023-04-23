package model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class Ticket {

    @NonNull
    private Integer id;
    @NonNull
    private LocalDate createdAt;
    @NonNull
    private String title;
    private String description;
    @NonNull
    private Customer customer;
    private Servicer servicer;
    @NonNull
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
                "|%40d|%40s|%-40s|%-40s|%-40s|%-40s|",
                id, createdAt, title, description, customer.getName(), servicer == null ? "" : servicer.getName()
        );
    }
}
