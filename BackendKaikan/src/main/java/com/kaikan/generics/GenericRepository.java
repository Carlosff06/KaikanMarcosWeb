package com.kaikan.generics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean

public interface GenericRepository < T extends BaseEntity, Long> extends JpaRepository < T, Long > {
}

