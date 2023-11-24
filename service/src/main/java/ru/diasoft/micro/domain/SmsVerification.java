package ru.diasoft.micro.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sms_verification")
public class SmsVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_verification_verificationid_seq")
    @SequenceGenerator(name = "sms_verification_verificationid_seq", sequenceName = "sms_verification_verificationid_seq")
    @Column(name = "verificationid")
    private Long verificationId;
    @Column(name = "processguid")
    private String processGUID;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "code")
    private String code;
    @Column(name = "status")
    private String status;
}
