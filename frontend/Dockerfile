docker image prune -a --force
mkdir -p /var/jenkins_home/images_tar
cd /var/jenkins_home/workspace/deploytest/testproject_react/
docker build -t react .
docker save react > /var/jenkins_home/images_tar/react.tar

cd /var/jenkins_home/workspace/deploytest/testproject/
docker build -t django .
docker save django > /var/jenkins_home/images_tar/django.tar

ls /var/jenkins_home/images_tar