package com.query_dsl.query_dsl_tutorial.boundedContext.interestKeyword;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterestKeyword {
    @Id
    private String keyword;

    // 객체의 데이터가 같은지 비교
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InterestKeyword that)) return false;
        return Objects.equals(getKeyword(), that.getKeyword());
    }

    // 해당 객체의 해시 코드를 반환
    @Override
    public int hashCode() {
        return Objects.hashCode(getKeyword());
    }
}
