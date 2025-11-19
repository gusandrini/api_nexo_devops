package br.com.nexo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PredicaoService {

    @Value("${spring.ai.openai.api-key}")
    private String openaiApiKey;

    // Permite usar endpoint customizado (ex: Render) ou OpenAI
    @Value("${render.model.url:}")
    private String renderModelUrl;

    @Value("${openai.api.url:https://api.openai.com/v1/chat/completions}")
    private String openaiApiUrl;

    @Value("${openai.model:gpt-3.5-turbo}")
    private String openaiModel;

    public Double obterPercentualMudanca(Object dadosDescricao) {
        // Monta prompt com os dados do formulário
        String prompt = "Com base no seguinte perfil, qual a probabilidade (em %) desse usuário mudar de profissão? Responda apenas com um número de 0 a 100. Perfil: " + dadosDescricao.toString();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        Map<String, Object> body = new HashMap<>();
        body.put("model", openaiModel);
        body.put("messages", new Object[]{message});
        body.put("max_tokens", 10);
        body.put("temperature", 0.2);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        String endpoint = (renderModelUrl != null && !renderModelUrl.isBlank()) ? renderModelUrl : openaiApiUrl;
        try {
            Map response = restTemplate.postForObject(endpoint, entity, Map.class);
            if (response != null && response.containsKey("choices")) {
                Object choices = response.get("choices");
                if (choices instanceof java.util.List && !((java.util.List) choices).isEmpty()) {
                    Map choice = (Map) ((java.util.List) choices).get(0);
                    Map messageResp = (Map) choice.get("message");
                    String content = messageResp.get("content").toString().replaceAll("[^0-9]", "");
                    if (!content.isEmpty()) {
                        return Double.valueOf(content);
                    }
                }
            }
        } catch (Exception e) {
            // Em caso de erro, retorna null (ou pode lançar exceção customizada)
            e.printStackTrace();
        }
        return null;
    }
}
