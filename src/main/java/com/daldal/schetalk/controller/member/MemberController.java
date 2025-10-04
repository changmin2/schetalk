package com.daldal.schetalk.controller.member;

import com.daldal.schetalk.common.ScheTalkApiResponse;
import com.daldal.schetalk.domain.member.Member;
import com.daldal.schetalk.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "Member API", description = "회원가입/로그인 관련 API")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    @Operation(summary = "회원가입", description = "username과 password를 입력하여 회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = ScheTalkApiResponse.class))),
            @ApiResponse(responseCode = "409", description = "아이디 중복",
                    content = @Content(schema = @Schema(implementation = ScheTalkApiResponse.class)))
    })
    public ScheTalkApiResponse<Long> join(@RequestBody Map<String,String> user){

        //아이디가 중복 됐을때
        if(memberService.duplicate(user.get("username")) == false){
            return new ScheTalkApiResponse<>(false, "회원가입 실패",1L);
        }else{
            String username = user.get("username");
            String password = passwordEncoder.encode(user.get("password"));

            memberService.join(username,password);

            return new ScheTalkApiResponse<>(true, "회원가입 성공",1L);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "username, password로 로그인")
    public ScheTalkApiResponse<Member> login(@RequestBody Map<String,String> user) {
        Member member = memberService.getMember(user.get("username"))
                .orElse(null);

        if (member == null) {
            return new ScheTalkApiResponse<>(false, "가입되지 않은 ID 입니다.", null);
        }

        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            return new ScheTalkApiResponse<>(false, "잘못된 비밀번호 입니다.", null);
        }


        return new ScheTalkApiResponse<>(true, "로그인 성공", member);
    }

}
