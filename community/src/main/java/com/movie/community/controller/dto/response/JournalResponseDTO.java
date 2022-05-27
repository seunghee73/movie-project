package com.movie.community.controller.dto.response;

import com.movie.community.domain.Journal;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JournalResponseDTO {
	private Long id;
	private String imageUrl;
	private String quote;
	private String emotion;
	private String writer;
	private LocalDate date;

	public JournalResponseDTO(Journal journal) {
		this.id = journal.getId();
		this.imageUrl = journal.getImageUrl();
		this.quote = journal.getQuote();
		this.emotion = journal.getEmotion();
		this.writer = journal.getMember().getName();
		this.date = journal.getCreatedDate();
	}
}
