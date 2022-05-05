.PHONY: all

all:
	java -jar jflex-full-1.8.2.jar minijava.flex
	java -jar java-cup-11b.jar -interface -parser MiniJavaParser minijava.cup
	javac MiniJavaC.java
	java MiniJavaC milestones/$(file).java
	java -jar Mars4_5.jar mips/$(file).asm
	


clean:
	rm MiniJavaLexer.java MiniJavaParser.java
	rm *.class
	rm ir/*.class
	rm syntaxtree/*.class
	rm visitor/*.class
	rm codegen/*.class
	rm registerallocator/*.class