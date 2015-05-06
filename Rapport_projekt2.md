Projektarbete 2: Interaktiv prototyp

Jonatan Hilmarch (Grupp 13)

hilmarch AT skip.chalmers.se

Kurs: Människa-Datorinteraktion TIG061 HT 2010


Projekt 1 - en tillbakablick

Enligt projektets systemdefinition skall skall en svensk student kunna använda sig av budgeteringsverktyget för att bättre kunna styra sin ekonomi. Detta skall ske genom ökad planering, kontroll och uppföljning. Vi ansåg också att det var viktigt med ett system fungerande för flera plattformar -på persondatorer och de vanligaste typerna av smartphones.

Av funktionslistan i projekt 1 är de flesta punkter nästan helt implementerade. Det som lagts mindre utrymme för är historik, möjlighet att se lista av köp och upprättande av olika typer av diagram. Den interaktiva prototypen stödjer alltså mest planering, lite mindre kontroll och uppföljning.

Prototypen tillåter att användaren sparar och laddar uppgifter till disk i xml-format utan fel. Detta gör att många säkerligen redan idag skulle kunna få användning av verktyget i sitt vardagliga liv.

Vad fungerar bra med prototypen?

Prototypen är stabil och all funktionalitet går hanteras via huvudfönstrets meny och de efterföljande dialogrutor som visas. Stor vikt har lagts på att användaren inte skall kunna göra fel eller att applikationen skall hänga sig. Till att börja med är de menyval som aktiverar icke aktuella funktioner otillgängliga genom att de skuggas. I dialogrutorna styrs användaren till att göra rätt genom att data väljs i rullgardinslistor. Viktiga felkontroller av formulärdata är också implementerade. Slutligen kan användaren gå tillbaka till näst senaste modell genom att välja "ångra" i menyn.

Inga användartester eller liknande har gjorts men som utvecklade är jag nöjd med hur budgeten presenteras i två skrollbara tabeller. Det känns igen från det sätt datan presenteras i kalkylprogram och det är överblickligt.

Förbättringsförslag

Under projektets gång så har jag listat funktioner som bör implementeras och vägt dem mot varandra för att avgöra vad som har lägst respektive högst prioritet. Under projektets gång har det dock dykt upp funktioner som tas upp här och nu men som inte tagits upp vid projektets start. Nedan blandas planerade funktioner som inte implementerats med andra tankar för en förbättrad prototyp som dykt upp:

En given och planerad funktion är att kunna spara information via nätverket till en server. För detta behövs egen programvara för servern och kunskaper i trådning. Jag känner mig inte så säker på trådning och har därför valt att inte lägga tid på det i den interaktiva prototypen.

Att kunna ändra namnet på en period är också en självklarhet men något som lågprioriterats.

Hela sättet på hur händelser hanteras i applikationen kan komma att ändras i framtida versioner. Det skulle t.ex. kunna vara bra att lista händelser i en egen vy. Då skulle man kunna hantera dessa på liknande sätt som budgetposterna genom att t.e.x möjliggöra borttagning av flera händelser samtidigt. Dessutom blir kassaboken överblicklig.

Det skulle vara bra att kunna använda den data som verktyget genererar för export till t.ex. csv-format för att kunna användas i kalkylprogram eller andra system med annan funktionalitet.

I nuläget sparas data till fil på disken. På sikt är det bättre att en databas används för detta.

En bra ångra-funktion stödjer också "gör om/redo" och möjlighet att ångra sig flera steg bakåt. Prototypen är begränsad när det gäller möjligheten att ångra sig. Man kan bara ångra sig ett steg bakåt och man kan inte ångra att man tryckte på ångra.

Om verktyget skulle kunna hanteras endast med tangentbordet skulle det vara bra. Detta stödjer användarmönstret "keyboard only".

Ett sätt att göra det tydligare vad som händer när användaren interagerar med applikationen är att använda sig av ikoner jämsides med text för menyval och funktionalitet. Detta tycker jag dock är detaljer som kan implementeras i senare iterationer.

Projekt 1 visade bl.a. på att datan bör visualiseras i grafer och diagram och inte bara i siffror. Detta har inte prioriterats vid implementeringen av den interaktiva prototypen.

Mallar för att hämta delar av en budget är ett sätt att snabbt bygga ihop en ny period om den liknar tidigare perioder. Detta stödjer användarmönstret "deferred choices".

Användarmönster

De användarmönster som varit i fokus från Tidwells bok är "safe exploration", "streamlined repetition" och "changes in midstream". Prototypen stödjer "saft exploration" eftersom prototypen frågar vid lämpliga tillfällen om användaren vill spara, för att felkontroller vid formulär görs och för att inaktuella menyalternativ skuggas. Vid viktiga lägen frågar applikationen också ibland om användare verkligen vill utföra operationen. "Streamlined repetition" stöds för att man kan markera flera budgetposter samtidigt för redigering och radering. "Changes in midstream" stöds för att applikationen inte hänger sig om användaren avbryter en dialogruta och för att man kan spara den aktuella profilen.

Hur Jonatan Hilmarch's budgeteringsverktyg är tänkt att användas

Användaren skall först skapa eller öppna en profil. Detta görs genom menyvalet "Profil". Om inte några perioder redan finns i profilen måste användaren skapa en period genom menyvalet "Period". Om användaren senare vill ändra aktuell period väljs "Period-->Öppna". För att kunna hantera kassaboken och t.ex. registrera nya händelser måste det finnas budgetposter som skapas genom menyvalet "Budget-->Lägg till budgetpost". En händelse tillhör nämligen en budgetpost där en budgetpost kan ha noll eller flera händelser. All funktionalitet som kräver att användaren matar in data sker med hjälp av dialogrutor. När händelser registreras kan det samlade utfallet ses bredvid den aktuella budgetposten i någon av de två tabellerna i huvudfönstret. Vissa beräkningar görs också för visning på skärmen så att användaren slipper att räkna själv. Om användaren har svårt att förstå hur verktyget skall användas går det att klicka på menyvalet "Hjälp" för att antingen gå till projektets hemsida eller öppna ett pdf-dokument med en manual. För att bättre kunna utvärdera sina perioder finns möjlighet att skriva en rapport till disk.