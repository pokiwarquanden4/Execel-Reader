package com.example.excelreader.repository;

import com.example.excelreader.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM account WHERE `name` = :name ", nativeQuery = true)
    User findByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE account SET `name`=:name, `pass`=:pass WHERE `id`=:id", nativeQuery = true)
    void updateUser(@Param("id") int id, @Param("name") String name, @Param("pass") String pass);
}
