package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product extends  BaseModel {

    @Column(name = "name")
    @Size(max=60)
    String name;

    @Column(name = "description")
    @Size(max=230)
    String description;

    @Column(name = "short_description")
    @Size(max = 150)
    String short_description;

    @Column(name = "price")
    Double price;

}
