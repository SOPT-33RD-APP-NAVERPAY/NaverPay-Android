# NaverPay-Android

<table>
  <tbody>
    <tr>
      <td align="center" width="33%">
        <a href="https://github.com/librarywon">
          <img src="https://github.com/SOPT-33RD-APP-NAVERPAY/NaverPay-Android/assets/52442547/a669ab33-6e72-49b3-a40c-2ac53a1321de" width="200px;" alt=""/>
        </a>
      </td>
      <td align="center" width="33%">
        <a href="https://github.com/Junseo511">
          <img src="https://github.com/SOPT-33RD-APP-NAVERPAY/NaverPay-Android/assets/52442547/90f04347-14b0-4b51-bfbd-9078f7104a85" width="200px;" alt=""/>
        </a>
      </td>
      <td align="center" width="33%">
        <a href="https://github.com/minemi00">
          <img src="https://github.com/SOPT-33RD-APP-NAVERPAY/NaverPay-Android/assets/52442547/69e51c38-58aa-4770-bed4-1d4457d10279" width="200px;" alt=""/>
        </a>
      </td>
    </tr>
    <tr>
      <td align="center"><b>서재원(👑)</b></td>
      <td align="center"><b>허민회</b></td>
      <td align="center"><b>최준서</b></td>
    </tr>
    <tr>
      <td align="center">담당 뷰: Benefit, Point</td>
      <td align="center">담당 뷰: Place</td>
      <td align="center">담당 뷰: Home</td>
    </tr>
  </tbody>
</table>

## Work Space

[Notion](https://librarywon.notion.site/DO-SOPT-NaverPay-e59308c11e8c40c1bec94963c8075589?pvs=4)

## Directory Tree

```kotlin
├─ app/network
│   ├─ network
│   │   ├─ ApiFactory
│   │   ├─ Dto
│   │   │   ├─ home
│   │   │   ├─ place
│   │   │   ├─ benefit
│   │   │   └─ point
│   │   └─ Service
│   ├─ domain
│   │   └─ model
│   └─ ui
│       ├─ main
│       │   ├─ home
│       │   ├─ place
│       │   ├─ benefit
│       │   └─ point
│       └─ util
│   
```

## Git Branch

feature 단위로 브랜치를 구성합니다.

- 해당 작업을 위한 issue를 먼저 생성하고 브랜치를 파서 작업합니다.
- 작업 완료 후 PR을 날려서 코드리뷰를 받은 뒤 머지합니다. 머지한 뒤에 브랜치는 사용하지 않습니다.
    - Review 는 PR 이 올라오고 24h 안에 이루어져야합니다.
    - approve 가 모두 이루어져야 main 으로 merge 가 가능합니다.

예시)

- feature/color_setting
- feature/font_setting
- feature/main
- feature/home

## Git Commit Convention

### Commit Message

- [{tag}] : {content}
    - tag
        - 커밋의 단위를 표현해주세요.
    - content
        - 해당 커밋에 담긴 구현 내용을 공유해주세요
            - 여러 책임들이 한 커밋에 있지 않도록 주의해주세요.
- 구현을 하고 commit 을 쪼개지 말고, 작업을 하면서 수시로 commit 을 남겨주세요.

### Tag

- [FEAT] : 새로운 기능 구현 (UI작업도)
- [ADD] : 부수적인 코드 추가 및 라이브러리 추가, 새로운 파일 생성
- [CHORE] : 버전 코드 수정, 패키지 구조 변경, 타입 및 변수명 변경 등의 작은 작업
- [FIX] : 버그 및 오류 해결
- [REFACTOR] : 기존 코드 리펙토링

## Code Convention

<img width="781" alt="view_convention" src="https://github.com/SOPT-33RD-APP-NAVERPAY/NaverPay-Android/assets/52442547/ffad81fa-cf38-4ddc-b436-9eb52d195a28">
<img width="755" alt="xml_drawable_convention" src="https://github.com/SOPT-33RD-APP-NAVERPAY/NaverPay-Android/assets/52442547/78cf47e2-1158-47ab-921e-9bc485d19525">

