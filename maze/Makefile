# Répertoires
SRC_DIR := src
BIN_DIR := bin

# Outils
JAVAC := javac
JAVA := java

# Classe principale (à adapter)
MAIN := main.Main

# Sources (jusqu'à 3 niveaux de sous-répertoires, ajuste si besoin)
SOURCES := $(wildcard $(SRC_DIR)/*.java) \
           $(wildcard $(SRC_DIR)/*/*.java) \
           $(wildcard $(SRC_DIR)/*/*/*.java)

# Cibles
all:
	@if not exist $(BIN_DIR) mkdir $(BIN_DIR)
	$(JAVAC) -encoding Cp1252 -d $(BIN_DIR) $(SOURCES)

run: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN)

clean:
	@if exist $(BIN_DIR) rmdir /s /q $(BIN_DIR)
