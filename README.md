# flash_project (Server)!

## flash_project IOS GitHub Link
https://github.com/kihwn/flash_iOS

## Description
### Project Name
> 음성을 통한 치매 진단 AI<br>
### Project Personnel
> 3<br>
### Project Description
> 통화 음성 등의 치매 예상 환자의 음성 파일을 클라이언트에 넣으면 이를 서버로 전달하여 Clova Speech 서비스를 이용해 Text로 전환, AI Model에 넣어 치매인지 아닌지 판단을 하여 결과를 반환해주는 프로젝트<br>

### System Architecture
![After System Architecture](https://github.com/ShinHeeEul/flash_project/assets/83682424/c76e27c2-ca1a-4a4e-b66b-95a764473bf6)

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
  > front-end : swift, html, css, javascript <br>
  > AI Model(BERT) : cuda, pytorch, transformer, tensorflow <br> 
  > Training Data : AI Hub
  
### developed Environment
  > Back-end,front-end : Intellij <br>
  > Server : Local Environment, AWS EC2(t2.micro, g3.4xlarge <br>
  > client : xcode
  > AI Model : Anaconda, Jupyter NoteBook <br>
  > docker
  
## Usage

 1. IOS 앱 다운
 2. 실행 후, 회원가입
 3. 로그인 후 첨부란을 통해 사용자 음성 데이터 첨부
 4. 텍스트로 변환된 음성 데이터 확인 후 분석 요청
 5. 분석 결과 확인

## Usage Example
## html
![exampleClient](https://user-images.githubusercontent.com/83682424/228263906-079770d6-1b21-42d8-9c66-d5068a101378.jpg)
## IOS
![４](https://github.com/ShinHeeEul/flash_project/assets/83682424/659b7db1-8501-45a8-808f-aea4400e4db8)
 ![３](https://github.com/ShinHeeEul/flash_project/assets/83682424/aa697fff-fd84-419e-a44d-3c634aa5093b)<br>
 ![２](https://github.com/ShinHeeEul/flash_project/assets/83682424/e548ee14-fa0d-41e2-b994-56cc89179532)
 ![１](https://github.com/ShinHeeEul/flash_project/assets/83682424/4b1ed8b3-1047-45c1-bdfb-cf79c2fc3ec1)



