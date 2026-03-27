# 65 다리 만들기 2 스키마 모델링

```mermaid
graph TB
  A1["S0 지도와 상태 초기화"]
  A2["S1 bfs 정의"]
  A3["S2 섬 라벨링 루프"]
  A4["S3 다리 후보 생성"]
  A5["S4 크루스칼 수행"]
  A6["S5 모든 섬 연결 성공인가"]
  A7["S_END 결과 반환"]

  B1["V0 xCnt"]
  B2["V1 yCnt"]
  B3["V2 graph"]
  B4["V3 visited"]
  B5["V4 dx"]
  B6["V5 dy"]
  B7["V6 islandList"]
  B8["V7 islandCnt"]
  B9["V8 edges"]
  B10["V9 parent"]
  B11["V10 usedEdge"]
  B12["V11 totalWeight"]

  A1 --> A2
  A2 --> A3
  A3 --> A4
  A4 --> A5
  A5 --> A6
  A6 -- "YES" --> A7
  A6 -- "NO" --> A7

  A1 --> B1
  A1 --> B2
  A1 --> B3
  A1 --> B4
  A1 --> B5
  A1 --> B6
  A1 --> B7
  A1 --> B8
  A4 --> B9
  A5 --> B10
  A5 --> B11
  A5 --> B12
```

## S/V 싱크 체크 결과
- S 집합: `S0,S1,S2,S3,S4,S5,S_END` 코드/스키마 일치
- V 집합: `V0,V1,V2,V3,V4,V5,V6,V7,V8,V9,V10,V11` 코드/스키마 일치
