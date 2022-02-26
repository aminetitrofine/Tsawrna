# Stage 1: Building artifact
FROM node:latest as node
WORKDIR /app

COPY package* .
RUN npm install

COPY . .
RUN npm run build --prod

# Stage 2: Running artifact
FROM nginx:alpine
COPY --from=node /app/dist/moroccanpixels_frontend /usr/share/nginx/html