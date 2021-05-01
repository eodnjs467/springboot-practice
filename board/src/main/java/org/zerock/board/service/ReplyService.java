package org.zerock.board.service;

import org.zerock.board.dto.ReplyDto;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDto replyDto);

    List<ReplyDto> getList(Long bno);

    void modify(ReplyDto replyDto);

    void remove(Long rno);

    //ReplyDto -> Reply객체  , Board 객체 처리 수반됨
    default Reply dtoToEntity(ReplyDto replyDto) {
        Board board = Board.builder().bno(replyDto.getBno()).build();

        Reply reply = Reply.builder()
                .rno(replyDto.getRno())
                .text(replyDto.getText())
                .replyer(replyDto.getReplyer())
                .board(board)
                .build();
        return reply;
    }

    //Reply 객체를 ReplyDto로 변환 Board 객체가 필요하지 않으므로 게시물 번호만
    default ReplyDto entityToDto(Reply reply) {
        ReplyDto dto = ReplyDto.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return dto;
    }

}
