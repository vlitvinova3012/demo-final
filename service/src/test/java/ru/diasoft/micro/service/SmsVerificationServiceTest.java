package ru.diasoft.micro.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.controller.dto.SmsVerificationCheckRequest;
import ru.diasoft.micro.controller.dto.SmsVerificationCheckResponse;
import ru.diasoft.micro.controller.dto.SmsVerificationPostRequest;
import ru.diasoft.micro.controller.dto.SmsVerificationPostResponse;
import ru.diasoft.micro.repository.SmsVerificationRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SmsVerificationServiceTest {
    @Mock
    private SmsVerificationRepository repository;
    private SmsVerificationPrimaryService service;
    private SmsVerificationPostRequest postRequest;
    private SmsVerificationCheckRequest checkRequest;
    private final String GUID = UUID.randomUUID().toString();
    private final String VALID_CODE = "007";
    private final String INVALID_CODE = "008";

    @Before
    public void init() {
        service = new SmsVerificationPrimaryService(repository);
        postRequest = new SmsVerificationPostRequest("+78054");
        checkRequest = new SmsVerificationCheckRequest(GUID, VALID_CODE);
    }

    @Test
    void dsSmsVerificationCheckValidCode() {
        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(checkRequest);
        assertTrue(response.getCheckResult());
    }

    @Test
    void dsSmsVerificationCheckInvalidCode() {
        checkRequest.setCode(INVALID_CODE);
        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(checkRequest);
        assertFalse(response.getCheckResult());
    }

    @Test
    void dsSmsVerificationCreate() {
        SmsVerificationPostResponse response = service.dsSmsVerificationCreate(postRequest);
        assertTrue(!response.getProcessGUID().isEmpty());
    }
}