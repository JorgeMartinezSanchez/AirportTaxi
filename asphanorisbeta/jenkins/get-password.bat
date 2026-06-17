@echo off
echo ==========================================
echo  Obteniendo contraseña de Jenkins
echo ==========================================

echo.
docker exec asphanoris-jenkins cat /var/jenkins_home/secrets/initialAdminPassword

echo.
echo ==========================================
echo  Copia la contraseña de arriba
echo ==========================================
pause