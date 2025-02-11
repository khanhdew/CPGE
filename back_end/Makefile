# List Variables Path
SERVER_MAIN = ./cmd/server/main.go
WIRE_DIR = internal/wire

# List Variables Wire
GO = go
WIRE = wire

# List Variables Goose
GOOSE=goose
GOOSE_NAME ?= GO_ECOMMERCE
GOOSE_DRIVER ?= mysql
GOOSE_DB_DSN = "root:root123@tcp(127.0.0.1:3306)/go_ecommerce"
GOOSE_MIGRATION_DSN ?= sql/schema
GOOSE_PATH_SCHEMA ?= sql/schema
GOOSE_PATH_QUERIES = sql/queries

# Phony Targets
.PHONY: help
.PHONY: sqlc_generate cre_env run_server
.PHONY: docker_run docker_stop docker_stop_v docker_exec_mysql docker_exec_redis
.PHONY: swagger_generate

# Help Command
help:
	@echo "Usage: make [command]"
	@echo "Commands:"
	@echo "\t run_server \t Start the server"
	@echo "\t cre_env \t Create .env from .yaml"
	@echo "\nSQLC Commands:"
	@echo "\t sqlc_generate \t Generate SQLC queries"
	@echo "\Docker Commands:"
	@echo "\t docker_run \t Run Docker Compose"
	@echo "\t docker_stop \t Stop Docker Compose"
	@echo "\t docker_stop_v \t Stop Docker Compose and remove volumes, networks, ..."
	@echo "\t docker_exec_mysql \t Execute MySQL Container"
	@echo "\t docker_exec_redis \t Execute Redis Container"
	@echo "\nSwagger Commands:"
	@echo "\t swagger_generate \t Generate Swagger Documentation"

# Run Server
run_server:
	@echo "Running Server"
	$(GO) run $(SERVER_MAIN)

# SQLC - Generate
sqlc_generate:
	@echo "Generating SQLC queries"
	sqlc -f ./environment/sqlc.yaml generate
	@echo "SQLC queries generated"

# Create Environment Variables
cre_env:
	@echo "Creating .env from .yaml"
	rm -rf .env
	$(GO) run cmd/cli/viper/main.vipper.go
	@echo ".env file created"

# Docker Compose
docker_run:
	@echo "Running Docker Compose"
	docker compose -f ./environment/docker-compose.yml up -d
	@echo "Docker Compose running"

docker_stop:
	@echo "Stopping Docker Compose"
	docker compose down -f ./environment/docker-compose.yml
	@echo "Docker Compose stopped"

docker_stop_v:
	@echo "Stopping Docker Compose and remove volumes, networks, ..."
	docker compose down -f ./environment/docker-compose.yml --volumes --remove-orphans
	@echo "Docker Compose stopped and removed volumes, networks, ..."

docker_exec_mysql:
	@echo "Executing MySQL Container"
	docker exec -it mysql_v8_container mysql -u root -p
	@echo "MySQL Container executed"

docker_exec_redis:
	@echo "Executing Redis Container"
	docker exec -it redis_container redis-cli
	@echo "Redis Container

# Swagger
swagger_generate:
	@echo "Generating Swagger Documentation"
	swag init -g ./cmd/server/main.go -o ./cmd/swag/docs
	@echo "Swagger Documentation generated"