# dr-gp-scoring

An example of scoring data in database with datarobot codegen.

## required

I built this with

* postgres (PostgreSQL) 11.5
* [pl/java 1.5.2 ](https://github.com/tada/pljava) - include pljava 1.5.2 jar in `lib`
* openjdk version "1.8.0_152-release"

See [Pl/JAVA wiki](https://tada.github.io/pljava/) for instructions on installation

In postgres.conf file - you will set pljava.classpath variable to  point at your jars.  According to the documentation this variable isn’t meant to point to the code you develop and use in PL/Java, but I could not get the code to work using the `sqlj.install_jar`.

With that said, add pljava jar, codegen jar, and jar containing user defined function via config file, or through psql

`SET pljava.classpath TO '/path/to/pljava-1.5.2.jar:/path/to/5d5da72a3fa59e2850f824fc.jar:/path/to/postgres-scoring-0.1.0.jar';`

## data

lending club dataset `data/10K_Lending_Club_Loans.txt`.  see schema

see schema in `misc/table_schema.sql`

## postgres scoring function

see `misc/pljava.ddr` for `CREATE FUNCTION` syntax.  if you get permission errors, set the language to javau
THe file `pljava.ddr` is generated when building the jar provided functions are annotated properly.  

## java scoring

* `src/main/java/Scoring.java`.  This version creates a function that feels more sql-ish.  

* `src/main/java/BatchScoring.java` - is a batch scoring function which takes as an argument a table.  Expectation here is that all features for model are provided in the table.  This also has mlops tracking agents set up.  If you go through, you should fix the "settings" for the mlops agents as it is hardcoded to my deployment.  Currently this is set for feature tracking, prediction tracking with association ids and stats for how long it takes to score.

## models

see `lib/5d5da72a3fa59e2850f824fc.jar`
