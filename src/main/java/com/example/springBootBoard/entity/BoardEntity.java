package com.example.springBootBoard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//DB의 테이블 역활을 하는 클래스
@Entity
@Getter @Setter
@Table(name = "board_table")//이 이름으로 자동으로 테이블을 생성!
public class BoardEntity {
    @Id//필수 컬럼 아이디값으로 무엇을 쓸것인가 MY SQL 기준 자동 증가 가능
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20 , nullable = false)//길이는 2자 이상 Null 일수 없다라고 표기해주기!
    private String boardWriter;


}
