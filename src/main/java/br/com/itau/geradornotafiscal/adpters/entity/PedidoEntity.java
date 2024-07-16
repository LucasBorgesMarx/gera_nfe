package br.com.itau.geradornotafiscal.adpters.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PedidoEntity {
	 @JsonProperty("id_pedido")
	    private int idPedido;

	    @JsonProperty("data")
	    private LocalDate data;

	    @JsonProperty("valor_total_itens")
	    private double valorTotalItens;

	    @JsonProperty("valor_frete")
	    private double valorFrete;

	    @JsonProperty("itens")
	    private List<ItenEntity> itens;

	    @JsonProperty("destinatario")
	    private DestinatarioEntity destinatarioEntity;

}
