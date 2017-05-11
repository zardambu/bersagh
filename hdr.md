- Maven Dependency
```
    <dependency>
        <groupId>org.hdrhistogram</groupId>
        <artifactId>HdrHistogram</artifactId>
    </dependency>
```

- Creation
    + `Histogram(long highestTrackableValue, int numberOfSignificantValueDigits)`
    + create a range from [1, highest]
    + `numberOfSignificantValueDigits` should be <= 5
        * specifies accuracy (bin size) at a given value
        * for example, if `h = 3,600,000,000` (1 hour in m.sec) and `n = 3`
            + the coarsest bin resolution is 3,600,000 (3.6 sec)
            + the finest is 1 m.sec

- Recording
    + `histogram.recordValue(long value)`

- Output
    + `histogram.outputPercentileDistribution(PrintStream printStream, Double outputValueUnitScalingRatio)`