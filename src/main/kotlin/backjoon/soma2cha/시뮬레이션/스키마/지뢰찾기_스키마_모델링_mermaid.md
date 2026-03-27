# 지뢰찾기 알고리즘 상태 전이 그래프

한 다이어그램 안에서 `S`(흐름)와 `V`(상태)를 분리해서 본다.

## 1) 통합 다이어그램 (S+V)

```mermaid
graph TB
  subgraph STATE["V 상태 나열"]
    direction LR
    V0["V0 mineMap"]
    V1["V1 openMap"]
    V2["V2 출력맵 sol"]
    V3["V3 exploded"]
    V4["V4 dr/dc"]
    V5["V5 n"]
    V6["V6 인접지뢰수 cnt"]
  end

  subgraph FLOW["S 실행 흐름"]
    direction TB
    S0["S0 입력 로드<br/>mine/open 로드 : V0,V1,V5"]
    S1["S1 폭발 여부 선검사<br/>열린 지뢰 존재 확인 : V0,V1,V3"]
    S2["S2 모든 칸 순회<br/>출력 작성 루프 : V0,V1,V2,V5"]
    S3["S3 폭발+지뢰칸 검사<br/>exploded && mine ? : V0,V3"]
    S4["S4 지뢰 표시<br/>'*' 반영 : V2"]
    S5["S5 열린 칸 검사<br/>openMap[x][y]=='x' ? : V1"]
    S6["S6 인접 8방향 계산<br/>cnt 계산 : V0,V4,V5,V6"]
    S7["S7 숫자 반영<br/>cnt 문자화 : V2,V6"]
    S8["S8 순회 완료 후 출력<br/>sol 출력 : V2"]

    S0 --> S1 --> S2 --> S3
    S3 -- 예 --> S4 --> S2
    S3 -- 아니오 --> S5
    S5 -- 예 --> S6 --> S7 --> S2
    S5 -- 아니오 --> S2
    S2 -- 완료 --> S8
  end
```

## 2) V 갱신 규칙 (S 단계 기준)

- `S1`: `V3` 폭발 여부 확정
- `S4,S7`: `V2` 출력맵 갱신
- `S6`: `V6` 인접 지뢰 수 계산
- `S8`: `V2` 출력

## 직관 요약

흐름은 `폭발 여부 확정 -> 칸별 출력 작성`이고,
상태 관리는 `V0~V6` 정의표와 갱신 규칙표로 추적한다.
