package com.todo.listup.signIn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SignInRepository extends JpaRepository<Sign, Long>, SignInRepositoryCustom {
}
