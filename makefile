all:
	javac -d ./classes/ -Xlint:unchecked src/interationdesigner/InteractionDesigner.java src/interationdesigner/models/*.java

run:
	java -cp .:./classes/ interactiondesigner.InteractionDesigner

test: all
	javac -cp .:./libs/junit-4.jar:./classes -d ./test/classes/ ./test/*.java
	java -cp .:./libs/junit-4.jar:./libs/hamcrest-core-1.3.jar:./classes:./test/classes/ org.junit.runner.JUnitCore ActionTest InteractionTableTest InteractionTest

clean:
	rm -rf classes/*
	rm -rf test/classes/*