package com.example.master.repository;

import com.example.master.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByKeycloakUserId(String keycloakUserId);

    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.mobile = :mobile, u.district = :district, u.cdpo = :cdpo, u.sectors = :sectors WHERE u.keycloakUserId = :keycloakUserId")
    int updateUserProfile(
            @Param("keycloakUserId") String keycloakUserId,
            @Param("name") String name,
            @Param("mobile") String mobile,
            @Param("district") String district,
            @Param("cdpo") String cdpo,
            @Param("sectors") String sectors
    );
}
