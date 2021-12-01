del afujita\src\game\*.class
SET /P _filename= Enter xml file (w/o .xml): 
cd ./afujita/src/
javac game/Rogue.java
java game.Rogue %_filename%.xml
cd ../../