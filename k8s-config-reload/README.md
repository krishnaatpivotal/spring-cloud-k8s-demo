# k8s-config-reload

This runs on Nodeport. This is the entry point to k8s Cluster without LB and Ingress.

# Prerequisite
Must Have Local K8s Cluster
Skaffold is not a must



This Repo(usermicro) springboot app is simple application for to see
* Load Application property file using k8s configMap.
* Spring Auto refreshes the properties value if changes occurs in ConfigMap
* We need RBAC policy for our springboot app so that it can watch the configMap.




# How to Run

Before deploying the springboot app make sure following yaml files are applied to k8s cluster.

kubectl apply -f k8s/rbac.yaml
kubectl apply -f k8s/config.yaml

# With manual deployment using Service and Deployment
mvn clean install -DskipTests 
./mvnw clean spring-boot:build-image

kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

# With Skaffold
Then run below command

mvn clean install -DskipTests && skaffold run

Check on which nodeport the application is running.

goto
localhost:nodeport/
It should print the ConfigMap value.

{bean.message: This is coming from K8s Cluster.}

Then update the configmap value to
{bean.message: This is coming from K8s Cluster 123 changed.}

then apply the changes
kubectl apply -f config.yaml
then visit 
localhost:nodeport/
it should print the updated value.
