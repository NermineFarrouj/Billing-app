package net.supervision.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.supervision.billingservice.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private long customerId;//we added the id plus the Customer attribute because Customer that we have in this module does not exist in the database otherwise we would have used just the Customer attribute
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems = new ArrayList<>();
    @Transient private Customer customer;//with @Transient we say to jpa to ignore this entity as it does not exist in the db

    public double getTotal(){
        double total = 0;
        for (ProductItem productItem : productItems) {
            total+=productItem.getAmount();
        }
        return total;
    }

}