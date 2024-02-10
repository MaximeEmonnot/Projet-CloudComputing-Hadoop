JARCOMMAND := jar cf

BUILDS     := ./Builds
TASK_A_SRC := ./Sources/TaskA
TASK_B_SRC := ./Sources/TaskB
TASK_C_SRC := ./Sources/TaskC

default: clean all

all: task-a task-b task-c

task-a: task-a-temp task-a-final

task-a-temp:
	@echo "Task A Temp"

task-a-final:
	@echo "Task A Final"

task-b: task-b-temp task-b-final

task-b-temp:
	@echo "Task B Temp"

task-b-final:
	@echo "Task B Final"

task-c: task-c-temp task-c-final

task-c-temp:
	@$(JARCOMMAND) $(BUILDS)TaskCTemp.jar $(TASK_C_SRC)\TaskCTemp*.java

task-c-final:
	@echo "Task C Final"

clean:
	-rm -R -f $(BUILDS)