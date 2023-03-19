# Tondeuse à gazon automatique
## Description
Ce projet permet de programmer une tondeuse à gazon automatique afin que cette dernière puisse parcourir l'intégralité
d'une surface rectangulaire.

## Installation
### Prérequis 
Afin de pouvoir exécuter ce programme sur votre machine vous installer [java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) 
et [maven](https://maven.apache.org/install.html).
## Télécharger le code source
#### Récupérer le code avec git
Vous pouvez utiliser git pour télécharger le code  sur machine. Pour ce faire, utiliser la ligne commande ci-dessous
```shell
git clone https://github.com/fofanaabou/lawn-mower.git
```
### Récupérer avec le zip
Vous pouvez télécharger une version compression sur en vous rendant sur le menu `code -> Download Zip`

## Utilisation
Après avoir cloner ou téléchager le projet sur votre machine, ci-dessous les instructions pour l'exécuter:

 1) Rendez-vous dans le repertoire lawn-mower puis lancer la commande suivante:
 ```shell
mvn package
```
2) Créer un fichier txt contenant des données comme ce example 
```text
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA
```
3) Aller dans le repertoire lawn-mower et la lancer la commande suivante:
```shell
java -jar target/lawnDimension-clipper-1.0-SNAPSHOT.jar
```
Vous aurez cet affichage
```shell
2018P002:/mnt/c/Users/mrdispo/Desktop/workspace/sapient/lawn-mower$ java -jar target/lawnDimension-clipper-1.0-SNAPSHOT.jar
Enter the file's path (Example -> /user/home/data/info.txt):
```
Vous devez fournir l'emplacement du fichier, un exemple a été donné précédement.

5) Exemple de résultat
```shell
====Clippers info=========
+-----------------+------+
| Last position   | ID   |
+-----------------+------+
| 1 3 N           | 1    |
| 5 1 E           | 2    |
+-----------------+------+
```