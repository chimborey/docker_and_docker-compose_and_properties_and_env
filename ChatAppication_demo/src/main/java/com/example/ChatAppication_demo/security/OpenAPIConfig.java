package com.example.ChatAppication_demo.security;


// ចាប់យកបណ្ណាល័យ (Library) ត្រឹមត្រូវរបស់ io.swagger.v3
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info; // ប្រើប្រាស់ Info ត្រង់នេះដើម្បីកុំឱ្យច្រឡំជាមួយ ProcessHandle
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // ប្រកាសថា Class នេះជាកន្លែងកំណត់រចនាសម្ព័ន្ធ (Configuration) របស់កម្មវិធី
public class OpenAPIConfig {

    @Bean
    public SecurityFilterChain chain(HttpSecurity httpSecurity){
        return httpSecurity
                .csrf(crsf -> crsf.disable())
                .authorizeHttpRequests(author -> author.anyRequest().permitAll())
                .build();
    }

    // ⚙️ ១. កំណត់រចនាសម្ព័ន្ធរួម និងបន្ថែមប៊ូតុងចាក់សោរ JWT (Authorize Button)
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "BearerAuth"; // ឈ្មោះសោរ

        return new OpenAPI()
                .info(new Info()
                        .title("Chat Application API Documentation") // ឈ្មោះគម្រោងធំ
                        .description("នេះជាឯកសារណែនាំពីរបៀបប្រើប្រាស់ API សម្រាប់ប្រព័ន្ធ Chat Application") // ការពិពណ៌នារួម
                        .version("v1.0"))
                // បញ្ចូលលក្ខខណ្ឌការពារ៖ រាល់គ្រប់ API ទាំងអស់នឹងទាមទារសោរឈ្មោះ BearerAuth នេះ
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP) // ប្រភេទ HTTP Authentication
                                        .scheme("bearer")            // ទម្រង់ Bearer
                                        .bearerFormat("JWT")));       // ទម្រង់កូដជា JWT
    }

    // ⚙️ ២. បង្កើតក្រុមសម្រាប់ API សេរី (Public APIs Group)
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("1-PUBLIC-APIS") // ឈ្មោះក្រុមបង្ហាញលើ UI
                .pathsToMatch("/api/auth/**", "/login/**") // ស្កែនយកតែ API ណាដែលមានផ្លូវ (Path) ទាំងនេះ
                .build();
    }

    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder()
                .group("2-PRIVATE-APIS") // ឈ្មោះក្រុមបង្ហាញលើ UI
                .pathsToMatch("/api/chat/**", "/api/admin/**", "/api/buyer/**") // ស្កែនយក API ដែឡត្រូវការ Token
                .build();
    }
}
