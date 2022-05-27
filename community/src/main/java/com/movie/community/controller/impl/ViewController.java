package com.movie.community.controller.impl;

import com.movie.community.controller.dto.response.PostingResponseDetailDTO;
import com.movie.community.service.JournalService;
import com.movie.community.service.PostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ViewController {
	private final JournalService journalService;

	private final PostingService postingService;

	@GetMapping("/")
	public String home() {
		return "main";
	}

	@GetMapping("/view/postings")
	public String postings() {
		return "postings";
	}

	@GetMapping("/view/posting-form")
	public String postingCreateForm() {
		return "create_post";
	}

	@GetMapping("/view/posting-modify-form/{postingId}")
	public String postingUpdateForm(@PathVariable Long postingId, Model model) {
		PostingResponseDetailDTO posting = postingService.getPosting(postingId);
		model.addAttribute("post", posting);
		return "update_post";
	}

	@GetMapping("/view/journals")
	public String journals() {
		return "member";
	}

	@GetMapping("/view/journal-form")
	public String journalForm() {
		return "create_journal";
	}

	@GetMapping("/view/movies")
	public String movies() {
		return "movies";
	}

	@GetMapping("/view/movies/{movieId}")
	public String movieDetail(@PathVariable String movieId) {
		return "movie_detail";
	}

	@GetMapping("/view/holiday-list")
	public String holidayList() {
		return "holiday";
	}

	@GetMapping("/view/holiday/{holidayId}")
	public String holidayMovies(@PathVariable String holidayId) {
		return "holiday_movie_list";
	}

	@GetMapping("/view/postings/{postingId}")
	public String postingDetail(@PathVariable String postingId) {
		return "posting_detail";
	}

	@GetMapping("/sign-up")
	public String signUp() {
		return "sign-up";
	}
}
