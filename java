+---------------+
| Serialization |
+---------------+

* java.io.Serializable is a 'marker interface'
    -> it doesn't declare any functionality
    -> it just flags to other entities that this class is serializable

* The serializable interface actually means:
    "I, as a programmer, has understood the consequences of serialization and permit the JVM to serialize this"

* There are some problems with serialization that all objects are not Serializable by default
    -> long term persistence ... class internal design might change => breaking encapsulation
    -> security ... any object you have a reference to could be serialized and its internal could be accessible
    -> For more info checkout "Effective Java 2nd Edition", "Item 74: Implement Serializable judiciously."


