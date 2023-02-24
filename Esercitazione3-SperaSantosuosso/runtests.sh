#!/bin/bash

> my1.report.txt
> my2.report.txt

for TESTFILE in tests/*
do
	echo $(basename -- "$TESTFILE") >> my1.report.txt
	mvn --batch-mode -q exec:java -Dexec.args="$TESTFILE" >> my2.report.txt 2>&1
done

echo 'WARNING: An illegal reflective access operation has occurred' > my.skip.txt
echo 'WARNING: Illegal reflective access by com.google.inject.internal.cglib.core.$ReflectUtils$1 (file:/usr/share/maven/lib/guice.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)' >> my.skip.txt
echo 'WARNING: Please consider reporting this to the maintainers of com.google.inject.internal.cglib.core.$ReflectUtils$1' >> my.skip.txt
echo 'WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations' >> my.skip.txt
echo 'WARNING: All illegal access operations will be denied in a future release' >> my.skip.txt
grep -v -x -F -f my.skip.txt my2.report.txt > my3.report.txt
paste my1.report.txt my3.report.txt > my.report.txt

cat my.report.txt

while read p; do
	if [[ ! "$p" =~ ^invalid[0-9]*.txt.Syntax.error$ ]] && [[ ! "$p" =~ ^valid[0-9]*.txt.Input.is.valid$ ]]; then
		echo "Error"
		exit 1
	fi
done < my.report.txt

echo "Ok"
