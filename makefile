tests = ActionTest InteractionTableTest InteractionTest InteractionControllerTest ActionFactoryTest
testClasspath = .:./libs/junit-4.jar:./libs/hamcrest-core-1.3.jar:./classes:./test/classes/
testBuildPath = .:./libs/junit-4.jar:./classes

classpath = .:./classes/
mainclass = interactiondesigner.InteractionDesigner;
buildTargets = src/interationdesigner/InteractionDesigner.java src/interationdesigner/models/*.java src/interationdesigner/controllers/*.java src/interationdesigner/utils/*.java


all:
	javac -d ./classes/ -Xlint:unchecked $(buildTargets)

run:
	java -cp $(classpath) $(mainclass)

test: all
	javac -cp $(testBuildPath) -d ./test/classes/ ./test/*.java
	java -cp $(testClasspath) org.junit.runner.JUnitCore $(tests)

clean:
	rm -rf classes/*
	rm -rf test/classes/*