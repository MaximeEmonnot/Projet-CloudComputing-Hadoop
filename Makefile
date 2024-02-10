JAVACOMPILE := javac
JARCOMMAND  := jar cvf
JAVATARGET  := 1.8

LIBRARIES   := .;./Lib/hadoop-annotations-3.3.6.jar;./Lib/hadoop-common-3.3.6.jar;./Lib/hadoop-mapreduce-client-core-3.3.6.jar

BUILDS      := ./Builds
TASK_A_SRC  := ./Sources/TaskA
TASK_B_SRC  := ./Sources/TaskB
TASK_C_SRC  := ./Sources/TaskC

default: all clean

all: task-a task-b task-c

task-a: task-a-temp task-a-final

task-a-temp:
	@echo Compiling TaskATemp...
	$(JAVACOMPILE) -source $(JAVATARGET) -target $(JAVATARGET) -cp "${LIBRARIES}" $(TASK_A_SRC)/TaskATemp*.java
	@echo Building JAR for TaskATemp...
	$(JARCOMMAND) $(BUILDS)/TaskATemp.jar $(TASK_A_SRC)/TaskATemp*.class

task-a-final:
	@echo Compiling TaskAFinal...
	$(JAVACOMPILE) -source $(JAVATARGET) -target $(JAVATARGET) -cp "${LIBRARIES}" $(TASK_A_SRC)/TaskAFinal*.java
	@echo Building JAR for TaskAFinal...
	$(JARCOMMAND) $(BUILDS)/TaskAFinal.jar $(TASK_A_SRC)/TaskAFinal*.class

task-b: task-b-temp task-b-final

task-b-temp:
	@echo Compiling TaskBTemp...
	$(JAVACOMPILE) -source $(JAVATARGET) -target $(JAVATARGET) -cp "${LIBRARIES}" $(TASK_B_SRC)/TaskBTemp*.java
	@echo Building JAR for TaskBTemp...
	$(JARCOMMAND) $(BUILDS)/TaskBTemp.jar $(TASK_B_SRC)/TaskBTemp*.class

task-b-final:
	@echo Compiling TaskBFinal...
	$(JAVACOMPILE) -source $(JAVATARGET) -target $(JAVATARGET) -cp "${LIBRARIES}" $(TASK_B_SRC)/TaskBFinal*.java
	@echo Building JAR for TaskBFinal...
	$(JARCOMMAND) $(BUILDS)/TaskBFinal.jar $(TASK_B_SRC)/TaskBFinal*.class

task-c: task-c-temp task-c-final

task-c-temp:
	@echo Compiling TaskCTemp...
	$(JAVACOMPILE) -source $(JAVATARGET) -target $(JAVATARGET) -cp "${LIBRARIES}" $(TASK_C_SRC)/TaskCTemp*.java
	@echo Building JAR for TaskCTemp...
	$(JARCOMMAND) $(BUILDS)/TaskCTemp.jar $(TASK_C_SRC)/TaskCTemp*.class

task-c-final:
	@echo Compiling TaskCFinal...
	$(JAVACOMPILE) -source $(JAVATARGET) -target $(JAVATARGET) -cp "${LIBRARIES}" $(TASK_C_SRC)/TaskCFinal*.java
	@echo Building JAR for TaskCFinal...
	$(JARCOMMAND) $(BUILDS)/TaskCFinal.jar $(TASK_C_SRC)/TaskCFinal*.class
