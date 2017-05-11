#SED

- sed s'/pattern/replacement/' <oldFile >newFile
- & is used for the replacement pattern
    + meaning: the matched pattern
- \1 first remembered pattern, \2 second remembered pattern, ...
- ? means non-greedy
    + .*? is everything non-greedy
- flag 'd' is for delete
    + sed '/pattern/d' input_file > output_file
- flag '-i.bak' is for edit in place
    + sed -i.bak '/pattern/d' input_file
- flag `g` is to apply for all matched cases in the line
    + example: reformat csv file to have aligned columns
        * `cat imap.csv | sed s/,/,@/g | column -t -s "@" | pbcopy`


####Examples:

- copy and rename file name with different extensions
    + e.g.: (1.cdl, 1.exp, 1.claim => sub-schedule.cdl, sub-schedule.exp, sub-schedule.claim)
```
    ls 1.* | sed 's/1\(.*\)/cp & sub-schedule\1/' | bash
    ls 10.* | sed 's/10\(.*\)/cp & 11\1/' | bash
    ls 6.* | sed 's/6\(.*\)/mv & 6.JapaneseStepPoliciSample2\1/' | bash
    ls 5.* | sed 's/5.MultipleSectionsWithPerRiskCovers\(.*\)/cp & 6.MultipleSectionsWithPerRiskCovers+CoverTreeOnTop\1/' | bash
    ls 7.* | sed 's/7.PerRisk\(.*\)/cp & 8.Expression\1/' | bash
```

- delete subject line in all cdl files in all directories
```
    for file in $(find . -name '*cdl')
    do
        sed '/.*[S|s]ubject.*/d' $file > $file.temp
        mv $file.temp $file
    done

    or simply:
        sed -i.bak '/.*[S|s]ubject.*/d' *
```

- extract set ids from logs
```
    Sample logs:
...
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142) [na:1.8.0_101]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617) [na:1.8.0_101]
	at java.lang.Thread.run(Thread.java:745) [na:1.8.0_101]
15:35:52.398 [sessionId:] [jobExecutionId:] [New I/O worker #8] INFO  org.kududb.client.AsyncKuduClient - Removing server ac7d625b536d4b3f994a2f7938c15252 from this tablet's cache e370f49856074c59a6dd45d61183599c
Finished uploading /Users/smoradi/Downloads/PEQTs/Simset_69_JPEQ.csv to Kudu server.


Started reading /Users/smoradi/Downloads/PEQTs/Simset_56_ACEQ.csv
15:36:34.540 [sessionId:] [jobExecutionId:] [New I/O worker #10] INFO  org.kududb.client.AsyncKuduClient - Discovered tablet Kudu Master for table Kudu Master with partition ["", "")
15:36:34.687 [sessionId:] [jobExecutionId:] [New I/O worker #10] INFO  org.kududb.client.AsyncKuduClient - Discovered tablet e370f49856074c59a6dd45d61183599c for table event-occurrence-sets with partition ["", "")
15:36:47.051 [sessionId:] [jobExecutionId:] [New I/O worker #13] INFO  org.kududb.client.AsyncKuduClient - Removing server ac7d625b536d4b3f994a2f7938c15252 from this tablet's cache e370f49856074c59a6dd45d61183599c
15:37:16.653 [sessionId:] [jobExecutionId:] [New I/O worker #17] INFO  org.kududb.client.AsyncKuduClient - Removing server ac7d625b536d4b3f994a2f7938c15252 from this tablet's cache e370f49856074c59a6dd45d61183599c
15:37:33.135 [sessionId:] [jobExecutionId:] [New I/O worker #13] INFO  org.kududb.client.AsyncKuduClient - Removing server ac7d625b536d4b3f994a2f7938c15252 from this tablet's cache e370f49856074c59a6dd45d61183599c
15:38:11.936 [sessionId:] [jobExecutionId:] [New I/O worker #17] INFO  org.kududb.client.AsyncKuduClient - Removing server ac7d625b536d4b3f994a2f7938c15252 from this tablet's cache e370f49856074c59a6dd45d61183599c
15:38:51.881 [sessionId:] [jobExecutionId:] [New I/O worker #13] INFO  org.kududb.client.AsyncKuduClient - Removing server ac7d625b536d4b3f994a2f7938c15252 from this tablet's cache e370f49856074c59a6dd45d61183599c
15:39:30.401 [sessionId:] [jobExecutionId:] [New I/O worker #17] INFO  org.kududb.client.AsyncKuduClient - Removing server ac7d625b536d4b3f994a2f7938c15252 from this tablet's cache e370f49856074c59a6dd45d61183599c
15:39:55.262 [sessionId:] [jobExecutionId:] [New I/O worker #13] INFO  org.kududb.client.AsyncKuduClient - Removing server ac7d625b536d4b3f994a2f7938c15252 from this tablet's cache e370f49856074c59a6dd45d61183599c
Finished uploading /Users/smoradi/Downloads/PEQTs/Simset_56_ACEQ.csv to Kudu server.
...
    
    pbpaste| grep Finished | sed 's/.*\_\(.*\)\_.*/\1/'
```



#Bash
- `for` & `if` statements
    + Note: there has to be space between condition and square bracket

    + delete all branches except develop
```
        for branch in $(git branch)
        do
            if [ $branch != develop ] && [ $branch != "*" ]
            then
                git branch -D $branch
            fi
        done
```        


#Regex
- non whitespace [^s]
- Changing case with regular expressions
    + `\L` and `\U` change all characters cases until there's a `\E` or `\e`
    + `\l` and `\u` change one character case
    + example: convert `geoRecNum.toInt,` to `valueOf(GeoRecNum),` in Intellij
        `([^\s])([^\s]*)\..*,`     =>    `valueOf(\u$1$2)`
- Magical alignment in Intellj
    + first replace `( )\+=` with `                 +=`
    + then replace `(\w[\w| ]{18}) +(.+)` with `$1$2`

#grep
- Text in subfolders	
    + `grep -r Walden ~/Documents/*` 
        Finds Walden in any file in any subfolder of ~/Documents.

- Whole words only	
    + `grep -w live`
        Finds only live ; does not find liver , lives , lived , and s on.
    
- Case-insensitive text	
    + `grep -i pond` 
        Finds pond , POND , or Pond .

- Number of occurrences only
    + `grep -c Walden -r .` 
        Returns the names of files containing Walden and the number of hits in each file.

- Regex
    + `grep "GB,\ *[0-9]\+,\ *1," imap.csv`
        * use `\ ` for `space`
        * use `\+` for quantifier +
