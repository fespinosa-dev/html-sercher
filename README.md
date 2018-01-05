# html-sercher
A command-line based Information Retrieval system using Apache Lucene version and Java that parses and indexes HTML documents in a given folder and its
subfolders listing all parsed files. Lucene is an open source search library that provides standard functionality for analyzing, indexing, and searching text-based documents.


## How to install

The program is wrriten in Java so you must have installed the latest version of the [JRE/JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

Download apche maven to build the project if you don't have it installed. [maven](http://www-eu.apache.org/dist/maven/maven-3/3.5.2/binaries/apache-maven-3.5.2-bin.zip). 

```
git clone https://github.com/fjevictoriano/html-sercher.git

cd html-sercher

mvn install

cd target/

mv IR_P01-1.0-SNAPSHOT-jar-with-dependencies.jar htmlsearcher.jar

```

## How to use

You can choose wich space model to use for scoring. 

**VZ**: Vector Space Model

**OK**: Okapi BM25

```
java -jar htmlsearcher.jar [path to document folder] [path to index folder] [VS/OK] [query] 
```



