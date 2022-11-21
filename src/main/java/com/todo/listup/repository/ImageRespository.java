package com.todo.listup.repository;

import com.todo.listup.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRespository extends JpaRepository<Images, Long>, ImageRepositoryCustom {
}
