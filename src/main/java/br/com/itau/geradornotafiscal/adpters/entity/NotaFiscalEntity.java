package br.com.itau.geradornotafiscal.adpters.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NotaFiscalEntity {

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("id_nota_fiscal")
    private String idNotaFiscal;

    @JsonProperty("data")
    private LocalDateTime data;

    @JsonProperty("valor_total_itens")
    private double valorTotalItens;

    @JsonProperty("valor_frete")
    private double valorFrete;

    @JsonProperty("itens")
    private List<ItemNotaFiscalEntity> itens;

    @JsonProperty("destinatario")
    private DestinatarioEntity destinatarioEntity;

}