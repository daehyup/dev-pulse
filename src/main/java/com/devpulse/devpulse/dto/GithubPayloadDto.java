// GithubEventDTO안에 중첩된 payload: {} 객체를 담는 '중간 설계도'
package com.devpulse.devpulse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class GithubPayloadDto {

    private List<GithubCommitDto> commits;

}
