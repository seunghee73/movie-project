package com.movie.community.controller.dto.request;

import com.movie.community.domain.Journal;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class JournalRequestDTO {
	private String imageUrl;
	private String quote;
	private String emotion;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	public Journal toEntity() {
		return new Journal(this);
	}
}
