package com.pepper.backend.repository;

import com.pepper.backend.dto.UsernameAndEmailDto;
import com.pepper.backend.dto.UsernameDto;
import com.pepper.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByUsername(String username);

    List<UsernameAndEmailDto> findAllProjectedBy();

    UsernameAndEmailDto findByUserId(Long id);

    List<UsernameDto> findTop5ByUsernameContains(String username);
}
