#Bakcend dokumentáció

### Fejlesztői környezet bemutatása, beállítása, használt technológiák

A Backend megvalósításához Spring Bootot használunk. Ez a keretrendszer nagyban megkönnyíti egy REST API készítését, ugyanis a benne található eszközökkel egyszerűen lehet adatbázisokba adatot feltölteni, lekérni, módosítani, vagy törölni. A kiinduló projekt létrehozásához a Spring Boot initializert használjuk, ennek segítségével már kezdéskor kiválaszthatjuk a szükséges dependenciákat, ezzel időt spórolva.

A projektet ezt követően az IntelliJ IDEA segítségvel fejlesztettük, mely az egyik legelterjedtebb fejlesztőeszköz manapság. A kezdőprojekt beimportálását követően elkezdhetjük a fejlesztést. A server futtatásához hozzá kell adni egy build konfigurációt, mellyel ezután bármikor újrafordíthatjuk és futtathatjuk a projektet.

Az adatbázis megvalósításához a h2 adatbázismotort használjuk, és egy .sql fájl segítségével kezdéskor feltöltjük az adatbázist különböző adatokkal, hogy könnyebben lehessen később tesztelni.

### Adatbázis terv

[Adatbázis terv vizuálisan](https://github.com/barnipro/dartstracker/database.png)

### Alkalmazott könyvtárstruktúra

- Entities:
Itt találhatóak az alkalmazásban használt entitások, melyekben az entitás tulajdonságai vannak leírva, valamint a kapcsolatok is itt vannak leírva más entitásokkal

- Controllers:
Itt találhatók az egyes entitások controllerei. Ezek az osztályok felelnek a kérések feldolgozásaiért, valamint a válasz visszaküldéséért. 

- Repositories:
Ebben a mappában találhatók azok osztályok, melyek a CRUD műveleteket végzik, entitásonként külön-külön.

- Security: Az autentikációért felelős egyéb osztályok találhatóak itt. 

A részletes végponttervek a [readme.md](https://github.com/barnipro/dartstracker/readme.md) file-ban találhatók.