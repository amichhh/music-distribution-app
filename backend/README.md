## music-distribution-app

Spring Bootで作成した楽曲配信アプリのバックエンド（API）です。

### ドメイン

|ドメイン名|物理名|
|--|--|
|楽曲|music|
|アーティスト|artist|
|売上|sales|
|アカウント|account|
|セキュリティ|security|

### ユースケース

共通

|ドメイン|機能名|物理名|URL|HTTPメソッド|
|--|--|--|--|--|
|security|ログインする|login|/login|POST|✅
|security|ログアウトする|logout|/logout|GET|✅

サイト訪問者/会員

|ドメイン|機能名|物理名|URL|HTTPメソッド|
|--|--|--|--|--|
|account|会員(メンバーアカウント)登録する|signUp|/api/account/signup|POST|✅
|account|自分のアカウントを編集する|editMemberAccount|/api/member/account/edit|POST|
|music|タイトルで楽曲を検索する|findMusicByTitle|/api/music/title|GET|✅
|music|アーティストで楽曲を検索する|findMusicByArtist|/api/music/artist|GET|✅
|music|人気の楽曲を検索する|findMusicByPopularity|/api/music/popular|GET|✅
|music|最新の楽曲を検索する|findMusicByNewest|/api/music/new|GET|✅
|music|楽曲を試聴する|listenMusicTrial|/api/music/listen/trial|GET|
|music|楽曲をダウンロードする|downloadMusic|/api/member/music/download|GET|
|music|ダウンロードした楽曲を聴く|listenMusic|/api/music/listen|GET|
|music|プレイリストを検索する|findPlaylist|/api/member/music/playlist|GET|✅
|music|プレイリストを作成する|registerPlaylist|/api/member/music/playlist|POST|✅
|music|プレイリストを編集する|editPlaylist|/api/member/music/playlist/edit|POST|
|artist|アーティストを検索する|findArtist|/api/artist|GET|✅
|artist|アーティストをお気に入り登録する|likeArtist|/api/member/artist/favorite|POST|✅
|artist|アーティストのお気に入り登録を解除する|likeArtist|/api/member/artist/favorite|POST|✅
|artist|お気に入り登録したアーティストを検索する|findFavoriteArtist|/api/member/artist/favorite|GET|✅
|sales|楽曲を購入する|purchaseMusic|/api/member/sales/purchase|POST|✅
|sales|自分の購入履歴を確認する|findPurchaseHistory|/api/member/sales/history|GET|✅

事業者

|ドメイン|機能名|物理名|URL|HTTPメソッド|
|--|--|--|--|--|
|music|楽曲を納品する|deliverMusic|/api/business/music|POST|✅
|music|楽曲を編集する|editMusic|/api/business/music/edit|POST|
|artist|アーティストを登録する|registerArtist|/api/business/artist|POST|✅
|artist|アーティストを編集する|editArtist|/api/business/artist/edit|POST|
|sales|日付毎の売上記録を検索する|findSalesRecordPerDay|/api/business/sales/day|GET|✅

運営者

|ドメイン|機能名|物理名|URL|HTTPメソッド|
|--|--|--|--|--|
|account|メンバーアカウントを検索する|findMemberAccount|/api/staff/account/member|GET|✅
|account|事業者アカウントを検索する|findBusinessAccount|/api/staff/account/business|GET|✅
|account|運営者アカウントを検索する|findStaffAccount|/api/staff/account/staff|GET|✅
|account|事業者アカウント登録する|registerBusinessAccount|/api/staff/account/business|POST|✅
|account|アカウントを有効にする|validateAccountStatus|/api/staff/account/valid|POST|
|account|アカウントを無効にする|invalidateAccountStatus|/api/staff/account/invalid|POST|

管理者

|ドメイン|機能名|物理名|URL|HTTPメソッド|
|--|--|--|--|--|
|account|運営者アカウント登録する|registerStaffAccount|/api/admin/account/staff|POST|
|account|アカウント権限を変更する|changeAccountAuthority|/api/admin/account/authority|POST|

### テーブル

music

|項目名|物理名|型|文字数|必須|PK|FK|
|--|--|--|--|--|--|--|
|楽曲ID|ID|bigint|-|○|○|-|
|タイトル|TITLE|varchar|100|○|-|-|
|アーティストID|ARTIST_ID|bigint|-|○|-|○|
|企業ID|COMPANY_ID|varchar|40|○|-|○|
|カバー画像URL|ART_URL|varchar|400|○|-|-|
|音源URL|SOUND_URL|varchar|400|○|-|-|
|価格|PRICE|int|-|○|-|-|
|発売日|RELEASE_DAY|Date|-|○|-|-|
|購入回数|PURCHASE_COUNT|int|-|○|-|

playlist

|項目名|物理名|型|文字数|必須|PK|FK|
|--|--|--|--|--|--|--|
|プレイリストID|ID|bigint|-|○|○|-|
|タイトル|TITLE|varchar|100|○|-|-|
|作成者ID|ACCOUNT_ID|varchar|30|○|-|-|

playlist_item

|項目名|物理名|型|文字数|必須|PK|FK|
|--|--|--|--|--|--|--|
|プレイリストアイテムID|ID|bigint|-|○|○|-|
|プレイリストID|PLAYLIST_ID|bigint|-|○|-|○|
|楽曲ID|MUSIC_ID|bigint|-|○|-|○|
|ソート順|SORT_ORDER|int|-|○|-|-|

artist

|項目名|物理名|型|文字数|必須|PK|FK|
|--|--|--|--|--|--|--|
|アーティストID|ID|bigint|-|○|○|-|
|アーティスト名|NAME|varchar|100|○|-|-|
|説明|OUTLINE|varchar|400|○|-|-|

artist_favorite

|項目名|物理名|型|文字数|必須|PK|FK|
|--|--|--|--|--|--|--|
|お気に入りID|ID|bigint|-|○|○|-|
|アカウントID|ACCOUNT_ID|varchar|30|○|-|○|
|アーティストID|ARTIST_ID|bigint|-|○|-|○|

sales_record

|項目名|物理名|型|文字数|必須|PK|FK|
|--|--|--|--|--|--|--|
|購入履歴ID|ID|bigint|-|○|○|-|
|購入者ID|ACCOUNT_ID|varchar|30|○|-|○|
|楽曲ID|MUSIC_ID|bigint|-|○|-|○|
|購入金額|PURCHASE_AMOUNT|int|-|○|-|-|
|購入日時|PURCHASE_DATE|Date|-|○|-|-|
|支払方法|PAYMENT_METHOD|varchar|30|○|-|-|

company

|項目名|物理名|型|文字数|必須|PK|FK|
|--|--|--|--|--|--|--|
|企業ID|ID|varchar|40|○|○|-|
|企業名|NAME|varchar|50|○|-|-|
|備考|DESCRIPTION|varchar|400|-|-|-|

account

|項目名|物理名|型|文字数|必須|PK|FK|
|--|--|--|--|--|--|--|
|アカウントID|ACCOUNT_ID|varchar|30|○|○|-|
|ユーザー名|NAME|varchar|30|○|-|-|
|パスワード|PASSWORD|varchar|64|○|-|-|
|権限|AUTHORITY|varchar|10|○|-|-|
|アカウント状態|ACCOUNT_STATUS|varchar|10|○|-|-|

balance

|項目名|物理名|型|文字数|必須|PK|FK|
|--|--|--|--|--|--|--|
|アカウントID|ACCOUNT_ID|varchar|30|○|○|○|
|残高|BALANCE|int|-|○|-|-|

belong

|項目名|物理名|型|文字数|必須|PK|FK|
|--|--|--|--|--|--|--|
|アカウントID|ACCOUNT_ID|varchar|30|○|○|-|
|企業ID|COMPANY_ID|string|30|○|-|○|

### ディレクトリ構成
```
src
 ├──main
 │   ├──java
 │   │   └──music
 │   │       ├──application
 │   │       │   ├──dto
 │   │       │   └──usecase
 │   │       ├──domain
 │   │       │   └──model
 │   │       ├──infrastructure
 │   │       │   └──repository
 │   │       ├──presentation
 │   │       │   ├──controller 
 │   │       │   └──form
 │   │       ├──Application.java
 │   │       └──SecurityConfig.java
 │   │     
 │   └──resources
 │       ├──application.yml
 │       └──messages.properties
 │ 
 └──test
```