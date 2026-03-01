package com.muf.modules.master.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Integer> {

    @Query("SELECT ca FROM CustomerAccount ca LEFT JOIN FETCH ca.owner WHERE ca.id = :id")
    Optional<CustomerAccount> findByIdWithOwner(@Param("id") Integer id);

    @Query("SELECT ca FROM CustomerAccount ca WHERE ca.ownerId = :ownerId")
    List<CustomerAccount> findByOwnerId(@Param("ownerId") Integer ownerId);

    @Query("SELECT CASE WHEN COUNT(ca) > 0 THEN true ELSE false END FROM CustomerAccount ca WHERE LOWER(ca.companyName) = LOWER(:companyName)")
    boolean existsByCompanyNameIgnoreCase(@Param("companyName") String companyName);

    @Query("SELECT CASE WHEN COUNT(ca) > 0 THEN true ELSE false END FROM CustomerAccount ca WHERE LOWER(ca.companyName) = LOWER(:companyName) AND ca.id != :id")
    boolean existsByCompanyNameIgnoreCaseAndIdNot(@Param("companyName") String companyName, @Param("id") Long id);
}