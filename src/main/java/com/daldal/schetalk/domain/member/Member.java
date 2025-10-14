package com.daldal.schetalk.domain.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 ID
    private Long memberId;

    @Column(nullable = false)
    @Schema(description = "회원 아이디", example = "changmin")
    private String email;

    @Column(nullable = false)
    @Schema(description = "회원 비밀번호", example = "1234")
    private String password;

}
