

# ១. ប្រើប្រាស់ជំនាន់ Java (JDK 17 ឬ 21 ទៅតាមគម្រោងរបស់អ្នក)
FROM eclipse-temurin:25-jdk-alpine

# ២. កំណត់ Folder ធ្វើការនៅក្នុង Container
WORKDIR /app

# ៣. ចម្លងឯកសារ .jar ដែល build រួចពីថត target មកកាន់ Container
COPY target/*.jar app.jar

# ៤. បញ្ជាឱ្យ Container រត់កម្មវិធី Spring Boot ពេលវាចាប់ផ្តើម
ENTRYPOINT ["java", "-jar", "app.jar"]