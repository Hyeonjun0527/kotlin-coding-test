# 64 크루스칼 MST 스키마 모델링

```mermaid
graph TB
  A1["S0 상태 초기화"]
  A2["S1 간선 정렬"]
  A3["S2 find와 union 정의"]
  A4["S3 간선 순회"]
  A5["S4 같은 집합인가"]
  A6["S4 union 후 usedEdge와 totalWeight 갱신"]
  A7["S5 usedEdge가 n-1인가"]
  A8["S_END 정답 반환"]

  B1["V0 nodeCnt"]
  B2["V1 graph"]
  B3["V2 parent"]
  B4["V3 usedEdge"]
  B5["V4 totalWeight"]
  B6["V5 offset"]

  A1 --> A2
  A2 --> A3
  A3 --> A4
  A4 --> A5
  A5 -- "YES" --> A4
  A5 -- "NO" --> A6
  A6 --> A4
  A4 --> A7
  A7 -- "YES" --> A8
  A7 -- "NO" --> A8

  A1 --> B1
  A1 --> B3
  A1 --> B4
  A1 --> B5
  A1 --> B6
  A2 --> B2
```

## S/V 싱크 체크 결과
- S 집합: `S0,S1,S2,S3,S4,S5,S_END` 코드/스키마 일치
- V 집합: `V0,V1,V2,V3,V4,V5` 코드/스키마 일치
