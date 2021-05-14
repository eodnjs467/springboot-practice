package com.example.testdb.service;

import com.example.testdb.dto.*;
import com.example.testdb.entity.UserBoard;
import com.example.testdb.entity.UserInfo;
import com.example.testdb.repository.UserBoardRepository;
import com.example.testdb.repository.UserReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserBoardServiceImpl implements UserBoardService{

    private final UserBoardRepository repository;



    @Override
    public PageResultDto<UserBoardDto, Object[]> getList(PageRequestDto pageRequestDto) {
        Function<Object[], UserBoardDto> fn = (en -> entityToDto((UserBoard) en[0], (UserInfo) en[1]));

        Page<Object[]> result = repository.searchPage(pageRequestDto.getType(), pageRequestDto.getKeyword(), pageRequestDto.getPageable(Sort.by("bno").descending()));

        return new PageResultDto<>(result, fn);
    }



    @Override
    public Long register(UserBoardDto dto) {
        UserBoard board = dtoToEntity(dto);

        repository.save(board);
        log.info(board + " 등록 ");

        return board.getBno();
    }

    @Transactional
    @Override
    public UserBoardDto get(Long bno) {                     // 뷰로 뽑을때 Object 로 뽑아내는걸로 수정

        Object result = repository.getUserBoardByBno(bno);
        Object[] arr = (Object[]) result;

        return entityToDto((UserBoard) arr[0], (UserInfo) arr[1]);
//        UserBoard result = repository.getOne(bno);
//        UserInfo userInfo = result.getWriter();
//
//        UserBoardDto dto = entityToDto(result, userInfo);

//        return dto;

    }

    @Transactional
    @Override
    public void modify(UserBoardDto dto) {
        UserBoard board = repository.getOne(dto.getBno());

        board.changeTitle(dto.getTitle());
        board.changeContent(dto.getContent());

        log.info(dto + "수정 ...");

        repository.save(board);
    }

    @Transactional
    @Override
    public void remove(Long bno) {
        log.info(bno + "삭제 ...");

        repository.deleteById(bno);
    }

}
