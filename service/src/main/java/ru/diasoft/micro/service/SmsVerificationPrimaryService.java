package ru.diasoft.micro.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.diasoft.micro.controller.dto.SmsVerificationCheckRequest;
import ru.diasoft.micro.controller.dto.SmsVerificationCheckResponse;
import ru.diasoft.micro.controller.dto.SmsVerificationPostRequest;
import ru.diasoft.micro.controller.dto.SmsVerificationPostResponse;
import ru.diasoft.micro.domain.SmsVerification;
import ru.diasoft.micro.repository.SmsVerificationRepository;

import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Primary
public class SmsVerificationPrimaryService implements SmsVerificationService{
    private final SmsVerificationRepository repository;
    @Override
    public SmsVerificationCheckResponse dsSmsVerificationCheck(SmsVerificationCheckRequest smsVerificationCheckRequest) {
        if(repository.findByCodeAndProcessGUID(smsVerificationCheckRequest.getProcessGUID(), smsVerificationCheckRequest.getCode()).isPresent()){
            return new SmsVerificationCheckResponse (true);
        } else {
            return new SmsVerificationCheckResponse (false);
        }
    }

    @Override
    public SmsVerificationPostResponse dsSmsVerificationCreate(SmsVerificationPostRequest smsVerificationPostRequest) {
        String GUID = UUID.randomUUID().toString();
        String code = String.format("%04d", new Random().nextInt(10000));
        SmsVerification smsVerification = SmsVerification.builder()
                .phoneNumber(smsVerificationPostRequest.getPhoneNumber())
                .processGuid(GUID)
                .secretCode(code)
                .status("WAITING")
                .build();
        SmsVerification saved = repository.save(smsVerification);
        return new SmsVerificationPostResponse(saved.getProcessGuid());
    }
}
