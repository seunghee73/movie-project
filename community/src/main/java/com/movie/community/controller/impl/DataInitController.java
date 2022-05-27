package com.movie.community.controller.impl;

import com.movie.community.controller.dto.request.HolidayRequestDTO;
import com.movie.community.controller.dto.request.MovieRequestDTO;
import com.movie.community.domain.Holiday;
import com.movie.community.repository.HolidayRepository;
import com.movie.community.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class DataInitController {
	private final MovieService movieService;
	private final HolidayRepository holidayRepository;

	@GetMapping("/data/generate")
	public String generate() throws IOException, ParseException {
		// holiday
		String[] holidays = {"어린이날", "밸런타인데이", "영화인의날", "크리스마스", "빼빼로데이", "한글날", "싸피인의날",
				"식목일", "만우절", "근로자의날", "새해", "광복절", "어버이날", "스승의날", "문화의날", "핼러윈",
				"화이트데이", "고백데이", "부부의날"
		};
		LocalDate[] holidayDates = {LocalDate.parse("2022-05-05"), LocalDate.parse("2022-02-14"),
				LocalDate.parse("2022-05-25"), LocalDate.parse("2022-12-25"),
				LocalDate.parse("2022-11-11"), LocalDate.parse("2022-10-09"), LocalDate.parse("2022-05-27"),
				LocalDate.parse("2022-04-05"), LocalDate.parse("2022-04-01"), LocalDate.parse("2022-05-01"),
				LocalDate.parse("2022-01-01"), LocalDate.parse("2022-08-15"), LocalDate.parse("2022-05-08"),
				LocalDate.parse("2022-05-15"), LocalDate.parse("2022-10-20"), LocalDate.parse("2022-10-31"),
				LocalDate.parse("2022-03-14"), LocalDate.parse("2022-09-17"), LocalDate.parse("2022-05-21")
		};
		for (int i = 0; i < holidays.length; i++) {
			HolidayRequestDTO holidayRequestDTO = new HolidayRequestDTO();
			holidayRequestDTO.setHoliday(holidays[i]);
			holidayRequestDTO.setDate(holidayDates[i]);

			Holiday holiday = holidayRequestDTO.toEntity();
//			holidayRepository.save(holiday);
		}




		JSONParser parser = new JSONParser();
		Reader reader = new FileReader("src/main/resources/static/movies.json");
		JSONObject jsonObject = (JSONObject) parser.parse(reader); // 일단 파일 Json 가져옴

		JSONArray jsonArray = (JSONArray) jsonObject.get("result"); // "result" 속성에는 Json list 가 있음. 뽑아내기

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i); // 해당 Result 의 0번째 요소에 접근. 얘가 바로 Movie data 1개

			MovieRequestDTO dto = new MovieRequestDTO();
			dto.setId((Long) object.get("id"));
			dto.setTitle((String) object.get("title"));
			dto.setScore((Double) object.get("score"));
			Long voteCount = (Long) object.get("voteCount");
			dto.setVoteCount(Math.toIntExact(voteCount));
			dto.setPopularity((Double) object.get("popularity"));
			String date = (String) object.get("releaseDate");
			dto.setReleasedDate(LocalDate.parse(date));
			dto.setDescription((String) object.get("description"));
			dto.setImageUrl((String) object.get("imageUrl"));
			dto.setGenres((String) object.get("genres"));
			dto.setHoliday(holidays[i % holidays.length]);

//			movieService.addMovie(dto);
		}

		return "ok";
	}
}

