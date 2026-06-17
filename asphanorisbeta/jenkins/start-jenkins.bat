@echo off
echo ==========================================
echo  Iniciando Jenkins con Docker
echo ==========================================

cd /d "%~dp0"

echo.
echo [1/2] Descargando imagen de Jenkins...
docker pull jenkins/jenkins:lts-jdk17

echo.
echo [2/2] Iniciando contenedor...
docker-compose up -d

echo.
echo ==========================================
echo  ¡Jenkins iniciado exitosamente!
echo ==========================================
echo  Jenkins: http://localhost:8080
echo.
echo  Para ver los logs:
echo  docker logs -f asphanoris-jenkins
echo.
echo  Para detener:
echo  docker-compose down
echo ==========================================
pause