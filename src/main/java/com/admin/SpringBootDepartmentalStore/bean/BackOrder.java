package com.admin.SpringBootDepartmentalStore.bean;

import javax.persistence.*;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;

@Entity
@Data
@Table(name = "Backorder")
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class BackOrder {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long backOrderId;

        @OneToOne
        @JoinColumn(name = "orderId", referencedColumnName = "orderId")
        private Order order;

}
