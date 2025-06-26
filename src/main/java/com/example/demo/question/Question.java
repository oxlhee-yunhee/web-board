package com.example.demo.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.demo.answer.Answer;
import com.example.demo.User.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity

public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//데이터를 저장할 때 일일이 입력하지 않아도 자동으로 저장됨. 고유한 번호를 생성하는 방법을 지정하는 부분
	private Integer id;
	
	@Column(length=200)//열의 길이 200
	private String subject;
	
	@Column(columnDefinition = "TEXT")//텍스트를 열 데이터로 넣을 수 있음.
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy="question", cascade=CascadeType.REMOVE)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;
	
}
