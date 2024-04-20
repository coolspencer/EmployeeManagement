package com.example.Locking.Locking.persistence.entity;

@Entity
@Table(name = "stock_table")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private int count;

    @Column(name = "version")
    private int version;

}
