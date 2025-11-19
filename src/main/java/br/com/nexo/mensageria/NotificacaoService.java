package br.com.nexo.mensageria;

import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class NotificacaoService {
    private final List<String> notificacoes = new LinkedList<>();

    public void adicionar(String notificacao) {
        notificacoes.add(0, notificacao); // adiciona no início (mais recente primeiro)
        if (notificacoes.size() > 50) { // limita a 50 notificações
            notificacoes.remove(notificacoes.size() - 1);
        }
    }

    public List<String> listar() {
        return Collections.unmodifiableList(notificacoes);
    }
}
