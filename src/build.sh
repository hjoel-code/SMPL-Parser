javac -classpath ".:/usr/share/java/cup.jar" smpl/*/*.java
javac -classpath ".:/usr/share/java/cup.jar" smpl/lang/*/*.java
javac -classpath ".:/usr/share/java/cup.jar" smpl/values/type/*/*.java

java -classpath ".:/usr/share/java/cup.jar" smpl.sys.SMPLRepl 
# java -classpath ".:/usr/share/java/cup.jar" smpl.sys.SMPLRepl ../examples/fib.smpl
# java -classpath ".:/usr/share/java/cup.jar" smpl.sys.SMPLRepl ../examples/if-test.smpl
