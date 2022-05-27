package com.movie.community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie.community.controller.dto.request.JournalRequestDTO;
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
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Journal {
	@Id
	@Column(name = "journal_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String imageUrl;

	@Column
	private String quote;

	@Column
	private LocalDate createdDate;

	@Column
	private String emotion;

	@JsonIgnore
	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	public Journal(JournalRequestDTO requestDTO) {
		insertDtoToEntity(requestDTO);
	}

	public void update(JournalRequestDTO requestDTO) {
		insertDtoToEntity(requestDTO);
	}

	public void setWriter(Member member) {
		this.member = member;
	}

	private void insertDtoToEntity(JournalRequestDTO requestDTO) {
		this.imageUrl = requestDTO.getImageUrl();
		this.quote = requestDTO.getQuote();
		this.emotion = requestDTO.getEmotion();
		this.createdDate = requestDTO.getDate();
	}
}
