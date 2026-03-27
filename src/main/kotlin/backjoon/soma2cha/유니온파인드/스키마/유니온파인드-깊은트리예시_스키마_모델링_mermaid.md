# 유니온파인드 깊은트리예시 스키마 모델링

```mermaid
graph TB
  A1["S0 초기화와 유효성 검사"]
  A2["C1 nodeCnt가 0 이하인가"]
  A3["S1 깊은 체인 트리 구성"]
  A4["S2 find 정의"]
  A5["S3 startNode에서 find 수행"]
  A6["S4 경로 압축 갱신"]
  A7["S_END parent 반환"]

  B1["V0 nodeCnt"]
  B2["V1 parent"]
  B3["V2 startNode"]

  A1 --> A2
  A2 -- "YES" --> A7
  A2 -- "NO" --> A3
  A3 --> A4
  A4 --> A5
  A5 --> A6
  A6 --> A7

  A1 --> B1
  A1 --> B2
  A5 --> B3
```

## S/V 싱크 체크 결과
- S 집합: `S0,S1,S2,S3,S4,S_END` 코드/스키마 일치
- V 집합: `V0,V1,V2` 코드/스키마 일치
