# flash_project

## Description
### Project Name
> 음성을 통한 치매 진단 AI<br>
### Project Personnel
> 3<br>
### Project Description
> 통화 음성 등의 치매 예상 환자의 음성 파일을 클라이언트에 넣으면 이를 서버로 전달하여 Clova Speech 서비스를 이용해 Text로 전환, AI Model에 넣어 치매인지 아닌지 판단을 하여 결과를 반환해주는 프로젝트<br>

### System Architecture
  ![afterSystemArch](https://user-images.githubusercontent.com/83682424/228255833-8bfec615-5943-449f-8097-4cb91fb308ca.jpg)
  
### Domain Model
#### Client
![beforeClientClassDiagram](https://github.com/ShinHeeEul/flash_project/assets/83682424/ddcae9e7-0a60-4328-ac8c-529e179780d3)

#### Server
![beforeServerClassDiagram](https://github.com/ShinHeeEul/flash_project/assets/83682424/51a52b19-4e23-41e6-8b2a-3d9721b9f146)


## Environment
### Hardware
> OS : Window, MacOS <br>
> GPU : Nvidia MX 450
  
### Software
  > Back-end : Spring frameWork, Spring Boot, Spring Web, Lombok<br>
  > DB : H2 Database<br>
  > front-end : html, css, javascript <br>
  > AI Model(BERT) : cuda, pytorch, transformer, tensorflow <br> 
  > Training Data : AI Hub
  
### developed Environment
  > Back-end,front-end : Intellij <br>
  > Server : Local Environment -> AWS <br>
  > AI Model : Anaconda, Jupyter NoteBook <br>
  > docker를 통해 이미지화
  
## Usage
> 현재 로컬 환경에서 개발 <br>

 1. H2 DB, Jupyter Notebook 실행
 2. Server의 flashApplication.java의 main 메서드 실행
 3. localhost:8080으로 접속 후, 회원가입
 4. 로그인 후 첨부란을 통해 사용자 음성 데이터 첨부
 5. 분석 결과 확인

## Usage Example
![exampleClient](https://user-images.githubusercontent.com/83682424/228263906-079770d6-1b21-42d8-9c66-d5068a101378.jpg)

  
