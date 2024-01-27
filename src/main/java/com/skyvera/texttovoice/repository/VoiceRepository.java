package com.skyvera.texttovoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skyvera.texttovoice.entity.VoicesEntity;

@Repository
public interface VoiceRepository extends JpaRepository<VoicesEntity,Long> {

}
