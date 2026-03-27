# 엘리베이터 알고리즘 상태 전이 그래프

한 다이어그램 안에서 `S`(흐름)와 `V`(상태)를 분리해서 본다.

## 1) 통합 다이어그램 (S+V)

```mermaid
graph TB
  subgraph STATE["V 상태 나열"]
    direction LR
    V0["V0 현재층 currentFloor"]
    V1["V1 요구큐 requestQueue"]
    V2["V2 대기중인층맵 waitingFloorMap"]
    V3["V3 층별대기개수 waitingCountByFloor"]
    V4["V4 현재시간 currentTime"]
    V5["V5 방향 dirUp"]
    V6["V6 처리층수기록 processedFloors"]
  end

  subgraph FLOW["S 실행 흐름"]
    direction TB
    S0["S0 초기화<br/>t=1, floor=1, dir=UP : V0,V1,V2,V3,V4,V5,V6"]
    S1["S1 루프 시작"]
    S2["S2 종료조건 검사<br/>요구큐 비었고 대기중인층 비었는지 확인 : V1,V2"]
    S3["S3 요청 유입 처리<br/>요구큐의 현재시간 요청을 대기로 편입 : V1,V2,V3"]
    S4["S4 현재층 즉시 처리<br/>층별대기개수[currentFloor] 모두 처리 : V2,V3,V6"]
    S5["S5 대기 존재 검사<br/>대기중인층이 비었는지 확인 : V2"]
    S6["S6 대기 없음<br/>이동 없이 시간 +1 : V4"]
    S7["S7 방향 결정<br/>위/아래 대기 유무로 유지 또는 전환 : V2,V5"]
    S8["S8 벽 보정<br/>1층/ n층에서 방향 강제 : V0,V5"]
    S9["S9 1층 이동<br/>현재 방향대로 한 층 이동 : V0,V5"]
    S10["S10 시간 +1<br/>현재시간 증가 : V4"]
    S_END["S_END 종료<br/>처리층수기록 반환"]

    S0 --> S1 --> S2
    S2 -- 종료 --> S_END
    S2 -- 계속 --> S3 --> S4 --> S5
    S5 -- 대기요청 없음 --> S6 --> S1
    S5 -- 대기요청 있음 --> S7 --> S8 --> S9 --> S10 --> S1
  end
```

## 2) V 갱신 규칙 (S 단계 기준)

- `S0`: `V0,V1,V2,V3,V4,V5,V6` 초기화
- `S3`: `V1`에서 현재시간 요청 pop, `V2,V3`에 대기 적재
- `S4`: 현재층 처리로 `V3,V2` 감소/정리, `V6` 기록
- `S6`: `V4` 증가
- `S7`: `V5` 방향 판단
- `S8`: `V5` 벽 보정
- `S9`: `V0` 이동
- `S10`: `V4` 증가

## 직관 요약

흐름은 `S0 -> ... -> S10`으로 고정되어 반복되고,
상태 관리는 `V0~V6` 정의표와 갱신 규칙표로 따로 추적한다.
