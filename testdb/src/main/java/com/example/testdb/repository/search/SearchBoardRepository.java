package com.example.testdb.repository.search;

import com.example.testdb.entity.UserBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
    UserBoard search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
