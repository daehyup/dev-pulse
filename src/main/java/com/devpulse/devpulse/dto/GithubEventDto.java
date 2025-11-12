// Github API 응답은 이벤트의 배, 이 DTO는 그 이벤트 1개를 담는 최상위 설계도
package com.devpulse.devpulse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class GithubEventDto {

    private String type; // pushEvent인지 필터링
    private GithubPayloadDto payload; //

}
