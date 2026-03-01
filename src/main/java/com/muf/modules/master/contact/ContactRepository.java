package com.muf.modules.master.contact;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

//    @Query("SELECT c FROM Contact c LEFT JOIN FETCH c.customerAccount WHERE c.id = :id")
    @Query("""
SELECT c FROM Contact c
LEFT JOIN FETCH c.customerAccount
WHERE c.id = :id
""")
    Optional<Contact> findByIdWithCustomerAccount(@Param("id") Integer id);

    @Query("SELECT c FROM Contact c WHERE c.customerAccountId = :customerAccountId")
    List<Contact> findByCustomerAccountId(@Param("customerAccountId") Integer customerAccountId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Contact c WHERE LOWER(c.email) = LOWER(:email) ")
    boolean existByEmailIgnoreCase(@Param("email")String email);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Contact c WHERE LOWER (c.email) = LOWER(:email) AND c.id != :id")
    boolean existsByEmailIgnoreCaseAndIdNot(@Param("email")String email, @Param("id")Integer id);

}
