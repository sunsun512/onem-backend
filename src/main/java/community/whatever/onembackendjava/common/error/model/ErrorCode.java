package community.whatever.onembackendjava.common.error.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DB001("DB001","잘못된 요청입니다."),
    DB002("DB002","데이터를 찾을 수 없습니다."),
    DB003("DB003","참조키를 찾을 수 없습니다."),
    DB004("DB004","서비스를 이용할 수 없는 도메인입니다."),

    RU001("RU001", "csv 파일 읽기에 실패했습니다.");

    private final String errorCode;
    private final String message;
}
