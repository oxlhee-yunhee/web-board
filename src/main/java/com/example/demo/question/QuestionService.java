package com.example.demo.question;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDateTime;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.answer.Answer;
import com.example.demo.DataNotFoundException;
import com.example.demo.User.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	public Page<Question> getList(int page, String kw) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.questionRepository.findAllByKeyword(kw, pageable);
	}

	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}

	public void create(String subject, String content, SiteUser user) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user);
		this.questionRepository.save(q);
	}
	
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}
	
	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}
	
	private Specification<Question> search(String kw){//kw-검색어
		return new Specification<>() {
			private static final long serialVersionUID=1L;
			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<Question, SiteUser> u1=q.join("author", JoinType.LEFT);
				Join<Question, Answer> a=q.join("answerList", JoinType.LEFT);
				Join<Answer, SiteUser> u2=a.join("author", JoinType.LEFT);
				return cb.or(cb.like(q.get("subject"), "%"+kw+"%"),//제목
						cb.like(q.get("content"), "%"+kw+"%"),//내용
						cb.like(u1.get("username"), "%"+kw+"%"),//질문 작성자
						cb.like(a.get("content"), "%"+kw+"%"),//답변 내용
						cb.like(u2.get("username"), "%"+kw+"%"));//답변 작성자
			}
		};
	}

}
