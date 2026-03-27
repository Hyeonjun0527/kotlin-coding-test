# 안전지대 알고리즘 상태 전이 그래프

한 다이어그램 안에서 `S`(흐름)와 `V`(상태)를 분리해서 본다.

## 1) 통합 다이어그램 (S+V)

```mermaid
graph TB
  subgraph STATE["V 상태 나열"]
    direction LR
    V0["V0 board"]
    V1["V1 danger"]
    V2["V2 dx/dy"]
    V3["V3 n"]
    V4["V4 safeCount"]
  end

  subgraph FLOW["S 실행 흐름"]
    direction TB
    S0["S0 입력 보드 로드<br/>board 로드 : V0,V3"]
    S1["S1 danger 초기화<br/>0으로 채움 : V1,V3"]
    S2["S2 모든 칸 순회<br/>x,y 반복 : V0,V3"]
    S3["S3 지뢰 여부 검사<br/>board[x][y]==1 ? : V0"]
    S4["S4 주변 9칸 위험 마킹<br/>danger=1 : V1,V2,V3"]
    S5["S5 순회 완료<br/>마킹 종료 : V1"]
    S6["S6 안전 칸 카운트<br/>danger==0 count : V1,V4"]
    S7["S7 결과 반환<br/>safeCount 반환 : V4"]

    S0 --> S1 --> S2 --> S3
    S3 -- 예 --> S4 --> S2
    S3 -- 아니오 --> S2
    S2 -- 완료 --> S5 --> S6 --> S7
  end
```

## 2) V 갱신 규칙 (S 단계 기준)

- `S1`: `V1` 초기화
- `S4`: `V1` 위험 마킹
- `S6`: `V4` 안전 칸 카운트
- `S7`: `V4` 반환

## 직관 요약

흐름은 `위험 마킹 -> 안전 칸 카운트` 두 단계로 단순하고,
상태 관리는 `V0~V4` 정의표와 갱신 규칙표로 추적한다.
