package com.muf.modules.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TranslationRepository extends JpaRepository<Translation, Integer> {

    @Query("""
        select t.value
        from Translation t
        join t.translationKey k 
        join t.lang l 
        where k.key = :key
        and l.code = :langCode
        and t.isDeleted = 0
    """)
    Optional<String> findValueByKeyAndLang(
        @Param("key") String key,
        @Param("langCode") String langCode
    );
}
