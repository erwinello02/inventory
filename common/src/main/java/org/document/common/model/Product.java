package org.document.common.model;

import lombok.Getter;
import lombok.Setter;
import org.document.common.auditware.Auditable;
import org.document.common.enums.ProductStatus;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends Auditable<String> implements Serializable {
    @Id
    @SequenceGenerator(name = "product_pk_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_pk_seq")
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_uuid")
    private String productUuid;

    @Column(name = "product_name")
    private String productName;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "sub_category")
    private String subCategory;

    @Column(name = "product_unit")
    private Long unit;

    @Column(name = "product_stock_keeping_unit")
    private String stock;

    @Column(name = "product_minimum_qty")
    private String minimumQty;

    @Column(name = "product_quantity")
    private String quantity;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_tax")
    private Long tax;

    @Column(name = "product_discount_type")
    private Long discountType;

    @Column(name = "product_price")
    private Long price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;

    @Column(name = "product_image")
    private String image;
}