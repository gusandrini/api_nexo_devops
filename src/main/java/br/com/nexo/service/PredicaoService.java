package br.com.nexo.service;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import br.com.nexo.dto.DescricaoClienteDTO;
import java.util.HashMap;
import java.util.Map;

@Service
public class PredicaoService {

    private static final String IA_URL = "https://api-gs-2tdspw-ia.onrender.com/predict";

    public Map<String, Object> obterPredicaoIA(DescricaoClienteDTO dto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("Job Satisfaction", dto.getDsSatisfacao());
        body.put("Salary", dto.getNrSalario());
        body.put("Field of Study (Class)", dto.getIdCampoEstudo());
        body.put("Current Occupation (Class)", dto.getIdOcupacao());
        body.put("Education Leve (Class)", dto.getIdNivelEducacional());
        body.put("Family Influence (Class)", dto.getIdInfluenciaFamiliar());
        body.put("Years of Experience", dto.getQtdaAnosExperiencia());
        body.put("Technology", dto.getDsTecnologia());
        body.put("Change", dto.getDsMudanca());
        body.put("Age", dto.getNrIdade());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        try {
            Map response = restTemplate.postForObject(IA_URL, entity, Map.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
