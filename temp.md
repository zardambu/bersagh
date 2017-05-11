mvn jgitflow:release-start
mvn jgitflow:release-finish -Dmaven.javadoc.skip=true




Expected:

CURRENCY=USD,
INCEPTION=16 Oct 2013,
EXPIRATION=15 Oct 2014,
DEDUCTIBLES=not Absorbable,
SUBLIMITS=net of deductible,
ATTACHMENT BASIS=Loss Occurring,
OCCURRENCES=20 hours,
EXCLUSIONS=Alpha by Beta,
USING=XYZ {1.0},
PRODUCT=XYZ {2.0},
TYPE=ABC,
RISK=each Section,
NAME={1201 PartOff},
LOB=Commercial,
CAUSESOFLOSSCODES=ALL,FL,EQ


Actual:

CURRENCY=USD,
INCEPTION=16 Oct 2013,
EXPIRATION=15 Oct 2014,
DEDUCTIBLES=not Absorbable,
SUBLIMITS=net of deductible,
ATTACHMENT BASIS=Loss Occurring,
OCCURRENCES=20 hours,
EXCLUSIONS=Alpha by Beta,
USING=XYZ {1.0},
PRODUCT=XYZ {2.0},
STATUSTYPE=ABC,
NAME={1201 PartOff},
RISK=each Section,
LOB=Commercial,
CAUSESOFLOSSCODES=ALL,FL,EQ