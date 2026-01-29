package com.muf.modules.master.user.repository;

import com.muf.modules.master.user.entity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
//public interface UserRepository extends JpaRepository<User, Integer> {
//    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = 0")
//    Optional<User> findByEmail(@Param("email") String email);
//
//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email AND u.isDeleted = 0")
//    boolean existsByEmail(@Param("email") String email);
//
//}

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query("""
//        SELECT DISTINCT u
//        FROM User u
//        LEFT JOIN FETCH u.userRoleList ur
//        LEFT JOIN FETCH ur.role
//        WHERE u.email = :email
//        AND u.isDeleted = 0
//    """)
//    Optional<User> findByEmailWithRoles(@Param("email") String email);

    @Query("""
SELECT DISTINCT u FROM User u
LEFT JOIN FETCH u.userRoleList ur
LEFT JOIN FETCH ur.role
WHERE u.email = :email
""")
    Optional<User> findByEmailWithRoles(@Param("email") String email);


    @Query("""
        SELECT DISTINCT u
        FROM User u
        LEFT JOIN FETCH u.userRoleList ur
        LEFT JOIN FETCH ur.role
        WHERE u.id = :id
        AND u.isDeleted = 0
    """)
    Optional<User> findByIdWithRoles(@Param("id") Integer id);

    @Query("""
        SELECT COUNT(u) > 0
        FROM User u
        WHERE u.email = :email
        AND u.isDeleted = 0
    """)
    boolean existsByEmail(@Param("email") String email);
}

