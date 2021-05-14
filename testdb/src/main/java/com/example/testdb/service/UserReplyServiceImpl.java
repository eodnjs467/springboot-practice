package com.example.testdb.service;

import com.example.testdb.dto.UserReplyDto;
import com.example.testdb.entity.UserBoard;
import com.example.testdb.entity.UserReply;
import com.example.testdb.repository.UserReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserReplyServiceImpl implements UserReplyService{

    private final UserReplyRepository repository;

    @Override
    @Transactional
    public Long register(UserReplyDto dto) {

        UserReply entity = dtoToEntity(dto);
        repository.save(entity);

        System.out.println(entity);

        return entity.getRno();
    }

    @Override
    public List<UserReplyDto> get(Long bno) {
        List<UserReply> result = repository.getUserRepliesByBoardOrderByRno(UserBoard.builder().bno(bno).build());

        return result.stream().map(userReply -> entityToDto(userReply)).collect(Collectors.toList());
    }

    @Override
    public void modify(UserReplyDto dto) {
        UserReply reply = dtoToEntity(dto);
        repository.save(reply);


//        Optional<UserReply> result = repository.findById(dto.getRno());
//        UserReply userReply = result.get();
//        userReply.changeContent(dto.getContent());


//        Optional<UserReply> userReply = dtoToEntity(dto);
//        UserReply userReply = repository.getOne(dto.getRno());
//        userReply.changeContent(dto.getContent());

//        repository.save(userReply);
    }

    @Override
    public void remove(Long rno) {
        repository.deleteById(rno);

    }

}
