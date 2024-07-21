::Parameter1 = timer; Parameter2 = length to generate; ('all' for 7-10)
@echo off
Title NumberGenerator
set /p timer=Enter the amount for the timer (milliseconds): 
cls
echo Timer = %timer% milliseconds.
java -cp bin NumberGenerator %timer% 7
echo Something went wrong, SEE CODE.
pause