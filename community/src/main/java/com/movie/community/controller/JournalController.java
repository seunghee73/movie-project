package com.movie.community.controller;

import com.movie.community.controller.dto.request.JournalRequestDTO;
import com.movie.community.controller.dto.response.JournalResponseDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface JournalController {
	public List<JournalResponseDTO> journals(Authentication authentication);

	public ResponseDTO create(@RequestBody JournalRequestDTO requestDTO,
							  Authentication authentication);

	public ResponseDTO update(@RequestBody JournalRequestDTO requestDTO,
							  @PathVariable("journalId") String journalId,
							  Authentication authentication);

	public ResponseDTO delete(@PathVariable("journalId") String journalId,
							  Authentication authentication);
}
