package com.movie.community.service.impl;

import com.movie.community.controller.dto.request.JournalRequestDTO;
import com.movie.community.controller.dto.response.JournalResponseDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import com.movie.community.domain.Journal;
import com.movie.community.domain.Member;
import com.movie.community.repository.JournalRepository;
import com.movie.community.repository.MemberRepository;
import com.movie.community.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class JournalServiceImpl implements JournalService {
	private final JournalRepository journalRepository;

	private final MemberRepository memberRepository;

	@Override
	public List<JournalResponseDTO> getMemberJournals(Authentication authentication) {
		String loginMemberAccount = authentication.getName();
		Member memberEntity = memberRepository.findByAccount(loginMemberAccount);
		if (memberEntity == null) {
			throw new IllegalArgumentException("No such member account=" + loginMemberAccount);
		}

		List<Journal> journals = memberEntity.getJournals();

		return journals.stream()
				.map(JournalResponseDTO::new)
				.collect(Collectors.toList());
	}

	@Override
	public ResponseDTO createJournal(JournalRequestDTO requestDTO, Authentication authentication) {
		if (requestDTO == null) {
			return new ResponseDTO("Invalid client request", 400);
		}

		String loginMemberAccount = authentication.getName();
		Member memberEntity = memberRepository.findByAccount(loginMemberAccount);
		if (memberEntity == null) {
			return new ResponseDTO("No such member account=" + loginMemberAccount, 400);
		}

		Journal journal = requestDTO.toEntity();
		journal.setWriter(memberEntity);

		journalRepository.save(journal);

		return new ResponseDTO("success to save journal", 200);
	}

	@Transactional
	@Override
	public ResponseDTO updateJournal(JournalRequestDTO requestDTO, Long id, Authentication authentication) {
		if (requestDTO == null || id == null) {
			return new ResponseDTO("Invalid client request", 400);
		}

		String loginMemberAccount = authentication.getName();
		Member memberEntity = memberRepository.findByAccount(loginMemberAccount);
		if (memberEntity == null) {
			return new ResponseDTO("No such member account=" + loginMemberAccount, 400);
		}

		Journal toBeUpdatedJournal = journalRepository.findById(id);
		String journalWriterAccount = toBeUpdatedJournal.getMember().getAccount();

		if (!loginMemberAccount.equals(journalWriterAccount)) {
			return new ResponseDTO("No authentication", 400);
		}

		toBeUpdatedJournal.update(requestDTO);

		return new ResponseDTO("success to update journal", 200);
	}

	@Transactional
	@Override
	public ResponseDTO deleteJournal(Long id, Authentication authentication) {
		if (id == null) {
			return new ResponseDTO("Invalid client request", 400);
		}

		Journal journal = journalRepository.findById(id);
		String journalWriterAccount = journal.getMember().getAccount();

		String loginMemberAccount = authentication.getName();
		if (!loginMemberAccount.equals(journalWriterAccount)) {
			return new ResponseDTO("No authentication", 400);
		}

		journalRepository.remove(journal);

		return new ResponseDTO("success to delete journal", 200);
	}
}
