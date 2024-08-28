[![Discord](https://img.shields.io/discord/670667590812696623?color=fff&label=Discord&logo=discord&logoColor=fff)](https://discord.gg/verany)
![Twitter Follow](https://img.shields.io/twitter/follow/VeranyNET?color=919191&label=Folge%20%40VeranyNET&style=social)
![YouTube Channel Subscribers](https://img.shields.io/youtube/channel/subscribers/UCduDElXYi8zPjZMTIxCT45A?label=Abonniere%20VeranyNET&style=social)

Verany Core Dokumentation
=============

Willkommen zur Dokumentation der Core. Hier findet ihr Anleitungen zur Installation, Konfiguration und Weiterentwicklung. Zusätzlich dazu findet ihr hier eine Dokumentation über die Bestandteile des Projektes, darunter zählen zum Beispiel die Branches- und ihre Aufgaben die (wenn vorhanden) Module und weitere wichtige Hinweise, die noch vor dem eigentlichen Entwickeln wichtig für euch sein können.

> Achtung: Einige Inhalte dieser Datei sind noch nicht final umgesetzt und werden erst demnächst hinzugefügt. Sollte etwas nicht richtig funktionieren wende dich bitte an eine Development-Team Leitung. Beispielsweise sollen die Verbindungsdaten und Informationen im VeranyModule in eine JSON auf dem Server ausgelagert werden.

## Verwendung der Core in externen Projekten

Füge das zu deiner pom.xml hinzu:
```java
<repository>
    <id>verany-core-releases</id>
    <name>verany nexus</name>
    <url>https://nexus.verany.net/content/repositories/releases/</url>
</repository>
```

Und füg dann die Dependency hinzu:
```java
<dependency>
    <groupId>net.verany.core</groupId>
    <artifactId>core-api</artifactId>
    <version>1.17.1-R0.1-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

## Installation zur Weiterentwicklung / Wartung:
Die Installation der Core erfolgt über das Klonen in GitHub. Wir empfehlen dir für deine Projekte einen WorkSpace Ordner zu erstellen, in welchem du dann einen GitHub Ordner hast, in welchem widerrum nach Usern oder in unserem Fall Organisationen spezifiziert wird. In diesem Beispiel sieht das dann so aus:

```
D:\Dokumente\WorkSpace\GitHub\venasty-network\core
```

### Das Projekt mit CLI klonen
```ssh
gh repo clone venasty-network/core
```

## Beispiel zur Verwendung

```java
package net.verany.api.example;

import net.verany.api.Verany;
import net.verany.api.example.commands.ExampleCommand;
import net.verany.api.example.listener.PlayerJoinListener;
import net.verany.api.example.listener.PlayerQuitListener;
import net.verany.api.module.VeranyModule;
import net.verany.api.module.VeranyProject;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

@VeranyModule(name = "Example", prefix = "Example", version = "2021.7.1", authors = {"Notch"})
public class ExamplePlugin extends VeranyProject {

    public static ExamplePlugin INSTANCE;

    public ExamplePlugin() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        Verany.loadModule(this, this::init);
    }

    @Override
    public void init() {
        initCommands();
        initListener();
    }

    private void initCommands() {
        new ExampleCommand(this);
    }

    private void initListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
    }
    
    @Override
    public void onDisable() {
        Verany.shutdown(this);
    }
}
```

### Weitere Beispiele
findest du unter [Core Beispiele](https://github.com/venasty-network/core/discussions/12).

---
## Branches:
* `production`: Produktionsbuild (Wird automatisch auf die entsprechenden Dienste deployed)
* `release/*`: Release Builds (z.B. `release/2021.7.4`, eine stabil laufende Version benannt nach dem Zeitpunkt)
* `feature/*`: Feature Update Branch (z.B. `feature/prefix-update`, für große Updates)
* `dev/*`: Developer spezifischer Branch (z.B. `dev/nicokempe`, für kleine Änderungen und nur temporär ausgelegt)

---
## Lizensierung:
Niemand außer der Administration selbst und dem Entwicklerteam von Verany steht es zu den geschriebenen Code ohne Erlaubnis zu verwenden. Bei eigenem Einsatz in das Projekt gibt man automatisch alle Rechte an der aufgewendeten Arbeit ab.
