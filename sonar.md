##1. Install and run Sonar server locally

1) Add sonar profile to your `~/.m2/settings.xml`:
```xml
<settings>
    <pluginGroups>
        <pluginGroup>org.sonarsource.scanner.maven</pluginGroup>
    </pluginGroups>
    <profiles>
        <profile>
            <id>remoteSonar</id>
            <properties>
                <!-- Optional URL to server. Default value is http://localhost:9000 -->
                <sonar.host.url>
                    http://sonarqube.aws.rmsonecloud.net:9000
                </sonar.host.url>
            </properties>
        </profile>
     </profiles>
</settings>
```

2) Install Sonar locally
    - `brew update`
    - `brew install sonar`

3) Run Sonar
    - `brew services start sonar`

##2. Add Jacoco Maven plugin to your project pom file
 ```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.7.7.201606060606</version>
                <executions>
                    <execution>
                        <id>default-prepare-agent</id>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>default-report</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>default-check</id>
                        <goals>
                            <goal>check</goal>
                        </goals>
                        <configuration>
                            <rules>
                                <!--<rule>-->
                                    <!--<element>CLASS</element>-->
                                    <!--<excludes>-->
                                        <!--<exclude>*Test</exclude>-->
                                    <!--</excludes>-->
                                    <!--<limits>-->
                                        <!--<limit>-->
                                            <!--<counter>LINE</counter>-->
                                            <!--<value>COVEREDRATIO</value>-->
                                            <!--<minimum>0.50</minimum>-->
                                        <!--</limit>-->
                                    <!--</limits>-->
                                <!--</rule>-->
                            </rules>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build> 
 ```
 
Reference for rules:
 - http://www.eclemma.org/jacoco/trunk/doc/check-mojo.html
 

## How to see the coverage report
- By `mvn clean install`, jacoco generates coverage reports and put them in target directory of each module.
- By `mvn sonar:sonar`, sonar pushes the reports and they can be accessed through browser on `http://localhost:9000`.
- By `mvn sonar:sonar -PremoteSonar`, sonar pushes the reports to remote server on `http://sonarqube.aws.rmsonecloud.net:9000`.
