# SMPL-Parser Interpreter

Built as a final project for COMP3652 (Language Processors). 

Group Members:
---------------------

 - Joel Henry  
 - Dexter Small  
 - Nazhon Robinson

 
How to compile and run
---------------------
 1. From the src/smpl/lang folder, run:
```
    $ jflex SMPLLexer.jflex
    $ cup -parser SMPLParser SMPLParser.cup
```
 2.   Compile from src folder with:
```
  $ javac -classpath ".:/usr/share/java/cup.jar" smpl/*/*.java 
```
3. Run from the src folder with: 
```
  $ java -classpath ".:/usr/share/java/cup.jar" smpl.sys.SMPLRepl 
```
4. (optional) To run with files, from the src folder do:
```
  $ java -classpath ".:/usr/share/java/cup.jar" smpl.sys.SMPLRepl <filepath>.smpl
```

Terminal fast compile and run 
---------------------
1. From the src/smpl/lang folder, run:
```
  ./compile.sh
```
2. Build and run from src folder with:
```
  ./build.sh
```

Of course, if your cup.jar file is installed elsewhere, you should modify your classpath accordingly.


