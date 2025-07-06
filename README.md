# Projet Médiathèque - Gestion de documents

## Description

Ce projet de médiathèque est une application Java développée selon une architecture MVC légère.  
Elle permet de gérer différents types de documents (livres, cassettes, périodiques, etc.) avec une interface graphique Swing intuitive.

---

## Architecture et Organisation du Code

Le projet est structuré en trois packages principaux :

- **om (objets métier)**  
  Contient les classes représentant les entités du domaine.  
  La classe abstraite `Document` est la superclasse des types spécifiques (`Livre`, `Cassette`, `Périodique`, etc.).

- **dao (accès aux données)**  
  Regroupe les DAO spécialisés (`LivreDAO`, `CassetteDAO`, etc.) qui interagissent avec la base MySQL via JDBC.  
  Les insertions respectent la hiérarchie d’héritage grâce à une stratégie en deux étapes.

- **iu (interface utilisateur)**  
  Implémente l’interface graphique avec Swing.  
  Le menu principal (`BibliothequeGUI`) permet l’ajout, la recherche et la consultation des documents.

---

## Base de données

- Base MySQL organisée selon un modèle relationnel avec héritage :  
  - Table `document` contenant les attributs communs.  
  - Tables spécifiques liées par clé étrangère pour chaque type de document.  
- Connexion centralisée dans la classe `ConnexionDB`.  
- Utilisation de `PreparedStatement` pour sécuriser les requêtes SQL.  
- Scripts SQL réalisés avec MySQL Workbench.

---

## Fonctionnalités principales

- Ajout, recherche et consultation de documents.  
- Recherche optimisée en mémoire avec mots-clés via une Hashtable.  
- Gestion des erreurs robuste grâce à des exceptions personnalisées (`BiblioException`, `DocumentPasTrouve`).  
- Interface utilisateur simple et fluide avec Swing.

---

## Environnement et outils

| Domaine            | Technologies       |
|--------------------|--------------------|
| 🖥️ Langage         | Java               |
| 🛠️ IDE             | MyEclipse IDE      |
| 🎨 Interface GUI   | Java Swing         |
| 🔗 Accès aux données | JDBC              |
| 🗄️ Base de données  | MySQL              |

---

## Lancement de l’application

1. Assurez-vous que la base MySQL est en service avec la base et tables créées.  
2. Configurez la connexion dans la classe `ConnexionDB` (URL, utilisateur, mot de passe).  
3. Compilez et lancez l’application via MyEclipse IDE ou en ligne de commande.  
4. L’interface Swing s’ouvre avec le menu principal pour gérer la médiathèque.

---

## Remarques

- Le projet respecte une séparation claire des responsabilités selon le modèle MVC.  
- L’architecture orientée objet permet une bonne extensibilité pour ajouter d’autres types de documents.


