package ru.diasoft.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.diasoft.micro.domain.SmsVerification;

@Repository
public interface SmsVerificationRepository extends JpaRepository<SmsVerification, Long> {
}