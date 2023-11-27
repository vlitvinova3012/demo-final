package ru.diasoft.micro.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.micro.controller.dto.SmsVerificationCheckRequest;
import ru.diasoft.micro.controller.dto.SmsVerificationCheckResponse;
import ru.diasoft.micro.controller.dto.SmsVerificationPostRequest;
import ru.diasoft.micro.controller.dto.SmsVerificationPostResponse;
import ru.diasoft.micro.domain.SmsVerification;
import ru.diasoft.micro.repository.SmsVerificationRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SmsVerificationPrimaryServiceTest {

    @Mock
    private SmsVerificationRepository repository;

    @InjectMocks
    private SmsVerificationPrimaryService service;

    private SmsVerificationCheckRequest checkRequest;
    private SmsVerificationPostRequest postRequest;
    private SmsVerification smsVerification;

    @BeforeEach
    void setUp() {
        checkRequest = new SmsVerificationCheckRequest();
        checkRequest.setProcessGUID("1234");
        checkRequest.setCode("5678");

        postRequest = new SmsVerificationPostRequest();
        postRequest.setPhoneNumber("1234567890");

        smsVerification = new SmsVerification();
        smsVerification.setProcessGUID("1234");
        smsVerification.setCode("5678");
    }

    @Test
    void testDsSmsVerificationCheckWhenCodeAndProcessGUIDFoundThenReturnTrue() {
        when(repository.findByCodeAndProcessGUID(checkRequest.getProcessGUID(), checkRequest.getCode())).thenReturn(Optional.of(smsVerification));

        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(checkRequest);

        assertTrue(response.getCheckResult());
        verify(repository, times(1)).findByCodeAndProcessGUID(checkRequest.getProcessGUID(), checkRequest.getCode());
    }

    @Test
    void testDsSmsVerificationCheckWhenCodeAndProcessGUIDNotFoundThenReturnFalse() {
        when(repository.findByCodeAndProcessGUID(checkRequest.getProcessGUID(), checkRequest.getCode())).thenReturn(Optional.empty());

        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(checkRequest);

        assertFalse(response.getCheckResult());
        verify(repository, times(1)).findByCodeAndProcessGUID(checkRequest.getProcessGUID(), checkRequest.getCode());
    }

    @Test
    void testDsSmsVerificationCreateWhenCodeAndProcessGUIDFoundThenReturnResponseWithProcessGUID() {
        when(repository.save(any(SmsVerification.class))).thenReturn(smsVerification);

        SmsVerificationPostResponse response = service.dsSmsVerificationCreate(postRequest);

        assertEquals(smsVerification.getProcessGUID(), response.getProcessGUID());
        verify(repository, times(1)).save(any(SmsVerification.class));
    }

    @Test
    void testDsSmsVerificationCreateWhenCodeAndProcessGUIDNotFoundThenReturnResponseWithProcessGUID() {
        when(repository.save(any(SmsVerification.class))).thenReturn(smsVerification);

        SmsVerificationPostResponse response = service.dsSmsVerificationCreate(postRequest);

        assertEquals(smsVerification.getProcessGUID(), response.getProcessGUID());
        verify(repository, times(1)).save(any(SmsVerification.class));
    }
}