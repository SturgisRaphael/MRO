#!/bin/bash
statfile=stat.txt
rm ${statfile}
for file in $1/*; do
  f=${file##*/}
  if [ "${f%.*}" != "generate_results" ]
  then
	  echo ${f%.*} >> stat.txt
  	  time (./xcsp3-exec ${file} > results/${f%.*}.si) 2>> stat.txt
  	  echo >> stat.txt
  fi
done
