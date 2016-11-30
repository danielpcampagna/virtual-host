JSON_VERSION = 1.1.1

install:
	##########################
	## INSTALLING DEPENDENCIES
	##########################
	### JSON Java
	#wget https://search.maven.org/remotecontent?filepath=org/json/json/$(JSON_VERSION)/json-$(JSON_VERSION).jar -O lib/json-java.jar
	wget http://central.maven.org/maven2/com/googlecode/json-simple/json-simple/$(JSON_VERSION)/json-simple-$(JSON_VERSION).jar -O lib/json-java.jar
	##########################
	## END DEPENDECIES
	##########################

clear:
	##########################
	## DEPENDECIES
	##########################
	rm lib/*.jar
	##########################
	## END DEPENDENCIES
	##########################
setup:
	##########################
	## COMPILE
	##########################
	javac -cp lib/json-java.jar src/app/**.java src/app/domain/**.java src/conf/**.java src/util/**.java Main.java
	##########################
	## END COMPILATION
	##########################

start:
	java -cp .:lib/json-java.jar Main

generate-key:
	javac -cp lib/json-java.jar src/conf/Conf.java src/util/JSONDAO.java src/util/RelativePath.java src/util/GeradorDeChaveSimetrica.java
	java -cp .:lib/json-java.jar src/util/GeradorDeChaveSimetrica