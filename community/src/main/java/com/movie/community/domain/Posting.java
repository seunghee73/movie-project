package com.movie.community.domain;

import com.movie.community.controller.dto.request.PostingRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Posting {
	@Id
	@Column(name = "posting_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Column
	private LocalDateTime createdDate;

	@Column
	private LocalDateTime updatedDate;

	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "posting", cascade = CascadeType.REMOVE)
	private List<Comment> comments;

	public Posting(PostingRequestDTO requestDTO) {
		insertDtoToEntity(requestDTO);
		this.createdDate = LocalDateTime.now();
	}

	public void update(PostingRequestDTO requestDTO) {
		insertDtoToEntity(requestDTO);
		this.updatedDate = LocalDateTime.now();
	}

	public void setWriter(Member member) {
		this.member = member;
	}

	private void insertDtoToEntity(PostingRequestDTO requestDTO) {
		this.title = requestDTO.getTitle();
		this.content = requestDTO.getContent();
	}
}
