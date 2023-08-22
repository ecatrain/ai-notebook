build-ai-notebook-core-jar:
	${INFO} "Run jar build..."
	@ ./core/gradlew -Dorg.gradle.java.home=${JAVA17_HOME} -p ./core build
	${INFO} "Build jar complete"

build-ai-notebook-core-image:
	${INFO} "Run image build..."
	@ docker build -f ./core/ai-notebook-core.dockerfile -t ai-notebook-core:latest .
	${INFO} "Build image complete"

run-docker-compose:
	@ docker-compose up -d

restart-core:
	@ docker-compose up -d --remove-orphans --no-deps ai-notebook-core

down:
	@ docker-compose down

run: build-ai-notebook-core-jar build-ai-notebook-core-image run-docker-compose

restart: build-ai-notebook-core-jar build-ai-notebook-core-image restart-core

# Cosmetics
YELLOW := "\e[1;33m"
NC := "\e[0m"

# Shell Functions
INFO := @bash -c '\
  printf $(YELLOW); \
  echo "=> $$1"; \
  printf $(NC)' SOME_VALUE
