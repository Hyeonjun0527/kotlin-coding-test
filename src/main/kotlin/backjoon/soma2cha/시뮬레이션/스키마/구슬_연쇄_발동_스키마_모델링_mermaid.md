# 구슬 연쇄 발동 알고리즘 상태 전이 그래프

한 다이어그램 안에서 `S`(흐름)와 `V`(상태)를 분리해서 본다.

## 1) 통합 다이어그램 (S+V)

```mermaid
graph TB
  subgraph STATE["V 상태 나열"]
    direction LR
    V0["V0 임계치맵 thr"]
    V1["V1 typeStack"]
    V2["V2 manaStack"]
    V3["V3 size"]
    V4["V4 activations"]
    V5["V5 현재 입력 구슬(type,mana)"]
    V6["V6 changed"]
    V7["V7 remain"]
  end

  subgraph FLOW["S 실행 흐름"]
    direction TB
    S0["S0 초기화<br/>thr/스택/카운터 준비 : V0,V1,V2,V3,V4"]
    S1["S1 다음 구슬 읽기<br/>입력 1개 로드 : V5"]
    S2["S2 push<br/>오른쪽 끝에 적재 : V1,V2,V3,V5"]
    S3["S3 안정화 루프 시작<br/>변화 여부 추적 시작 : V6"]
    S4["S4 병합 가능 검사<br/>top2 타입 동일? : V1,V3"]
    S5["S5 병합 수행<br/>마나 합치고 size 감소 : V2,V3"]
    S6["S6 발동 가능 검사<br/>mana >= threshold ? : V0,V1,V2,V3"]
    S7["S7 발동 처리<br/>k 누적 : V0,V2,V3,V4"]
    S8["S8 remain 확인<br/>m % threshold : V7"]
    S9["S9 소멸(pop)<br/>remain==0 : V3"]
    S10["S10 top 갱신<br/>remain>0 : V2,V7"]
    S11["S11 변화 여부 검사<br/>changed ? : V6"]
    S12["S12 다음 입력으로 이동<br/>for-loop 진행 : V5"]
    S_END["S_END 종료<br/>총 발동 횟수 반환 : V4"]

    S0 --> S1 --> S2 --> S3 --> S4
    S4 -- 예 --> S5 --> S4
    S4 -- 아니오 --> S6
    S6 -- 예 --> S7 --> S8
    S8 -- 예 --> S9 --> S6
    S8 -- 아니오 --> S10 --> S6
    S6 -- 아니오 --> S11
    S11 -- 예 --> S3
    S11 -- 아니오 --> S12 --> S1
    S1 -- 입력 소진 --> S_END
  end
```

## 2) V 갱신 규칙 (S 단계 기준)

- `S0`: `V0,V1,V2,V3,V4` 초기화
- `S2`: `V1,V2,V3` push 반영
- `S5`: `V2,V3` 병합 반영
- `S7`: `V4` 발동 횟수 누적
- `S9,S10`: `V3` 소멸 또는 `V2` 잔여 마나 반영

## 직관 요약

흐름은 `push -> 병합 연쇄 -> 발동/소멸 연쇄 -> 안정화`를 반복하고,
상태 관리는 `V0~V7` 정의표와 갱신 규칙표로 추적한다.
