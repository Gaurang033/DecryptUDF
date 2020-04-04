Custom UDF
---------

```bash
%> mvn package
%> hive
hive> ADD JAR target/DecryptUDF-1.0.0.jar;
hive> CREATE TEMPORARY FUNCTION Encrypt as 'net.allabouthadoop.Encrypt';
hive> CREATE TEMPORARY FUNCTION Decrypt as 'net.allabouthadoop.Decrypt';
hive> select Encrypt(col_name) from table_name limit 1000;
hive> select Decrypt(col_name) from table_name limit 1000;
```
