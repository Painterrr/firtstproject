package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB가 해당 객체를 엔티티로 인식 가능
@AllArgsConstructor
@NoArgsConstructor // default constructor
@ToString
@Getter
public class Article {
    @Id // Primary Key
    @GeneratedValue // Automatic generation annotation
    private Long id;

    @Column
    private String title;
    @Column
    private String content;
}