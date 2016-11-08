@ECHO OFF
start javaw -cp hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
@ECHO BANCO INICIADO
java -cp hsqldb.jar org.hsqldb.Server -database.0 file:dados\SANDB -dbname.0 SANDB


