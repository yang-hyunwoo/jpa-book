package com.example.springbatch.part5;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TestStatistics {

    private String personCount;

    private String date;

    private String email;

    @Builder
    public TestStatistics(String personCount, String date, String email) {
        this.personCount = personCount;
        this.date = date;
        this.email = email;
    }
}
