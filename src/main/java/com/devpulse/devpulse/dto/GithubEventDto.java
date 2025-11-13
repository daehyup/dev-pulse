// Github API 응답은 이벤트의 배, 이 DTO는 그 이벤트 1개를 담는 최상위 설계도
// JSON 배열 [ {...}, {...} ] 안의 '이벤트 1개' { ... }를 담을 설계도
package com.devpulse.devpulse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true) // 필드에 없는 JSON 필드는 전부 무시, 에러 x
@Getter
@Setter
@NoArgsConstructor // new GithubEventDto() 로 빈 객체를 만들 때 필요
public class GithubEventDto {

    private String type; // pushEvent인지 필터링
    private GithubPayloadDto payload; // GithubPayloadDto 타입의 객체를 담음

}
