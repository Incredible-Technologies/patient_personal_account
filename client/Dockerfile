
# Stage 1: Build the Angular app

FROM node:18 as build

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm install

COPY . .

RUN npm run build:ssr 

# Stage 2: Set up the server
FROM node:18 as serve

WORKDIR /app

COPY --from=build /app/dist ./dist

EXPOSE 8080

CMD [ "node", "dist/client/server/main.js" ]

