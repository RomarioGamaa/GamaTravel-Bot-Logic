
# 🌍 GamaTravel - Automação de Atendimento e Vendas

Este projeto é um protótipo de alta fidelidade desenvolvido para validar a lógica de um sistema de atendimento automatizado para a agência **GamaTravel**.

## 🚀 Sobre o Projeto
O sistema simula um fluxo de chatbot para WhatsApp, focado em dois pilares de negócio:
* **Conversão de Vendas:** Funil persuasivo para o infoproduto "Roteiro Secreto: Tibet".
* **Agendamento de Consultoria:** Coleta de dados estruturados para atendimento personalizado.

## 📊 Fluxograma do Sistema
```mermaid
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
