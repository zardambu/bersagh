
 - PartialFunction
 	+ has isDefinedAt(x) like math functions
 	+ collect accepts partial functions
 	+ map accept functions

 - Blocks in Scala are themselves expressions

 - Tail-recursive
 	+ The final call in a tail-recursive function can be implemented by a jump back to the beginning of that function
 	+ No new stack space is needed

 - Higher-order functions
 	+ Functions which take other functions as parameters or return them as results

 - Curring
 	+ func(func2)(params...) = {...}

 - Parameterless methods are accessed just as value fields

 - Abstract classes
 	+ may have "deferred members"
 	+ no object of abs class can be created with "new"

 - Trait are interfaces which may have some implementation (like abstract classes, but only for some functionalities)

 - Dynamic dispatch
	+ the code invoked for a method call depends on the run-time type of the object which contains the method

- Lazy evaluation for singleton "object"
	+ The object is created the first time one of its members is accessed

- class/trait/object definitions
	+ class C(params) extends B { defs }
	+ trait T extends B { defs }
	+ object O extends B { defs }

- Case class particularities:
	+ no need to use "new"
	+ predefined hashCode, equals, and toString methods
	+ direct access to constructor paramters
	+ pattern matching

- Pattern matching is the generalization of switch statement to class hierarchy
	+ e match { case p1 => e1 ... case pn => en }

- Type parameters are passed in brackets [] while value parameters are passed in parentheses ()

- Local type inference
	eg: [String] can be omitted in isPrefix(s1, s2) while we know s1 & d2 are stack with type string
	    in def "isPrefix[A](p: Stack[A], s: Stack[A]): Boolean = ..."

- Type parameter bounds
    + The parameter declaration A <: Ordered[A] introduces A as a type parameter which must be a subtype of Ordered[A]
        in "trait Set[A <: Ordered[A]] {...}"

- View bounded type parameter
    + A view bounded type parameter clause [A <% T] only specifies that the bounded type A must be convertible to the bound type T
        using an implicit conversion

- Lower bounds
    + T >: S, the type parameter T is restricted to range only over supertypes of type S

- Co-variant subtyping
    + Stack[+A] => if S is sub-type of T, Stack[S] is sub-type of Stack[T]

- Contra-variance vs covariance
	+ Functions input are contra-variant, but the outputs are covariant f:-A => -B

- String interpolation
    + Anytime compiler encounters a string literal similar to id"abc $t1 def $2 ghi", it converts it to:
        new StringContext("abc ", " def ", " ghi").id(t1, t2)
    + You may create your own string interpolation by creating an implicit class taking a StringContext as ctor parameter like
        implicit class JsonHelper(val sc: StringContext) extends AnyVal {
            def json(args: Any*): JSONObject = ...
        }
        having above, json"{ name: $name, id: $id }" gets converted to
            new JsonHelper(new StringContext("{ name:", ",id: ", "}")).json(name, id)

- Extractors
    + An extractor has the opposite role of a constructor
        - `apply` constructs an object using some parameters
        - `unapply` decomposes an object to its parameters
    + One parameter object
        - `def unapply(object: S): Option[T]`
        - ex: `def unapply(user: FreeUser): Option[String] = Some(user.name)`
    + Multi Parameter object
        - `def unapply(object: S): Option[(T1, ..., Tn)]`
        - ex: `def unapply(user: PremiumUser): Option[(String, Int)] = Some((user.name, user.score))`
    + Boolean parameter object
        - `def unapply(object: S): Boolean`
        - ex: `def unapply(user: FreeUser): Boolean = user.upgradeProbability > 0.75`
        
- Traits vs abstract classes
    + Traits only have type parameters, while abstract classes can have both constructor parameters as well as type parameters
    + A class can inherits from multiple traits, but only one abstract class
    + Abstract class is fully interoperable with Java, while traits need to have no implementations to be interoperable with Java
    
- Parameter-less methods
    + if method returns something, skip parentheses to show it's an expression
    + if it's unit, put parentheses to show that it's a method and has side effect
    + if definition has no parentheses, calling side cannot have parentheses either

- Self types
    + using `this: AnotherType =>` in a trait means this trait needs to be mixed in with type `AnotherType`
    + using `self =>` (or anything like `outer =>`) in a class/trait create an alias for `this` to prevent shadowing of inner classes
    + multiple types 
        - `this: typeA with typeB with typeC`
        - if multiple types have a method with same signature, the function in the leftmost type is used 

- Type erasure
    + `https://medium.com/byte-code/overcoming-type-erasure-in-scala-8f2422070d20#.h0sgeixy0`


- Anonymous class
    + ???
    
- Matchers
    + ???
    
- Enums
    + ???

