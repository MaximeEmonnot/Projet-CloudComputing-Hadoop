# Projet-CloudComputing-Hadoop
Projet dans le cadre du Master 1 BDMA à Blois.  
Réalisé par Manon LACOMBE, Axel BRUNEL et Maxime EMONNOT.

## Table des matières

- [Présentation](#présentation)
- [Structure du dépôt](#structure-du-dépôt)
- [Conventions](#conventions)
    * [Casse des variables](#casse-des-variables)
    * [Langue des fichiers](#langue-des-fichiers)
    * [Alignements](#alignements)
    * [Commentaires](#commentaires)
- [Commandes Hadoop](#commandes-hadoop)

## Présentation

Ce projet consiste à apporter une solution permettant de traiter convenablement un problème de base de données, étant donné certaines contraintes de volume, de matériel, et de demandes spécifiques. Ces contraintes constituent une certaine problématique, et il est donc nécessaire d’apporter la solution la plus adéquate pour la résoudre.

Au cours de ce projet, nous devions définir l'architecture adoptée, que ce soit au niveau de la technologie de stockage et de la structure des fichiers de données. Nous avons ainsi choisi d'utiliser **HDFS**, avec des fichiers dont les lignes suivent cette structure : 
- Ligne étudiant : 
    * E;Num étudiant;Année;Promotion;Nom;Prénom;Date de naissance;Email;Numéro de téléphone;Adresse
- Ligne UE : 
    * U;Code UE;Année;Nom UE;Enseignant1,Enseignant2,Enseignant3,…
- Ligne Note : 
    * N;Code UE;Année;Num étudiant;Note

Nous devions également choisir 3 requêtes à traiter parmi les 5 suivantes : 
1. Sortir le relevé de notes selon un numéro d’étudiant et une année scolaire
2. Sortir le taux de réussite de notes d’un semestre selon l’année scolaire
3. Sortir le taux de réussite d’une UE depuis sa création par rapport à ses différents noms
4. Sortir les taux de réussite de toutes les UE assurées par un intervenant
5. Sortir le classement d’étudiants par rapport à leurs notes moyennes selon la promotion et l’année scolaire

Nous avons donc choisi de traiter les requêtes 2, 3 et 4.

Ce README présentera l'ensemble du dépôt, à savoir : 
- La structure du dépôt
- Les conventions de code pour notre organisation
- Les commandes sur le cluster Hadoop pour l'exécution des jobs 

## Structure du dépôt
Le dépôt suit la structure suivante : 
```
🗂️ Projet-CloudComputing-Hadoop
|
+-- 📂 Données
|   +-- 📜 etudiants.txt
|   +-- 📜 notes.txt
|   +-- 📜 ue.txt
|
+-- 📂 Sources
|   +-- 📂 TaskA
|   |   +-- ☕ TaskATempMain.java
|   |   +-- ☕ TaskATempDriver.java
|   |   +-- ☕ TaskATempMapper.java
|   |   +-- ☕ TaskATempReducer.java
|   |   +-- ☕ TaskAFinalMain.java
|   |   +-- ☕ TaskAFinalDriver.java
|   |   +-- ☕ TaskAFinalMapper.java
|   |   +-- ☕ TaskAFinalReducer.java
|   |
|   +-- 📂 TaskB
|   |   +-- ☕ TaskBTempMain.java
|   |   +-- ☕ TaskBTempDriver.java
|   |   +-- ☕ TaskBTempMapper.java
|   |   +-- ☕ TaskBTempReducer.java
|   |   +-- ☕ TaskBFinalMain.java
|   |   +-- ☕ TaskBFinalDriver.java
|   |   +-- ☕ TaskBFinalMapper.java
|   |   +-- ☕ TaskBFinalReducer.java
|   |
|   +-- 📂 TaskC
|       +-- ☕ TaskCTempMain.java
|       +-- ☕ TaskCTempDriver.java
|       +-- ☕ TaskCTempMapper.java
|       +-- ☕ TaskCTempReducer.java
|       +-- ☕ TaskCFinalMain.java
|       +-- ☕ TaskCFinalDriver.java
|       +-- ☕ TaskCFinalMapper.java
|       +-- ☕ TaskCFinalReducer.java
|
+-- 📂 Builds
|   +-- 📦 TaskATemp.jar
|   +-- 📦 TaskAFinal.jar
|   +-- 📦 TaskBTemp.jar
|   +-- 📦 TaskBFinal.jar
|   +-- 📦 TaskCTemp.jar
|   +-- 📦 TaskCFinal.jar
|
+-- 📜 README.md
+-- 📜 Rapport.pdf
```

## Conventions

Quelques conventions ont été définies au préalable. 

### Casse des variables

Dans certains cas, une variable doit être définie dans une casse spéficique. Le tableau suivant est un récapitulatif des différents éléments et de leur casse associée : 

| Elément                   | Casse choisie        | Explication |
|---------------------------|----------------------|-------------|
| Classe                    | PascalCase           | Par défaut en Java
| Interface                 | PascalCase           | Par défaut en Java
| Fonction                  | PascalCase           | Permet d'obtenir de belles lignes comme ```premiereVariable.FonctionA()``` où chaque nouveau mot commence bien par une majuscule. Malheureusement, les fonctions par défaut en Java utilisent le camelCase... J'ai jamais aimé ce langage de toute façon
| Membre de classe          | camelCase_           | Par habitude pour Manon. Ajoutons tout de même un `_` à la fin pour bien montrer qu'il s'agit d'un membre de classe
| Argument de fonction      | snake_case           | Pour les différencier des membres de classe
| Variable locale           | camelCase            | Par habitude pour Manon. La différenciation précédente pour les membres de classe s'explique donc ici
| Variable statique globale | SCREAMING_SNAKE_CASE | Cette pratique est très moche. Une variable ne devrait **JAMAIS** être statique et globale. Ceci pointe donc du doigt cette pratique pour dire *"Oui nous savons que c'est très moche, mais nous n'avons pas trouvé mieux"*. **IMPORTANT** : Lire ces variables devant son écran en criant bien leur nom, et en rajoutant `C'EST MOCHE` 😉

### Langue des fichiers

La rédaction du code doit se réaliser en **Anglais**.   
La rédaction des commentaires doit se réaliser en **Français** pour faciliter la compréhension de notre travail.   
Voir [Commentaires](#commentaires) pour voir les autres indications pour les commentaires.

### Alignements

L'indentation des lignes doit se faire à l'aide de **tabulations** **UNIQUEMENT**.  
L'alignement d'une ligne à une autre doit se faire à l'aide d'**espaces** **UNIQUEMENT**. 

Pour mieux comprendre cette consigne, voici quelques exemples : 
```java
void LongueFonctionAvecBeaucoupDeParametres(int premier_parametre,
                                            int deuxieme_parametre,
                                            int troisieme_parametre,
                                            int quatrieme_parametre)
{
    // Ici, nous avons aligné les arguments, car il aurait été nécessaire d'utiliser la
    // barre de défilement horizontale pour lire la suite.

    /* 
     * Un commentaire en plusieurs ligne est également aligné par rapport au caractère '*'
     */ 

    // Il est également possible d'aligner des variables par rapport à leur nom ...
    int       variable1;
    short     variable2;
    long long variable3;
    String    variable4;

    // ... ou bien par rapport à l'opérateur d'initialisation ...
    HashSet<Float> longueVariable5 = new HashSet<Float>();
    HashSet<Float> variable6       = new HashSet<Float>();

    // ... ou bien les deux.
    int    variable7                  = 8;
    String longueVariablePourLExemple = "Blablabla";

    // Les conditions des boucles conditionnelles doivent également être
    // alignées avec le if (sans espace entre le if et la parenthèse !)
    if(variable1 == 5
    && variable2 == 6
    && variable4 == "Test")
    {
        // Ici, nous avons 2 tabulations
        int       longueVariable     = 5;     // Nous avons toujours 2 tabulations, les autres espaces blancs sont des espaces
        long long tresLongueVariable = 4444;  // Note : les commentaires sont également alignés
    }
}
```

### Commentaires

Il existe trois manières d'écrire un commentaire en Java : 
- Commentaire sur une seule ligne. Commence par `//`
- Commentaire sur plusieurs lignes. Commence par `/*` et finit par `*/`
- Commentaire de documentation. Commence par `/**` et finit par `*/`

La rédaction d'un commentaire doit suivre ces règles : 

```java
// Un commentaire quelconque est toujours séparé des caractères définissant le commentaire

/*
 * Un commentaire sur plusieurs lignes commence toujours à partir de la première ligne
 * sans barre oblique. 
 * De plus, il est important d'aligner les *, pour un résultat plus propre 
 */

/**
 * De même que pour le commentaire sur plusieurs lignes, le commentaire de documentation commence
 * toujours à partir de la première ligne sans barre oblique.
 * Concernant l'alignement des *, seule la première * de l'ouverture du commentaire est prise en compte. 
 */

/*
 * Ceci est un commentaire avec une liste à puces : 
 * - Premier point
 *   * Penser à changer de caractère pour la puce
 *     + Afin de mieux lire les instructions
 * - Deuxième point
 *   * Aligner les puces avec le premier caractère du niveau précédent
 *     + Pour que ce soit plus propre et plus agréable à lire 
 */

/*
 * Ceci est un commentaire avec une liste chiffrée : 
 * 1.  Ces commentaires sont 
 * 2.  un peu plus difficiles
 * 3.  à écrire en raison
 * 4.  de l'alignement qui
 * 5.  doit intervenir 
 * 6.  lorsque le nombre
 * 7.  de points abordés
 * 8.  dépasse 10. Pas
 * 9.  sûr que cela arrive
 * 10. à un moment donné,
 * 11. mais cette règle est
 * 12. quand même définie.
 */
```

## Commandes Hadoop
