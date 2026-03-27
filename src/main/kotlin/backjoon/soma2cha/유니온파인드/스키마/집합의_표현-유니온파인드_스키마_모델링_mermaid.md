# 집합의 표현 유니온파인드 스키마 모델링

```mermaid
graph TB
  A1["S0 초기화"]
  A2["S1 find 정의"]
  A3["S2 union 정의"]
  A4["S3 연산 순회"]
  A5["S4 q가 0인가"]
  A6["S4 union 수행"]
  A7["S4 질의 YES 또는 NO 적재"]
  A8["S_END 결과 반환"]

  B1["V0 nodeCnt"]
  B2["V1 parent"]
  B3["V2 answerList"]
  B4["V3 op"]
  B5["V4 q"]
  B6["V5 a"]
  B7["V6 b"]

  A1 --> A2
  A2 --> A3
  A3 --> A4
  A4 --> A5
  A5 -- "YES" --> A6
  A5 -- "NO" --> A7
  A6 --> A4
  A7 --> A4
  A4 --> A8

  A1 --> B1
  A1 --> B2
  A1 --> B3
  A4 --> B4
  A5 --> B5
  A6 --> B6
  A6 --> B7
  A7 --> B6
  A7 --> B7
```

## S/V 싱크 체크 결과
- S 집합: `S0,S1,S2,S3,S4,S_END` 코드/스키마 일치
- V 집합: `V0,V1,V2,V3,V4,V5,V6` 코드/스키마 일치
