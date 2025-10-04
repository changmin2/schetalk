package com.daldal.schetalk.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheTalkApiResponse<T> {
    @Schema(description = "성공 여부", example = "true")
    private boolean success; // 성공 여부
    @Schema(description = "설명 메시지", example = "회원가입 성공")
    private String message;  // 상태 메시지
    @Schema(description = "실제 데이터")
    private T data;          // 실제 반환 데이터
}
