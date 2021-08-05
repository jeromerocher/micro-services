@ECHO OFF

del *.class /Q

ECHO.
ECHO Compiling '%1'...
javac "%~f1"
ECHO.

ECHO Executing '%~n1':
ECHO.
java %~n1


pause