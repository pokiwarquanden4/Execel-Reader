package com.example.excelreader.repository;

import com.example.excelreader.entity.Template;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImeisRepo extends JpaRepository<Template, Integer> {
    @Query(value = "SELECT * FROM report", nativeQuery = true)
    List<Template> getAllImeis();
    @Query(value = "SELECT * FROM report WHERE `imeis` = :imeis ", nativeQuery = true)
    Template findByImeis(@Param("imeis") String imeis);

    @Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'report'", nativeQuery = true)
    List<String> findHeaders();

    @Modifying
    @Transactional
    @Query(value = "UPDATE report SET `imeis`=:imeis WHERE `id`=:id", nativeQuery = true)
    void updateImeis(@Param("id") int id, @Param("imeis") String imeis);
}
