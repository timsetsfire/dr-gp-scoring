# dr-gp-scoring

An example of taking a datarobot codegen model and installing in postgres.

## required

I built this with

* postgres (PostgreSQL) 11.5
* [pl/java 1.5.2 ](https://github.com/tada/pljava)
* openjdk version "1.8.0_152-release"

See [Pl/JAVA wiki](https://tada.github.io/pljava/) for instructions on installation

In postgres.conf file - you will set pljava.classpath variable to  point at your jars.  According to the documentation this variable isn’t meant to point to the code you develop and use in PL/Java, but I could not get the code to work using the `sqlj.install_jar`.  

With that said, add pljava jar, codegen jar, and jar containing user defined function via config file, or through psql

`SET pljava.classpath TO '/path/to/pljava-1.5.2.jar:/path/to/5d5da72a3fa59e2850f824fc.jar:/path/to/postgres-scoring-0.1.0.jar';`

## postgres scoring function

see `misc/pljava.ddr`

## data

lending club dataset (see data folder).  see schema below

Table "public.lending_club"
Column            |          Type           | Collation | Nullable | Default | Storage  | Stats target | Description
-----------------------------+-------------------------+-----------+----------+---------+----------+--------------+-------------
application_id              | integer                 |           |          |         | plain    |              |
loan_amnt                   | integer                 |           |          |         | plain    |              |
funded_amnt                 | integer                 |           |          |         | plain    |              |
term                        | character varying(128)  |           |          |         | extended |              |
int_rate                    | character varying(128)  |           |          |         | extended |              |
int_rate_1                  | character varying(128)  |           |          |         | extended |              |
installment                 | double precision        |           |          |         | plain    |              |
grade                       | character varying(128)  |           |          |         | extended |              |
sub_grade                   | character varying(128)  |           |          |         | extended |              |
emp_title                   | character varying(128)  |           |          |         | extended |              |
emp_length                  | character varying(128)  |           |          |         | extended |              |
home_ownership              | character varying(128)  |           |          |         | extended |              |
annual_inc                  | character varying(128)  |           |          |         | extended |              |
verification_status         | character varying(128)  |           |          |         | extended |              |
pymnt_plan                  | character varying(128)  |           |          |         | extended |              |
url                         | character varying(512)  |           |          |         | extended |              |
description                 | character varying(4096) |           |          |         | extended |              |
purpose                     | character varying(512)  |           |          |         | extended |              |
title                       | character varying(512)  |           |          |         | extended |              |
zip_code                    | character varying(128)  |           |          |         | extended |              |
addr_state                  | character varying(128)  |           |          |         | extended |              |
dti                         | double precision        |           |          |         | plain    |              |
delinq_2yrs                 | character varying(128)  |           |          |         | extended |              |
earliest_cr_line            | character varying(128)  |           |          |         | extended |              |
inq_last_6mths              | character varying(128)  |           |          |         | extended |              |
mths_since_last_delinq      | character varying(128)  |           |          |         | extended |              |
mths_since_last_record      | character varying(128)  |           |          |         | extended |              |
open_acc                    | character varying(128)  |           |          |         | extended |              |
pub_rec                     | character varying(128)  |           |          |         | extended |              |
revol_bal                   | integer                 |           |          |         | plain    |              |
revol_util                  | character varying(128)  |           |          |         | extended |              |
total_acc                   | character varying(128)  |           |          |         | extended |              |
initial_list_status         | character varying(128)  |           |          |         | extended |              |
mths_since_last_major_derog | character varying(128)  |           |          |         | extended |              |
policy_code                 | integer                 |           |          |         | plain    |              |
is_bad                      | integer                 |           |          |         | plain    |              |
