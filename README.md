# evPlan
Eine beispielhafte Java Konsolenanwendung einer simplen Vereinsverwaltung, um mit den Schülerinnen und Schülern Blackbox, Whitebox, Unit und Integrationstests zu üben.

![screenshot](doc/Screenshot.png)

Im Verzeichnis **doc** befinden sich zahlreiche Dokumente für die Unterrichtsgestaltung. hier zu finden ist auch das [Lastenheft](doc/Lastenheft_evplan.pdf).

##
Das Starten der Anwendung kann über das über eine CI/CD Pipeline erzeugte jar File erfolgen. Im gleichen Verzeichnis muss sich auch die sqlite Datenbank **evplan.db** befinden!
```
java -jar evplan-1.0-SNAPSHOT-jar-with-dependencies.jar
```