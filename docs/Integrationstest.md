# Integrationstest EV PLan
Die Komponente, die Datenbankeinträge in der Datenbank durchführt ist die Klasse Gruppencontroller.java im Paket database. Diese Klasse erhält über die Klasse DBManager, welches als Singelton realisiert wurde, die Ankopplung an die SQLite Datenbank.

CRUD Operationen können dann über die folgenden Methoden realisiert werden:
- getGruppen():ArrayList\<Gruppe> ließt alle Gruppen aus der Datenbank.
- getGruppen(m:Mitglied):ArrayList\<Gruppe> ließt die Gruppen des jeweiligen Mitglieds
- getGruppe(id:int):Gruppe ließt die Gruppe der jeweiligen ID.
- deleteGruppe(id:int):void löscht die jeweilige Gruppe
- addGruppe(g:Gruppe):void legt eine Gruppe an
- setGruppe(g:Gruppe):void ändert eine Gruppe
- addMember(m:Mitglied),g:Gruppe):void fügt ein Mitglied einer Gruppe hinzu.

Diese im Klassendiagramm dargestellten Komponenten sollen im weiteren einem Integrationstest unterworfen werden.

![Gruppencontroller.jsvs](./images/Klassendiagramm_IntegrationsTest.png)