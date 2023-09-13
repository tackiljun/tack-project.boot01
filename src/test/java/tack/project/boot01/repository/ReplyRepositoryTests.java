package tack.project.boot01.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.extern.log4j.Log4j2;
import tack.project.boot01.domain.Board;
import tack.project.boot01.domain.Reply;
import tack.project.boot01.dto.ReplyPageRequestDTO;
import tack.project.boot01.service.ReplyService;


@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    /////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    private ReplyRepository replyRepository;

    /////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    private ReplyService replyService;

    /////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void insertOne() {

        Long bno = 100L;

        Board board = Board.builder().bno(bno).build();

        Reply reply = Reply.builder()
        .replyText("Reply.....1")
        .replyer("replyer00")
        .board(board)
        .build();

        replyRepository.save(reply);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testInsertDummies() {

        Long[] bnoArr = {99L, 95L, 91L, 85L, 81L};

        for (Long bno : bnoArr) {

            Board board = Board.builder().bno(bno).build();

            for(int i = 0; i < 5; i++) {
                
                Reply reply = Reply.builder()
                .replyText("Reply....."+bno+"--"+i)
                .replyer("replyer"+i)
                .board(board)
                .build();

                replyRepository.save(reply);

            }

        } //end forEach.

    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testListBoard() {

        Long bno = 99L;

        Pageable pageable = 
            PageRequest.of(0, 10, Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listBoard(bno, pageable);

        result.get().forEach(r -> log.info(r));

    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testCount() {

        Long bno = 99L;

        Long count = replyRepository.getCountBoard(bno);

        log.info("count: " + count);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testListLast() {

        ReplyPageRequestDTO requestDTO = 
            ReplyPageRequestDTO.builder().bno(99L).last(true).build();

        //replyService.list(requestDTO);

        log.info(replyService.list(requestDTO));

    }
    
}
