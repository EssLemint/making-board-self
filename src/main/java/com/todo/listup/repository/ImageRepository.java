package com.todo.listup.repository;

import com.todo.listup.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Images, Long>, ImageRepositoryCustom {
}
