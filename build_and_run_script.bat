set CLASSPATH=.\;.\lib\;.\bin;.\lib\slf4j-api-1.7.3.jar;.\lib\logback-classic-1.0.10.jar;.\lib\logback-core-1.0.10.jar;.\lib\opencv-245.jar
javac -sourcepath .\src\ .\src\edu\KorobovMS\Lab6.java -d .\bin
cd bin
set CLASSPATH=..\;..\lib\;..\bin;..\lib\slf4j-api-1.7.3.jar;..\lib\logback-classic-1.0.10.jar;..\lib\logback-core-1.0.10.jar;..\lib\opencv-245.jar
java -Djava.library.path=..\lib\x86\ edu.KorobovMS.Lab6 2> ..\out.txt
cd ..
