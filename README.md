

graph TD
    A[Início: Cliente manda Oi] --> B{Menu Principal}
    B -->|Opção 1| C[Venda: E-book Tibet]
    B -->|Opção 2| D[Coleta de Dados: Agendamento]
    B -->|Opção 3| E[Suporte / FAQ]
    B -->|Entrada Inválida| F[Mensagem de Erro + Reiniciar Menu]
    F --> B
    
    C --> G[Link de Pagamento / Checkout]
    D --> H[Salvar Lead no MongoDB]
    H --> I[Aviso para Consultor Humano]
