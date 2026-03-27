# 64clear 우선순위큐 MST 스키마 모델링

```mermaid
graph TB
  A1["S0 상태 초기화와 힙 적재"]
  A2["S1 find와 union 정의"]
  A3["S2 크루스칼 루프"]
  A4["S3 최소 간선 추출"]
  A5["S4 같은 집합인가"]
  A6["S4 union 후 누적값 갱신"]
  A7["S5 usedEdge가 n-1인가"]
  A8["S_END 정답 반환"]

  B1["V0 nodeCnt"]
  B2["V1 parent"]
  B3["V2 edgePQ"]
  B4["V3 usedEdge"]
  B5["V4 totalWeight"]

  A1 --> A2
  A2 --> A3
  A3 --> A4
  A4 --> A5
  A5 -- "YES" --> A3
  A5 -- "NO" --> A6
  A6 --> A3
  A3 --> A7
  A7 -- "YES" --> A8
  A7 -- "NO" --> A8

  A1 --> B1
  A1 --> B2
  A1 --> B3
  A1 --> B4
  A1 --> B5
```

## S/V 싱크 체크 결과
- S 집합: `S0,S1,S2,S3,S4,S5,S_END` 코드/스키마 일치
- V 집합: `V0,V1,V2,V3,V4` 코드/스키마 일치
