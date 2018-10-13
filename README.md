
# Darts Tracker

## Az alkalmazás rövid ismertetése

A Darts Tracker nevű SPA a Nemzetközi Darts Szövetség számára készülő alkalmazás, mely célja a szövetség számára egy olyan webes felület biztosítása, mely segítségével a szövetség könnyedén nyilvántarthatja a jelenleg futó versenyeket, valamint megtekintheti a múltban lefutott versenyeket játékonként, dobás szintű részletességig.

## Szerepkörök az alkalmazásban

Az alkalmazásban 3 különböző felhasználótípust különböztetünk meg. Mindegyik felhasználótípus jogköre hitelesítéshez kötött, vendégként, bejelentkezés nélkül az alkalmazás oldalai nem elérhetőek.

### ADMIN

Ez a jogosultságkör a Darts szövetség vezetősége számára kerül kiosztásra. Az egyszerűség kedvéért csak ADMIN-nak hívjuk, ugyanis ez a felhasználótípus bármilyen módosítást végezhet az alkalmazásban.

### OFFICIAL

Ezt a felhasználótípust a versenybírók kapják. Egy versenybíró lát minden olyan versenyt, melyhez őt osztották be, és ő végzi az eredmények felvitelét, vagyis joga van meccsek kiírásához, illetve az adott meccshez az eredmények felviteléhez.

### PLAYER

A PLAYER jogosultság a játékosok számára kerül kiosztásra. A játékos megtekintheti azon versenyeket, melyeken részt vett, azonban az eredményeket nem módosíthatja.

## Az alkalmazás logikai felépítése

A Darts Tracker alkalmazás felülete elsősorban listás jellegű, ezzel biztosítva a könnyű átláthatóságot és kezelhetőséget.

### Log In - login

A Log in képernyő minden más webes alkalmazáshoz hasonlóan biztosítja a bejelentkezést a játékosok, versenybírók és a szövetség vezetőségének számára. Az alkalmazás használatához mindenképpen be kell jelentkezni, ha a kérés nincs azonosítva, automatikusan a log in képernyőre navigál a kliens.

### Navigáció – menubar

Bejelentkezés után az alkalmazásban állandó jelleggel a felső sávba látható egy menüsáv, mely a navigációt biztosítja. Első körben három menüpont található rajta – versenyek, játékosok, versenybírók, elnökségi tagok és egy kilépés gomb. Ezek a menüpontok jogosultságtól függően érhetőek el az egyes felhasználók számára. Minden más az alkalmazásban megjelenített tartalom a menüsáv alatt jelenik meg.

### Versenyek – competition-list

A versenyek képernyő bejelentkezés után válik elérhetővé a felhasználók számára. Amennyiben az adatbázis nem tartalmaz egyetlen versenyt sem, vagy az adott felhasználónak egyetlen jelenlévő versenyhez sincs megtekintési jogosultsága, úgy egy üres lista képernyő jelenik meg, mely felhívja a user figyelmét arra, hogy jelenleg nincs mit megtekintenie.

A versenyek megjelenítése card jelleggel történik, minden verseny egy-egy kártyán látható. A kártyán megjelenik a verseny neve, országa, első és utolsó napjának a dátuma, valamint a játék típusa. Emellett minden kártyán lehetőség van navigálni a meccsek képernyőre, a versenybírók képernyőre, illetve a játékosok képernyőre. Ezek a menüpontok a lentebb definiált képernyőre navigálnak, és értelemszerűen az adott versenyhez tartozó elemeket szűrik ki az adatbázisból. ADMIN szerepkör esetén lehetőség nyílik egy következő gombbal – megerősítés után a versenyt törölni.

Ha egy adott verseny nevére kattint a felhasználó, akkor megjelennek annak a versenynek a meccsei. Ez a match-list képernyő lesz.

A kártyák fölött jogosultságtól függően megjelenik egy „Verseny kiírása” gomb, mely a versenykiírás képernyőre navigálja a felhasználót.

### Verseny kiírása – competition-create

Ezen a képernyőn lehet létrehozni új versenyeket, egy form segítségével, melyen a következő mezőket kell kiírni

 - Verseny neve
 - Országa
 - Kezdődátuma
 - Befejező dátuma
 - Játék típusa (select listából)
 - Játékosok (legalább 2 megadása szükséges, multiselect listával)
 - Versenybírók (legalább 1 megadása szükséges)

Versenykiírás módosítására nincsen lehetőség, minden esetben törölni kell a versenyt és újat létrehozni, ezzel biztosítva a játékosok és versenybírók állandóságát.

### Meccslista – match-list

A verseny részletei képernyő egy táblázatos listát jelenít meg, ezúttal azonban verseny adatok helyett meccs adatok jelennek meg a képernyőn. Egy meccsről meg kell jeleníteni, hogy ki a két játékos, mi a meccs időpontja, mi a meccs végeredménye, és hogy mennyi ideig tartott a játék. Lehetőség a lista végén található gombbal egy adott meccs részleteit megtekinteni, valamint egy meccset szerkeszteni és törölni is. A képernyőn ezen túl a megfelelő jogosultságokkal rendelkező személyek számára megjelenik egy „új meccs felvitele” gomb, ennek segítségével értelem szerűen új meccset lehet hozzáadni a listához.

### Meccs létrehozás – match-create

Ezen a képernyőn lehet új meccset felvinni. A meccsek létrehozásánál a következő adatokat kell megadni:

 - Egyes játékos (kötelező)
 - Kettes játékos (kötelező)
 - Versenybíró(k) (legalább egy versenybírót kötelezően meg kell adni)
 - Meccs időpontja
 - Hazai pontok
 - Vendég pontok
 - Dobások

A dobások megadása a következőképpen történik: a fent említett adatok beviteli mezői alatt megjelenik egy „Dobások” felület. Ezen a felületen egy gombbal lehet köröket és dobásokat hozzáadni. Minden hozzáadással egy plusz kör kerül hozzáadásra, úgy, hogy mindkét játékoshoz 3-3 dobást kell megadni. Ha az adott játékos mellé dobott az adott körben, vagy a győzelemhez nem kellett neki mind a három nyíl, úgy 0-át kell beírni azokba a mezőkbe, ahol nem szerzett pontot a játékos.

### Meccs szerkesztés – match-edit

Egy meccsen lehetőség van az eredmény módosítására, de kizárólag a végeredményt és a dobásokat lehet módosítani, más esetben törölni kell a meccset és újat létrehozni. Ez a képernyő megegyezik a match-create-tel, itt is minden adat megjelenik, de csak a fent említettek módosíthatóak.

### Meccs részletek – match-detail

A meccs részletek képernyőn egy adott meccs dobásról dobásra lebontva látszik.

### Felhasználó lista – user-list

Ebben a listában az adminisztrátorok mindenkit, a versenybírók a játékosokat láthatják. A játékosok számára nem elérhető ez a menüpont.

### Felhasználó létrehozása – user-create

A felhasználó létrehozása képernyőn új felhasználókat lehet megadni. Jogosultságkörtől függően lehet különböző típusú felhasználókat létrehozni. Az adminisztrátor létrehozhat bármilyen típusú felhasználót, a versenybíró azonban csak játékost adhat hozzá, és a játékos pedig nem adhat hozzá senkit.

Egy felhasználóról a következő adatokat tudjuk:

 - Név
 - Usernév
 - Ország
 - Születési dátum
 - Felhasználó típus

### Felhasználó szerkesztése – user-edit

Egy felhasználónak módosítható a jogosultságköre.

### Felhasználó részletek – user-detail

A user-create képernyőhöz hasonlóan itt is ugyanazok az adatok jelennek meg, de csak olvashatóak, nem módosíthatók.

## Az alkalmazás funkcionális megvalósítása – adatbázisok

Az alkalmazás a következő adatbázisokat tartalmazza, melyek segítségével tárolódnak az adatok

### Users

- Id (number, autoincrement)
- Username (varchar)
- Human name (varchar)
- Country (varchar)
- DateOfBirth(Date)
- Role (number) --> 0: admin, 1: official, 2: player

### Competitions

- Id (number, autoincrement)
- Name (varchar)
- Country (varchar)
- startDate (Date)
- endDate (Date)
- officials (varchar)

### Mathches

- Id (number, autoincrement)
- competitionId (number)
- playerOneId (number)
- playerTwoId (number)
- playerOneScore (number)
- PlayerTwoScore (number)
- OfficialIds (varchar)
- RoundIds (varchar)

### Rounds

- Id (number, autoincrement)
- matchId (number)
- player1_throw_1 (number)
- player1_throw_2 (number)
- player1_throw_3 (number)
- player2_throw_1 (number)
- player2_throw_2 (number)
- player2_throw_3 (number)

## Az alkalmazás funkcionális megvalósítása – API végpontok

### User

**GET**
*/users*
Response:

    [
    	{
    		id: number;
    		username: string;
    		human_name: string;
    		country: varchar;
    		date_of_birth: Date;
    		role: number;
    	}
    	.
    	.
    	.
    ]

**POST**
*/users*
Request:

    {
    	username: string;
    	human_name: string;
    	country: varchar;
    	date_of_birth: Date;
    	role: number;
    }

Response: 

    {
    	id: number;
    	username: string;
    	human_name: string;
    	country: varchar;
    	date_of_birth: Date;
    	role: number;
    }

**DELETE**
*/users/{:id}*
Request:

    {
    }

Response: 

    {
    }

**GET**
*/users/{:id}*

Response:

    {
    	id: number;
    	username: string;
    	human_name: string;
    	country: varchar;
    	date_of_birth: Date;
    	role: number;
    }

**POST**
*/users/{:id}*

Request:

    {
    	role: number;
    }

Response: 

    {
    	id: number;
    	username: string;
    	human_name: string;
    	country: varchar;
    	date_of_birth: Date;
    	role: number;
    }

### Competition
**GET**
*/competitions*

Response:

    [
    	{
    		id: number;
    		name: string;
    		country: string;
    		start_date: Date;
    		end_date: Date;
    		official_ids: string;
    	}
    	.
    	.
    	.
    ]

**POST**
*/competitions*

Request:

    {
    	name: string;
    	country: string;
    	start_date: Date;
    	end_date: Date;
    	official_ids: string;
    }

Response: 

    {
    	id: number;
    	name: string;
    	country: string;
    	start_date: Date;
    	end_date: Date;
    	official_ids: string;
    }

**DELETE**
*/competitions/{:id}*

Request:

    {
    }

Response: 

    {
    }

**GET**
*/competitions/{:id}*

Response:

    {
    	id: number;
    	name: string;
    	country: string;
    	start_date: Date;
    	end_date: Date;
    	official_ids: string;
    }

**POST**
*/competitions/{:id}*

Request:

    {
    	name: string;
    	country: string;
    	start_date: Date;
    	end_date: Date;
    	official_ids: string;
    }

Response: 

    {
    	id: number;
    	name: string;
    	country: string;
    	start_date: Date;
    	end_date: Date;
    	official_ids: string;
    }

### Match
**GET**
*/competitions/{:competition_id}/matches*

Response:

    [
    	{
    		id: number;
    		competition_id: number;
    		match_date: Date;
    		player_one_id: number;
    		player_two_id: number;
    		player_one_score: number;
    		player_two_score: number;
    		official_ids: string;
    		round_ids: string;
    	}
    	.
    	.
    	.
    ]

**POST**
*/competitions/{:competition_id}/matches*

Request:

    {
    	competition_id: number;
    	match_date: Date;
    	player_one_id: number;
    	player_two_id: number;
    	player_one_score: number;
    	player_two_score: number;
    	official_ids: string;
    	round_ids: string;
    }

Response: 

    {
    	id: number;
    	competition_id: number;
    	match_date: Date;
    	player_one_id: number;
    	player_two_id: number;
    	player_one_score: number;
    	player_two_score: number;
    	official_ids: string;
    	round_ids: string;
    }

**DELETE**
*/competitions/{:competition_id}/matches/{:id}*

Request:

    {
    }

Response: 

    {
    }

**GET**
*/competitions/{:competition_id}/matches/{:id}*

Response:

    {
    	id: number;
    	competition_id: number;
    	match_date: Date;
    	player_one_id: number;
    	player_two_id: number;
    	player_one_score: number;
    	player_two_score: number;
    	official_ids: string;
    	round_ids: string;
    }

**POST**
*/competitions/{:competition_id}/matches/{:id}*

Request:

    {
    	match_date: Date;
    	player_one_id: number;
    	player_two_id: number;
    	player_one_score: number;
    	player_two_score: number;
    	official_ids: string;
    	round_ids: string;
    }

Response: 

    {
    	id: number;
    	competition_id: number;
    	match_date: Date;
    	player_one_id: number;
    	player_two_id: number;
    	player_one_score: number;
    	player_two_score: number;
    	official_ids: string;
    	round_ids: string;
    }

### Round
**GET**
*/competitions/{:competition_id}/matches/{:match_id}/rounds*

Response:

    [
    	{
    		id: number;
    		competition_id: number;
    		match_id: number;
    		player_one_throw_one: number;
    		player_one_throw_two: number;
    		player_one_throw_three: number;
    		player_two_throw_one: number;
    		player_two_throw_two: number;
    		player_two_throw_three: number;
    	}
    	.
    	.
    	.
    ]

**POST**
*/competitions/{:competition_id}/matches/{:match_id}/rounds*

Request:

    {
    	competition_id: number;
    	match_id: number;
    	player_one_throw_one: number;
    	player_one_throw_two: number;
    	player_one_throw_three: number;
    	player_two_throw_one: number;
    	player_two_throw_two: number;
    	player_two_throw_three: number;
    }

Response: 

    {
    	id: number;
    	competition_id: number;
    	match_id: number;
    	player_one_throw_one: number;
    	player_one_throw_two: number;
    	player_one_throw_three: number;
    	player_two_throw_one: number;
    	player_two_throw_two: number;
    	player_two_throw_three: number;
    }

**DELETE**
*/competitions/{:competition_id}/matches/{:match_id}/rounds/{:id}*

Request:

    {
    }

Response: 

    {
    }

**GET**
*/competitions/{:competition_id}/matches/{:match_id}/rounds/{:id}*

Response:

    {
    	id: number;
    	competition_id: number;
    	match_id: number;
    	player_one_throw_one: number;
    	player_one_throw_two: number;
    	player_one_throw_three: number;
    	player_two_throw_one: number;
    	player_two_throw_two: number;
    	player_two_throw_three: number;
    }

**POST**
*/competitions/{:competition_id}/matches/{:match_id}/rounds/{:id}*

Request:

    {
    	competition_id: number;
    	match_id: number;
    	player_one_throw_one: number;
    	player_one_throw_two: number;
    	player_one_throw_three: number;
    	player_two_throw_one: number;
    	player_two_throw_two: number;
    	player_two_throw_three: number;
    }

Response: 

    {
    	id: number;
    	competition_id: number;
    	match_id: number;
    	player_one_throw_one: number;
    	player_one_throw_two: number;
    	player_one_throw_three: number;
    	player_two_throw_one: number;
    	player_two_throw_two: number;
    	player_two_throw_three: number;
    }
