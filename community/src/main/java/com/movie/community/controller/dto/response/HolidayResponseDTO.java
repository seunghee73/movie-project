package com.movie.community.controller.dto.response;

import com.movie.community.domain.Holiday;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayResponseDTO {
	private Long id;
	private String holiday;
	private LocalDate date;

	public HolidayResponseDTO(Holiday holiday) {
		this.id = holiday.getId();
		this.holiday = holiday.getHoliday();
		this.date = holiday.getDate();
	}
}
