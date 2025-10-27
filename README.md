Lista de Tarefas 06
📌 Descrição da Atividade

O objetivo desta atividade foi configurar e corrigir o projeto Lista de Tarefas, garantindo o funcionamento correto da comunicação entre o Frontend (Vue.js) e o Backend (Spring Boot).

Durante a execução inicial, foi identificado um erro proposital relacionado à política de CORS (Cross-Origin Resource Sharing), impedindo o carregamento dos dados seed na aplicação.

❌ Erro Encontrado

Ao executar o projeto, o frontend não conseguia carregar as tarefas do backend.
No console do navegador, era exibida uma mensagem semelhante a:

Access to fetch at 'http://localhost:8080/tarefas' from origin 'http://localhost:5173' has been blocked by CORS policy.

🔍 Causa

O erro ocorre porque o navegador bloqueia requisições entre origens diferentes (frontend e backend em portas distintas).
O backend não possuía configuração adequada para permitir conexões externas (CORS).

🛠️ Correção Aplicada

Foi criada a classe WebConfig.java para configurar o CORS globalmente no backend Spring Boot.

📄 Arquivo:

backend/api/src/main/java/br/com/tarefas/api/config/WebConfig.java

package br.com.tarefas.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*") // permite qualquer origem
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

🧠 Explicação da Solução

Essa configuração informa ao Spring Boot que:

Todas as rotas (/**) aceitam requisições de qualquer origem (*);

Métodos HTTP comuns estão liberados (GET, POST, PUT, DELETE);

Cabeçalhos personalizados são aceitos;

É permitido o envio de cookies/autenticação (allowCredentials(true)).

Após essa configuração, o frontend passou a se comunicar corretamente com o backend.

🚀 Passo a Passo para Executar a Aplicação
1️⃣ Backend (Spring Boot)
cd backend/api
./mvnw spring-boot:run


O backend será iniciado em:

http://localhost:8080

2️⃣ Frontend (Vue.js)
cd app-tarefas
npm install
npm run dev


O frontend será iniciado em:

http://localhost:5173

✅ Resultado Esperado

Após a correção, a aplicação deve:

Carregar corretamente as tarefas iniciais (seed data);

Permitir a listagem e manipulação das tarefas entre frontend e backend;

Exibir os dados sem erros de CORS no console.

🧩 Estrutura do Projeto
projeto-listatarefas_spring_vue_cors/
│
├── app-tarefas/                 # Frontend (Vue.js + Vite)
│   ├── src/
│   └── package.json
│
└── backend/api/                 # Backend (Spring Boot)
    ├── src/main/java/br/com/tarefas/api/
    ├── pom.xml
    └── WebConfig.java

🧾 Autor

Nome: Arthur M. Rieger
Disciplina: Desenvolvimento Web
Atividade: Lista de Tarefas 06
(Caso não funcione tente o .rar)
