package ru.diasoft.micro.domain;

import lombok.*;

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
    @SequenceGenerator(name = "sms_verification_verificationid_seq", sequenceName = "sms_verification_verificationid_seq", allocationSize = 1)
    @Column(name = "verificationid", columnDefinition="numeric(15,0)")
    @EqualsAndHashCode.Exclude
    private Long verificationId;

    @Column(name = "processguid", columnDefinition="varchar(255)")
    private String processGUID;

    @Column(name = "phonenumber", columnDefinition="varchar(255)")
    private String phoneNumber;

    @Column(name = "code", columnDefinition="varchar(255)")
    private String code;

    @Column(name = "status", columnDefinition="varchar(255)")
    private String status;
}
