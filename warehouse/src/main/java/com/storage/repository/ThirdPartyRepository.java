package com.storage.repository;

import com.storage.entity.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
}