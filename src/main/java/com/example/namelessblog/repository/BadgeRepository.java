package com.example.namelessblog.repository;

import com.example.namelessblog.domain.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
