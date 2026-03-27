# 파이어볼 알고리즘 상태 전이 그래프

한 다이어그램 안에서 `S`(흐름)와 `V`(상태)를 분리해서 본다.

## 1) 통합 다이어그램 (S+V)

```mermaid
graph TB
  subgraph STATE["V 상태 나열"]
    direction LR
    V0["V0 fireballs"]
    V1["V1 buckets"]
    V2["V2 N"]
    V3["V3 라운드 상태 K"]
    V4["V4 dr/dc"]
    V5["V5 칸 집계(sumM,sumS,parity)"]
    V6["V6 newM/newS/dirs"]
    V7["V7 최종 질량합 sol"]
  end

  subgraph FLOW["S 실행 흐름"]
    direction TB
    S0["S0 초기상태<br/>파이어볼 목록 입력 : V0,V2,V3,V4"]
    S1["S1 라운드 시작<br/>1..K 반복 : V3"]
    S2["S2 이동 단계<br/>모든 파이어볼 이동/적재 : V0,V1,V2,V4"]
    S3["S3 재구성 준비<br/>fireballs 비우기 : V0"]
    S4["S4 각 칸 순회<br/>bucket cell 처리 : V1"]
    S5["S5 칸 비어있는지 검사<br/>empty ? : V1"]
    S6["S6 1개 유지 여부<br/>size==1 이면 유지 : V0,V1"]
    S7["S7 2개 이상 집계<br/>합산/홀짝 계산 : V1,V5"]
    S8["S8 새 질량 계산<br/>newM=sumM/5 : V5,V6"]
    S9["S9 새 질량 0 검사<br/>newM==0 ? : V6"]
    S10["S10 소멸 처리<br/>추가 없음 : V0,V6"]
    S11["S11 4개 생성<br/>newS/dirs로 생성 : V0,V6"]
    S12["S12 라운드 상태 확정<br/>다음 라운드로 : V0,V3"]
    S13["S13 최종 질량 합 출력<br/>sum(m) 출력 : V0,V7"]

    S0 --> S1 --> S2 --> S3 --> S4 --> S5
    S5 -- 예 --> S4
    S5 -- 아니오 --> S6
    S6 -- 예 --> S4
    S6 -- 아니오 --> S7 --> S8 --> S9
    S9 -- 예 --> S10 --> S4
    S9 -- 아니오 --> S11 --> S4
    S4 -- 순회완료 --> S12 --> S1
    S1 -- K회 완료 --> S13
  end
```

## 2) V 갱신 규칙 (S 단계 기준)

- `S2`: `V1` 이동 결과 적재
- `S3`: `V0` 재구성 시작(clear)
- `S7,S8`: `V5,V6` 집계/파생값 계산
- `S10,S11`: `V0` 소멸/생성 반영
- `S13`: `V7` 계산 후 출력

## 직관 요약

흐름은 `이동 -> 칸별 재구성` 라운드를 반복하고,
상태 관리는 `V0~V7` 정의표와 갱신 규칙표로 추적한다.
