# # Node.js 14을 기반으로 하는 Docker 이미지 사용
# FROM node:14

# # 앱 디렉토리 생성
# RUN mkdir -p /usr/src/app
# WORKDIR /usr/src/app

# # 필요한 패키지 파일 복사
# COPY frontend/package.json frontend/package-lock.json ./

# # 패키지 설치
# RUN npm install

# # 소스 코드 복사
# COPY frontend/ .

# # 앱 실행 (개발 서버로 실행하려면 npm start 등을 사용)
# CMD ["npm", "run", "start"]

# Use the official Node.js image with a specific version
FROM node:14

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code to the container
COPY . .

# Expose port 3000 to the outside world
EXPOSE 3000

# Command to run the application
CMD ["npm","run" ,"start"]