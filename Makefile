JARCOMMAND := jar cf

BUILDS     := ./Builds
TASK_A_SRC := ./Sources/TaskA
TASK_B_SRC := ./Sources/TaskB
TASK_C_SRC := ./Sources/TaskC

default: all

all: task-a task-b task-c

task-a: task-a-temp task-a-final

task-a-temp:
	@echo Building JAR for TaskATemp...
	$(JARCOMMAND) $(BUILDS)/TaskATemp.jar $(TASK_A_SRC)/TaskATemp*.java

task-a-final:
	@echo Building JAR for TaskAFinal...
	$(JARCOMMAND) $(BUILDS)/TaskAFinal.jar $(TASK_A_SRC)/TaskAFinal*.java

task-b: task-b-temp task-b-final

task-b-temp:
	@echo Building JAR for TaskBTemp...
	$(JARCOMMAND) $(BUILDS)/TaskBTemp.jar $(TASK_B_SRC)/TaskBTemp*.java

task-b-final:
	@echo Building JAR for TaskBFinal...
	$(JARCOMMAND) $(BUILDS)/TaskBFinal.jar $(TASK_B_SRC)/TaskBFinal*.java

task-c: task-c-temp task-c-final

task-c-temp:
	@echo Building JAR for TaskCTemp...
	$(JARCOMMAND) $(BUILDS)/TaskCTemp.jar $(TASK_C_SRC)/TaskCTemp*.java

task-c-final:
	@echo Building JAR for TaskCFinal...
	$(JARCOMMAND) $(BUILDS)/TaskCFinal.jar $(TASK_C_SRC)/TaskCFinal*.java