package com.movie.community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie.community.controller.dto.request.CommentRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Comment {
	@Id
	@Column(name = "comment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String comment;

	@Column
	private LocalDateTime createdDate;

	@Column
	private LocalDateTime updatedDate;

	@JsonIgnore
	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@JsonIgnore
	@ManyToOne(targetEntity = Posting.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "posting_id")
	private Posting posting;

	public Comment(CommentRequestDTO requestDTO) {
		this.comment = requestDTO.getComment();
		this.createdDate = LocalDateTime.now();
	}

	public void update(CommentRequestDTO requestDTO) {
		this.comment = requestDTO.getComment();
		this.updatedDate = LocalDateTime.now();
	}

	public void setWriter(Member member) {
		this.member = member;
	}

	public void mapPosting(Posting posting) {
		this.posting = posting;
	}
}
