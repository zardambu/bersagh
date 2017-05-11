### Actions

- reduce
    + `reduce(f: (T, T) => T): T`
- fold
    + `fold(zeroValue: T)(op: (T, T) => T): T`
    + `op` args are `(acc, occ)` and `acc` can be reused to prevent object creation (main difference with reduce)
- aggregate
    + `aggregate[U: ClassTag](zeroValue: U)(seqOp: (U, T) => U, combOp: (U, U) => U): U`
    
    
    
### Transformations in Pair RDDs

- reduceByKey
- foldByKey
- aggregateByKey
- combineByKey
    + `combineByKey[C](createCombiner: V => C, mergeValue: (C, V) => C, mergeCombiners: (C, C) => C): RDD[(K, C)]`
    
    
    
### Actions on Pair RDDs

- countByKey
- collectAsMap
- lookup
    + `lookup(key: K): Seq[V]`
    


### Grouping

- groupByKey
    + `groupByKey(): RDD[(K, Iterable[V])]`
- groupBy
    + `groupBy[K](f: T => K)(implicit kt: ClassTag[K]): RDD[(K, Iterable[T])]`
- cogroup
    + `cogroup[W](other: RDD[(K, W)]): RDD[(K, (Iterable[V], Iterable[W]))]`
    + cogroup can be used to cogroup with two/three other RDDs
    + `groupWith` is another alias for cogroup
- join
    + `join[W](other: RDD[(K, W)]): RDD[(K, (V, W))]`
    + `leftOuterJoin[W](other: RDD[K, W]): RDD[K, (V, Option[W])]`
    + `rightOuterJoin[W](other: RDD[K, W]): RDD[K, (Option[V], W)]`
    + `fullOtherJoin[W](other: RDD[K, W]: RDD[K, (Option[V], Option[W])])`
    
    
    
###Partitioning

- join with no prior partitioning
    + join operation hashes all keys in both RDDs;
    + sends the elements with the same key across the network to the same machine; and
    + join together the elements with the same key on that machine
    
- join with one RDD being pre-partitioned (with known partitioner)
    + the pre-partitioned RDD won't be shuffled in join
    + the other RDD gets shuffled
    
- join with both RDD being pre-partitioned with the same partitioner
    + no shuffle happens as partitions with the same key of the two RDDs coexist in the same nodes

- `map` does not guaranty to produce a known partitioning on a pair RDD
    + `mapValues` and `flatMapValues` do instead
    
- operations that result in a partitioner being set on the output RDD
    + `cogroup`, `groupWith`
    + `join`, `leftOuterJoin`, `rightOuterJoin`, `fullOuterJoin`
    + `groupByKey`, `reduceByKey`, `combineByKey`
    + `partionBy`, `sort`
    + `mapValues`, `flatMapValue`, `filter` if parent has a partitioner

- custom partitioners
    + extend `Partitioner` class
    + essentially overriding
        * `numPartions: Int`
        * `getPartition(key: Any): Int`
        * `equals(other: Any): Boolean`
    + since java hashcode might return negative numbers, make sure to bound it to [0, n-1] in getPartition function
    + `equal` is a must and needed for spark to decided whether two of your RDDs are partitioned in a say way 
    
    

### Accumulator

- accumulators are `write-only` variables
    + tasks on the worker nodes cannot access acc value
    + only driver can read its value
    + it makes acc efficient
    
- use accumulator only in `actions`, not `transformations`
    + did not understand why?



### Broadcast

- broadcast variables are `read-only` like closure variables

- advantage of broadcast over automatic closure
    + automatic closure is designed for small task sizes
    + one variable might be used in multiple parallel operations
        * with closure, for each operation it gets serialized and sent over
        * with broadcast, it's only sent to one node only once

- Type T in broadcast has to be serializable



### Per-Partition Operators

- advantage
    + avoid setup work 
        * like database connection or creating a random number generator
    + avoid object creation overhead
    
- mapPartitions
    + `mapPartitions[U: ClassTag](f: Iterator[T] => Iterator[U], preservesPartitioning: Boolean = false): RDD[U]`

- foreachPartition
    + `foreachPartition(f: Iterator[T] => Unit): Unit`
    


### Spark Runtime Architecture

- driver
    + converts a user program into tasks
        * 
    + schedules tasks on executors
- executors
- cluster manager

    
### Misc

- coalesce
    + `coalesce(numPartition: Int)`
    + reduce the number of partitions without reshuffling the data
    
- partition size
    + `rdd.partition.size()`

- default serialization is very inefficient except for array of primitive types
    + use `kryo` instead
    
- Numeric RDD Operation
    + summary statistics available from `StatCounter` - returned by calling `stats()`
        * `count`, `sum`
        * `min`, `max`
        * `mean`, `stdev`, `variance`
    + above functions could be directly called 
        * `rdd.max()`, `rdd.mean()`, ...
        * `rdd.historgram(bucketCount: Int)`
    

