# fate-create
A desktop program meant to be used offline to create a Character sheet for the Table Top Role Playing Game Fate Core. 

## What is Fate Create?

Fate Create is a character sheet and character sheet builder for the Fate Core Table Top Role Playing Game. It was designed with the intention of creating an offline and user friendly program that allows the player to easily create their own charactes.

## State of the Project

At present the rough visual layout and design has been created and is currently runnable as an executable jar file for windows. There has also been significant effort put into the refactor of the code to be more maintainable. Though a character can in fact be created, there is no way, at present, to save or load these character sheets, though the foundations for this functionality is presently being put into the code during the refactor process.

IMPORTANT

At present Fate Create is only supported on Windows though there are plans to make it supported on both MAC and Linux distros in the future.

## Tools Used in Fate Create

+ Java 21
+ JavaFX version 21
+ Maven

## Installation

First make sure that you have both java 21 or later and Maven installed on your computer. Once that has been accomplished fork the project and download the repository via git.

```git clone <your_repository_url>```

From there you can easily begin to work on the files in 'src/main/java/creator/fate/create'

To run the prototype simply navigate to 'proto/target' and double click on the fate-create-0.1-PROTOTYPE file.

To compile the current source code make sure that you run the following command in the root directory for Fate Create

```mvn clean javafx:run```

This will compile and run the code of the current project.
