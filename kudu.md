## How to install
- Install VirtualBox
- Download and start Kudu QuickStart VM by following
    http://kudu.apache.org/docs/quickstart.html
    
## Notes
- The name of the VM is "quickstart.cloudera"
- Kudu dashboard on the VM:
    + `http://quickstart.cloudera:8051‍‍`
- Table names and properties can be seen in the dashboard
- To see data in the tables:
    + click on table id
    + copy Impala CREATE TABLE statement
    + `ssh demo@quickstart.cloudera` (user:demo, pass:demo)
    + run impala-shell
    + paste the copied create table statement (it should be one line statement)
    + `show tables;`
    + `select * from table_name;`

## Kudu application in Java / Scala
```scala
    val KUDU_MASTER = "quickstart.cloudera"
    val client = new KuduClient.KuduClientBuilder(KUDU_MASTER).build()
    val tableName = "scala_test3"

    // create table
    val columns : util.List[ColumnSchem````a] = new util.ArrayList(2)
    columns.add(new ColumnSchema.ColumnSchemaBuilder("key", Type.INT32)
      .key(true)
      .build()
    )
    columns.add(new ColumnSchema.ColumnSchemaBuilder("value", Type.STRING)
      .build()
    )
    val rangeKeys = ImmutableList.of("key")
    val schema = new Schema(columns)
    client.createTable(tableName, schema,
      new CreateTableOptions().setRangePartitionColumns(rangeKeys)
    )

    // populate
    val table: KuduTable = client.openTable(tableName)
    val session = client.newSession()
    for (i <- 1 to 3) {
      val insert = table.newInsert()
      val row : PartialRow = insert.getRow()
      row.addInt(0, i)
      row.addString(1, "value " + i)
      session.apply(insert)
    }

    // retrieve
    val projectColumns = ImmutableList.of("value")
    val scanner = client.newScannerBuilder(table)
      .setProjectedColumnNames(projectColumns)
      .build()

    while (scanner.hasMoreRows()) {
      val results : RowResultIterator = scanner.nextRows()
      while (results.hasNext()) {
        val result : RowResult = results.next()
        System.out.println(result.getString(0))
      }
    }

    // delete
    client.deleteTable(tableName)
```

## Concepts
- Tablets
    + for scalability, Kudu splits tables into smaller units called tablets
    + splitting can be per-table basis based on
        - hashing
        - range partitioning
    + two iterators are involved for reading data
        - first goes over different tablets `RowResultIterator`
        - second goes over the results in one tablet `RowResult`