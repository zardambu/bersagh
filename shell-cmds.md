### Java

- run a class with main function in a jar file 
    + `java -cp file.jar com.rms.store.KuduDataLoader peqt create`
    
- Viewing the Contents of a JAR File
    + `jar tf jar-file`


### Maven

- jgitflow
    + `mvn jgitflow:release-start`
    + `mvn jgitflow:release-finish -Dmaven.javadoc.skip=true`


### HDFS
- `hdfs dfs -ls /`
- `hdfs dfs -mkdir /eufl`
- `hdfs dfs -put smoke34* /eufl`


### Misc
    
- disk usage in a directory
    + `du -sh`

- disk free space
    + `df`
    
- md5 digest of a file (to check if a transmitted file is equal to origianl one)
    + md5sum fileName
    + md5 fileName

- sort java files in terms of number of lines
    + `find . -type f | xargs  wc -l | sort -r | grep java | grep -v target | grep -v test`