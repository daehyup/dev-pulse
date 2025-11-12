// commits
// 커밋 갯수 새는 클래스, '커밋 1개를 담는 최하위 설계도'
package com.devpulse.devpulse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class GithubCommitDto {

}
