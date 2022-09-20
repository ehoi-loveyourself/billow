# FROM node:lts-alpine as build-stage
# WORKDIR /var/jenkins_home/workspace/billow/frontend
# COPY package*.json ./
# RUN npm install
# COPY . .
# RUN npm run build
# FROM nginx:stable-alpine as production-stage

# COPY --from=build-stage /var/jenkins_home/workspace/billow/frontend/dist /usr/share/nginx/html
# COPY --from=build-stage /var/jenkins_home/workspace/billow/frontend/deploy_conf/nginx.conf /etc/nginx/conf.d/default.conf
# EXPOSE 80
# CMD ["nginx", "-g","daemon off;"]

FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:stable-alpine
RUN rm -rf /etc/nginx/conf.d/defalut.conf
COPY --from=build-stage /app/nginx/default.conf /etc/nginx/conf.d/default.conf

RUN rm -rf /usr/share/nginx/html/*
COPY --from=build-stage /app/dist /usr/share/nginx/html

EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]