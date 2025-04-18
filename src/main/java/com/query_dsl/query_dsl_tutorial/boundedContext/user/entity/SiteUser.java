package com.query_dsl.query_dsl_tutorial.boundedContext.user.entity;

import com.query_dsl.query_dsl_tutorial.boundedContext.interestKeyword.InterestKeyword;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
@Builder
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    // builder를 쓸때, null 에러 발생을 막기위해 사용
    // 비어있는 HashSet을 자동으로 넣어줌
    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<InterestKeyword> interestKeywords = new HashSet<>();

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<SiteUser> followers = new HashSet<>();

    public void addInterestKeword(String keword) {
        // @Builder.Default가 없는 경우 add시 NullPointException이 발생함
        interestKeywords.add(new InterestKeyword(keword));
    }

    public void addFollower(SiteUser follower) {
        followers.add(follower);
    }
}
