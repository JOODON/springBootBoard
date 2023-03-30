package com.example.springBootBoard.repository;

import com.example.springBootBoard.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity,Long> {

}
