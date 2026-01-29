package com.muf.modules.master.user.repository;

import com.muf.modules.master.user.entity.domain.RefreshToken;
import com.muf.modules.master.user.entity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
//    @Query("SELECT rt FROM RefreshToken rt WHERE rt.token = :token AND rt.isDeleted = 0")
//    Optional<RefreshToken> findByToken(@Param("token") String token);

    @Modifying
    @Query("UPDATE RefreshToken rt SET rt.isDeleted = 1, rt.updatedAt = CURRENT_TIMESTAMP WHERE rt.user = :user")
    void softDeleteByUser(@Param("user") User user);

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query("DELETE FROM RefreshToken rt WHERE rt.user = :user")
    void deleteByUser(@Param("user") User user);
}