package com.example.jpa_study.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comments;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products_tbl")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productDescription;
    private Long productPrice;
    private String subCategoryName;
    private String mainCategoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", insertable = false, updatable = false)
    // @JoinColumn(name = "sub_category_id"  : 부모테이블에 키가 생김
    private SubCategory subCategory; //부모테이블  @OneToMany(mappedBy = "subCategory" ) 이름 일치

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id", insertable = false, updatable = false)
    private MainCategory mainCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();
   //jpa 의 기술이므로 테이블 관계 설정시 필수는 아님
    @OneToMany(mappedBy = "cartProduct",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartList = new ArrayList<>();
}
