# Laboratoire Itune clones
## Objectif principal

L'objectif principal de ce projet de cours est de proposer un système de base de données complet, incluant une architecture trois tiers (interface utilisateur, serveur, base de données). Ce système de base de données multimédia sera réalisé au travers des trois (3) laboratoires du cours (36 heures au total). Les spécifications des exigences portent sur l'implantation de ce système de base de données multimédias, qui permettra à l'utilisateur d'interroger, de modifier et d'interagir avec une base de données multimédias. Ce projet sera réalisé, selon trois livrables, un par laboratoire. Le premier livrable permettra d'effectuer la création de la base de données, de se familiariser avec les concepts des bases de données et d'importer des données existantes. Le deuxième livrable consiste à concevoir et développer un logiciel d'interrogation de base de données multimédia encapsulant les fonctionnalités d'interrogation. Le troisième livrable permettra d'introduire des concepts de recherche par contenu et de synchronisation de contenu multimédia.

## Spécifications des requis

Les spécifications des requis contiennent les directives des laboratoires 1, 2 et 3.

-  Le document SRS est disponible: [SRS version 1.0](doc/exigences/SRS.pdf)
- Le document de spécifications des requêtes: [requetes.pdf](doc/exigences/requetes.pdf)
- La description et le contenu de la base de données en XML : [client](data/clients_utf8.xml), [films](data/films_utf8.xml) et [personnes](data/personnes_utf8.xml)
- Présentations des directives pour les laboratoires: [lab1.pdf](doc/exigences/lab1.pdf), [lab2.pdf](doc/exigences/lab2.pdf) et  [lab3.pdf](doc/exigences/lab3.pdf)
- Barèmes d'évaluation pour les laboratoires: [eval_lab1.pdf](doc/exigences/eval_lab1.pdf), [eval_lab2.pdf](doc/exigences/eval_lab2.pdf) et [eval_lab3.pdf](doc/exigences/eval_lab3.pdf)

## Code source fourni

- Voici le code source fourni (incluant JDBC): [src](src)
- Code pour l'insertion XML: [LectureBC.java](src/lecture/LectureBD.java)
- Code pour la lecture du vidéo: [Videoplayer.zip](https://cours.etsmtl.ca/gti660/private/labos/videoplayer.zip)

## Gestionnaire de version GIT (obligatoire)

- Nous utilisons Github Classroom pour la durée du projet. Les remises des livrables seront effectuées directement sur le répertoire git du projet dans le répertoire rapports.
- Pour chaque remise vous devrez marquer celle-ci à l'aide d'un tag. git tag lab1, git tag lab2 et git tag lab3
- Tutoriel sur l'utilisation de GIT: [intro_git.pdf](https://cours.etsmtl.ca/gti660/private/labos/intro_git.pdf)

## Outils de travail
Je vous suggère fortement d'utiliser cette configuration pour la réalisation de ce projet:
- Utiliser l'IDE [Visual Studio Code](https://code.visualstudio.com/docs/languages/java) pour écrire vos tests, votre code et votre documentation.
- Réaliser vos rapports avec le format markdown en utilisant le plugin [Markdown all in One](https://marketplace.visualstudio.com/items?itemName=yzhang.markdown-all-in-one)
- Générer vos rapports PDF en utilisant le plugin [Markdown PDF](https://marketplace.visualstudio.com/items?itemName=yzane.markdown-pdf)
- Réaliser vos diagrammes à l'aide du plugin [PlantUml](https://marketplace.visualstudio.com/items?itemName=jebbs.plantuml) en utilisant cette [documentation](https://plantuml.com/fr/).
- Voir un [exemple](doc/rapports/exemple/exemple.md)

# Test Driven Design

Pour exécuter tout les tests
```bash
mvn test 
```

Pour obtenir le rapport de couverture de test
```bash
mvn -P jacoco test
```
Tout les tests doivent passer pour pouvoir générer le rapport de couverture de test.
[Ouvrir le rapport jacoco dans votre browser](./target/jacoco/site/../../site/jacoco/index.html)


## Maven file Watch
```bash
npm install grunt
grunt mvnwatch:<FOLDER_TO_WATCH>
grunt mvnwatch:.
```

## Generation des diagramme de classes
```bash
java -jar plantuml-dependency-cli-1.4.0-jar-with-dependencies.jar -o plantuml.puml -b . -i **/*.java -e **/*Test*.jva -dn .*Test.* -v


java -jar plantuml-dependency-cli-1.4.0-jar-with-dependencies.jar -o plantuml-framework-graphed2.puml -b . -i ./src/main/java/Framework/**/*.java -e **/*Test*.jva -dn .*Test.* -v
```

## Spécification supplémentaire
- Vous devez impérativement utiliser une approche orienté objets.

- Architecture en couche
  - Architecture MVC
  - Les router ne doivent pas créer d'objets du modèle de données
  - Les classes Controleurs doivent utiliser des paramètres de type primitif (aucun objet). Les retours d'information sont aussi de type primitif. 
  - todo: lien vers video architecture en couche
  - Groupe de 4 à 5 personnes
  - Répartition: 4 semaines par laboratoire.
  

  ## [Impressionnez nous](doc/exigences/impressionnnez-nous.md)
  Point bonus possible...