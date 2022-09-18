package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@EntityListeners(value = {UserEntityListener.class})
//@Table(name ="HwUser",indexes = {@Index(columnList="name")},uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class HwUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "district", column = @Column(name = "home_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "home_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "home_zipCode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "company_city")),
            @AttributeOverride(name = "district", column = @Column(name = "company_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "company_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "company_zipCode"))
    })
    private Address companyAddress;


    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id", insertable = false , updatable = false)
    @Builder.Default
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="user_id")
    @ToString.Exclude
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

//    @Column(name="crtdat")        //테이블 컬럼 명 변경
//    @Column(nullable = false)       //not null 컬럼 생성
//    @Column(unique = true)        //unique 생성
//    @Column(length = 4000)        //컬럼 길이

//    @Column(updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

//    @Transient                      //db데이터 반영 x
//    private String testData;

//    @PrePersist
//    public void prePersist(){
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }



//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;


}

