package com.tdea.demo.adapters.messages.repository;

import com.tdea.demo.adapters.messages.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}

