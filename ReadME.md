### Особенности реализации
Парсинг параметров сделан с помощью apache commons cli чтобы не писать парсинг аргументов вручную.
Написан юнит тест для парсера аргументов.

### Версии java и maven
Java: 17

maven: 3.9.9

### Сборка

```bash
git clone https://github.com/winroot333/FileRowFilter.git
cd FileRowFilter
mvn clean package -DskipTests
```
### Запуск
```bash
java -jar ./target/file-row-filter.jar -o ./output -s ./testdata/testfile_1.txt ./testdata/testfile_2.txt
```
### Библиотеки
```xml
    <properties>
        <lombok.version>1.18.38</lombok.version>
        <commons-cli.version>1.9.0</commons-cli.version>
        <junit-jupiter.version>5.13.4</junit-jupiter.version>
        <assertj-core.version>3.27.3</assertj-core.version>
    </properties>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/commons-cli/commons-cli -->
        <dependency>
            <groupId>commons-cli</groupId>
            <artifactId>commons-cli</artifactId>
            <version>${commons-cli.version}</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit-jupiter.version}</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.assertj/assertj-core -->
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>${assertj-core.version}</version>
            <scope>test</scope>
        </dependency>
